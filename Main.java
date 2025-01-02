class AVLTree {

    // Node class for AVL Tree
    class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    private Node root;

    // Get the height of a node
    private int height(Node node) {
        return (node == null) ? 0 : node.height;
    }

    // Get the balance factor of a node
    private int getBalance(Node node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    // Right rotate subtree rooted with y
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Left rotate subtree rooted with x
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Insert a key into the AVL Tree
    public void insert(int key) {
        root = insertNode(root, key);
    }

    private Node insertNode(Node node, int key) {
        // Perform normal BST insertion
        if (node == null) return new Node(key);

        if (key < node.key)
            node.left = insertNode(node.left, key);
        else if (key > node.key)
            node.right = insertNode(node.right, key);
        else
            return node; // Duplicate keys not allowed

        // Update height of this node
        node.height = 1 + Math.max(height(node.left), height(node.right));

        // Get the balance factor
        int balance = getBalance(node);

        // Perform rotations if the node is unbalanced

        // Left-Left case
        if (balance > 1 && key < node.left.key)
            return rightRotate(node);

        // Right-Right case
        if (balance < -1 && key > node.right.key)
            return leftRotate(node);

        // Left-Right case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right-Left case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node; // Return the (unchanged) node pointer
    }

    // In-order traversal to print the tree
    public void inOrder() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.key + " ");
            inOrderTraversal(node.right);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        // Insert nodes
        int[] keys = {10, 20, 30, 40, 50};
        for (int key : keys) {
            tree.insert(key);
        }

        // Print in-order traversal of the AVL tree
        System.out.println("In-order traversal of the AVL tree:");
        tree.inOrder();
    }
}
