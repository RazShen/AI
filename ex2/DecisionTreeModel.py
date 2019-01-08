import utils
from collections import Counter
from DesicionTree import DTNode
import math


class DTModel(object):
    """
    DecisionTree class (manage the tree and the algorithm), includes the DTL algorithm (as learned in class) and all
    the methods to calculate the entropy, gain and other needed methods.
    """

    def __init__(self):
        """
        initialize the class
        """
        self.train_tags = utils.tags_train
        self.train_examples = utils.examples_train
        self.total_attributes = utils.features_train
        self.possible_tags = set(self.train_tags)
        self.dict_feature_and_values = {}  # dictionary of every feature with its possible values
        self.get_values_for_feature()
        self.root = self.dtl(self.train_examples, utils.tags_train, utils.features_train, self.mode(self.train_tags), 0)

    def dtl(self, examples, tags, attributes, default, depth):
        """
        Run the recursive DTL construct as learned in class
        :param examples: to build the train from
        :param tags: to build the train from
        :param attributes: to build the train from
        :param default: mode(train_tags)
        :param depth: of the current element
        :return: constructed dt
        """
        if len(examples) == 0:
            return DTNode(attributes, depth, is_leaf=True, preds=default)
        elif self.get_if_examples_own_same_tag(tags):
            return DTNode(depth, attributes, is_leaf=True, preds=tags[0])
        elif len(attributes) == 0:
            return DTNode(depth, attributes, is_leaf=True, preds=self.mode(tags))
        else:
            # find best attribute according to gain
            best = self.choose_attibute(examples, tags, attributes)
            tree = DTNode(best, depth)
            attr_for_new_tree = attributes[:]
            attr_for_new_tree.remove(best)
            idx_best = self.total_attributes.index(best)
            # create new nodes according to <best> values
            for val in self.dict_feature_and_values[idx_best]:
                examples_tags_with_val = [(example, tag) for example, tag in zip(examples, tags) if
                                          example[idx_best] == val]
                examples_val = [example for example, tag in examples_tags_with_val]
                tags_val = [tag for example, tag in examples_tags_with_val]
                branch_tree = self.dtl(examples_val, tags_val, attr_for_new_tree, self.mode(tags), depth + 1)
                tree.children[val] = branch_tree
            return tree

    def predict(self, example):
        """
        get prediction for example by iterating the tree
        :param example: to predict
        :return: prediction
        """
        curr = self.root
        while not curr.is_leaf:
            # get the feature with the index of the current attribute in this node
            feature_value = example[self.total_attributes.index(curr.attr)]
            # go to that feature node and continue scanning the tree
            curr = curr.children[feature_value]
        return curr.classification

    def get_values_for_feature(self):
        """
        Get the possible values for each feature (for example: age:[adult,child])
        :return: none
        """
        for i in range(len(utils.features_train)):
            self.dict_feature_and_values[i] = set()
            # feature 1 in index 0 of example
            for example in utils.examples_train:
                self.dict_feature_and_values[i].add(example[i])

    def find_positive_tag(self):
        """
        get the positive tag from the possible positive tags
        :return: positive tag
        """
        for tag in self.possible_tags:
            if tag in ["yes", "true"]:
                return tag

    def get_constructed_dt(self, node):
        """
        :param node: node to scan from
        :return: constructed dt as string
        """
        tree_as_string = ""
        for child in sorted(node.children):
            tree_as_string += node.depth * "\t"
            if node.depth > 0:
                tree_as_string += "|"
            tree_as_string += node.attr
            tree_as_string += "=" + child
            if node.children[child].is_leaf:
                tree_as_string += ":"
                tree_as_string += str(node.children[child].classification) + "\n"
            else:
                tree_as_string += "\n"
                tree_as_string += self.get_constructed_dt(node.children[child])

        return tree_as_string

    def get_if_examples_own_same_tag(self, tags):
        """
        check if all the tags are the same
        :param tags: tags
        :return: if all the tags are the same
        """
        set_tags = set(tags)
        return len(set_tags) == 1

    def mode(self, tags):
        """
        return most dominant class
        :param tags: to check
        :return: most dominant class
        """

        positive_c = 0
        negative_c = 0
        c = Counter()
        for tag in tags:
            c[tag] += 1
            if tag == "yes" or tag == "true":
                positive_c += 1
            else:
                negative_c += 1
        if positive_c == negative_c:
            return self.find_positive_tag()
        return c.most_common(1)[0][0]

    def choose_attibute(self, examples, tags, attributes):
        """
        calculate best attribute by the best gain
        :param examples:
        :return:
        """
        max_attr = attributes[0]
        max_gain = 0.0
        for attr in attributes:
            gain_for_attr = self.get_gain(examples, tags, attr)
            if max_gain < gain_for_attr:
                max_gain = gain_for_attr
                max_attr = attr
        return max_attr

    def get_gain(self, examples, tags, attribute):
        """
        Get the total gain for attribute
        :param examples: examples
        :param tags: tags
        :param attribute: to get gain for
        :return: gain
        """
        total_examples_and_tags = [(example, tag) for example, tag in zip(examples, tags)]
        s_entropy = self.get_entropy(total_examples_and_tags)
        idx_attribute = self.total_attributes.index(attribute)
        for val in self.dict_feature_and_values[idx_attribute]:
            examples_tags_with_val = [(example, tag) for example, tag in zip(examples, tags) if
                                      example[idx_attribute] == val]
            entropy_val = self.get_entropy(examples_tags_with_val)
            s_entropy -= (float(len(examples_tags_with_val)) * entropy_val) / len(examples)
        return s_entropy

    def get_entropy(self, examples_and_tags):
        """
        Calculate the entropy for examples and tag of some value of the attribute
        :param examples_and_tags: that has the value of the attribute
        :return: entropy
        """
        tags = [tag for example, tag in examples_and_tags]
        if not tags:
            return 0
        tags_and_total_num = Counter()
        entropy = 0.0
        p_per_class = []
        for tag in tags:
            tags_and_total_num[tag] += 1
        for tag_total_num in tags_and_total_num:
            p_per_class.append(float(tags_and_total_num[tag_total_num]) / len(tags))
        for p in p_per_class:
            if p == 0:
                return 0
            entropy += -p * math.log(p, 2)
        return entropy
