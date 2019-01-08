import utils
from functools import reduce


class NBModel(object):
    """
    Naive bayes model (using the algorithm learned in class).
    Using smoothing and the naive bayes assumption.
    """

    def __init__(self):
        """
        Initialize the model
        """
        self.train_tags = utils.tags_train
        self.train_examples = utils.examples_train
        self.possible_tags = set(self.train_tags)
        self.dict_tag_and_examples = {}  # each tag has examples
        self.get_dicts_by_tags(self.possible_tags)
        self.dict_feature_and_values = {}  # dictionary of every feature with its possible values
        self.get_values_for_feature()

    def get_dicts_by_tags(self, set_tags):
        """
        Fill a dictionary by the format- tag_x : examples with the tag x (yes: example1, example2,...)
        :param set_tags: set of tags
        :return: none
        """
        for possible_tag in set_tags:
            for example, tag in zip(utils.examples_train, utils.tags_train):
                if tag == possible_tag:  # if the tag of the example is the wanted tag
                    if possible_tag in self.dict_tag_and_examples:
                        self.dict_tag_and_examples[possible_tag].append(example)
                    else:
                        self.dict_tag_and_examples[possible_tag] = [example]

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

    def get_probability_for_tag(self, example, examples_for_tag):
        """
        Multiply all the probability for every feature and the probability for the class itself
        :param example: to tag
        :param examples_for_tag: list of all the examples for some tag (yes/no)
        :return: P(x_1|c) * ... P(x_n|c) * P(c)
        """
        probs_for_features = []
        for i in range(len(example)):
            probs_for_features.append(self.get_probability_per_feature(examples_for_tag, i, example))
        prob_for_class = float(len(examples_for_tag)) / len(self.train_tags)
        return reduce(lambda x, y: x * y, probs_for_features) * prob_for_class

    def get_probability_per_feature(self, examples_for_tag, feature_index, example):
        """
        Get the probability for feature with index <feature_index> of the example (using smoothing)
        :param examples_for_tag: list of all the examples for some tag (yes/no)
        :param feature_index: feature to inspect
        :param example: to predict
        :return: probability for that feature
        """
        wanted_value = example[feature_index]
        matched_examples = 1  # for smoothing
        for train_example in examples_for_tag:
            if train_example[feature_index] == wanted_value:
                matched_examples += 1
        total_possible_values_for_feature = len(self.dict_feature_and_values[feature_index])
        return float(matched_examples) / (total_possible_values_for_feature + len(examples_for_tag))

    def find_positive_tag(self):
        """
        If yes and no have the same probability, return yes
        :return: yes/true
        """
        for tag in self.possible_tags:
            if tag in ["yes", "true"]:
                return tag

    def predict(self, example):
        """
        Get prediction per example, find the probability for each tag and find the tag that has the best
        probability
        :param example: to predict on
        :return: best tag
        """
        max = 0.0
        max_tag = ""
        probs = []
        for possible_tag in self.possible_tags:  # yes or no
            prob_per_tag = self.get_probability_for_tag(example, self.dict_tag_and_examples[possible_tag])
            probs.append(prob_per_tag)
            if prob_per_tag > max:  # if this tag has the best probability so far
                max = prob_per_tag
                max_tag = possible_tag

        if len(probs) == 2:
            if probs[0] == probs[1]:  # if yes and no have the same probability, return yes
                return self.find_positive_tag()
        return max_tag
