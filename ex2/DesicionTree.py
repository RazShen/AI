import utils


class DTNode(object):
    """
    This is a node for the tree (also the root).
    Notice each node holds a dictionary of children (which are the dtls that was built values of its attribute)
    """

    def __init__(self, attribute, depth, is_leaf=False, preds=None):
        """
        Initialize the node with its attribute, current depth, is it a leaf and if so what is the prediction
        :param attribute: of the node
        :param depth: of the node
        :param is_leaf: is it a leaf
        :param preds: what is the prediction
        """
        self.train_tags = utils.tags_train
        self.train_examples = utils.examples_train
        self.attr = attribute
        self.values = {}
        self.depth = depth
        self.is_leaf = is_leaf
        self.classification = preds
        self.children = {}
