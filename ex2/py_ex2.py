from KNN import KnnModel
from NB import NBModel
from DecisionTreeModel import DTModel
import utils


def main():
    """
    Run the different models and write the results
    :return: none
    """
    knn_acc, knn_tags = get_results_knn()
    nb_acc, nb_tags = get_results_nb()
    dt_acc, dt_tags, tree = get_results_dt()
    write_outputs(knn_acc, knn_tags, nb_acc, nb_tags, dt_acc, dt_tags, tree)


def write_outputs(knn_acc, knn_tags, nb_acc, nb_tags, dt_acc, dt_tags, tree):
    """
    Write the output.txt and output_tree.txt
    :param knn_acc: knn accuracy
    :param knn_tags: knn tags as list
    :param nb_acc: naive bayes accuracy
    :param nb_tags: naive bayes tags
    :param dt_acc: decision tree accuracy
    :param dt_tags: decision tree tags
    :param tree: decision tree
    :return: none
    """
    # write the tree
    with open("output_tree.txt", "w") as tree_output:
        tree_output.write(tree)
    # write the output.txt
    with open("output.txt", "w") as output:
        output.write("Num\tDT\tKNN\tnaiveBase\n")
        for i, _ in enumerate(utils.tags_test):
            output.write(str(i + 1) + "\t" + str(dt_tags[i]) + "\t" + str(knn_tags[i]) + "\t" + str(nb_tags[i]) + "\n")
        output.write("\t" + str(dt_acc) + "\t" + str(knn_acc) + "\t" + str(nb_acc))


def get_results_knn():
    """
    Run the KNN algorithm
    :return: acc, tags
    """
    tags = []
    knn = KnnModel()
    for example in utils.examples_test:
        tags.append(knn.predict(example))
    knn_acc = utils.get_acc(utils.tags_test, tags)
    return knn_acc, tags


def get_results_nb():
    """
    Run the NB algorithm
    :return: acc,tags
    """
    tags = []
    nb = NBModel()
    for example in utils.examples_test:
        tags.append(nb.predict(example))
    nb_acc = utils.get_acc(utils.tags_test, tags)
    return nb_acc, tags


def get_results_dt():
    """
    Run the Decision Tree algorithm
    :return: acc, tags, tree
    """
    tags_dtl = []
    dt = DTModel()
    for example in utils.examples_test:
        tags_dtl.append(dt.predict(example))
    dt_acc = utils.get_acc(utils.tags_test, tags_dtl)
    tree = dt.get_constructed_dt(dt.root)
    tree = tree[:len(tree) - 1]
    return dt_acc, tags_dtl, tree


if __name__ == '__main__':
    main()
