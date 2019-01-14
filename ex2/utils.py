import math

def parse_file(labeled_file_path):
    """
    Parse the file to features, examples, and tags aligned with examples (as lists)
    :param labeled_file_path: path of labeled file
    :return: attributes, examples, tags
    """
    attributes = []
    examples = []
    tags_aligned_with_examples = []
    with open(labeled_file_path, "r") as labeled_file:
        content = labeled_file.readlines()
        attributes += content[0].strip("\n").strip().split("\t")
        for line in content[1:]:
            line = line.strip("\n").strip().split("\t")
            example, tag = line[:len(line) - 1], line[-1]
            examples.append(example)
            tags_aligned_with_examples.append(tag)
    return attributes, examples, tags_aligned_with_examples


def get_acc(true_tags, predicted_tags):
    """
    Find accuracy of matching 2 lists.
    :param true_tags: real tags
    :param predicted_tags: predicated tags
    :return: accuracy
    """
    good = bad = 0.0
    for x, y in zip(true_tags, predicted_tags):
        if x == y:
            good += 1
        else:
            bad += 1
    acc = float(good) / float(good + bad)
    return float(math.ceil(acc * 100)) / float(100)


# Initialize global utils variables

features_train, examples_train, tags_train = parse_file("train.txt")
features_train = features_train[:len(features_train) - 1]
features_test, examples_test, tags_test = parse_file("test.txt")
