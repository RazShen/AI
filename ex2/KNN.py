from collections import Counter
import utils


class KnnModel(object):
    """
    KNN model class, where k is 5.
    the metric is hamming distance.
    """

    def __init__(self):
        """
        Initailize the model
        """
        self.train_tags = utils.tags_train
        self.train_examples = utils.examples_train

    def predict(self, example):
        """
        Return prediction for example
        :param example: to predict for
        :return: prediction tag
        """
        examples_and_tags = [(ex, tag) for ex, tag in zip(self.train_examples, self.train_tags)]
        example_and_distance = []
        for example_and_tag in examples_and_tags:
            distance = self.calc_hamming_distance(example_and_tag[0], example)
            example_and_distance.append((example_and_tag, distance))

        closest_k = sorted(example_and_distance, key=self.get_distance_from_tuple)[:5]  # get 5 closest examples
        # extract just the tags
        closest_k_tags = [item[0][1] for item in closest_k]
        return self.get_top_tag(closest_k_tags)

    def calc_hamming_distance(self, first_example, second_example):
        """
        calculate hamming distance between two examples
        :param first_example: first example
        :param second_example: second example
        :return: hamming distance between examples
        """
        distance = 0
        for feature_1, feature_2 in zip(first_example, second_example):
            if feature_1 != feature_2:
                distance += 1
        return distance

    def get_distance_from_tuple(self, tagged_example_and_distance):
        """
        for sorting the elements
        :param tagged_example_and_distance: tuple of (example and tag, distance)
        :return: distance
        """
        return tagged_example_and_distance[1]

    def get_top_tag(self, tags):
        """
        Get the common tag from tags list (5 elements)
        :param tags: list
        :return: common tag
        """
        tags_counter = Counter()
        for tag in tags:
            tags_counter[tag] += 1
        return tags_counter.most_common(1)[0][0]
