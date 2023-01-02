package org.example.node;

public class Node {
    public Integer value;
    public Node left;
    public Node right;

    private static boolean isNodeExist(Node node) {
        return node != null && node.value != null;
    }

    //root
    private static void createNode(Node node, int value) {
        node.left = new Node();
        node.right = new Node();
        node.value = value;
    }

    @Override
    public String toString() {
        return "Value=" + this.value +
                ", Left=" + this.left.value +
                ", Right=" + this.right.value;
    }

    private static void insert(Node node, int value) {
        if (!isNodeExist(node)) {
            createNode(node, value);
        } else if (value < node.value) {
            insert(node.left, value);
        } else {
            insert(node.right, value);
        }
    }

    private static Node search(Node node, int value) {
        if (!isNodeExist(node)) {
            return null;
        }
        if (value == node.value) return node;

        if (value < node.value) return search(node.left, value);

        return search(node.right, value);
    }

    private static Node getMin(Node node) {
        if (!isNodeExist(node)) {
            return null;
        }
        if ((!isNodeExist(node.left))) {
            return node;
        }
        return getMin(node.left);
    }

    private static Node getMax(Node node) {
        if (!isNodeExist(node)) {
            return null;
        }
        if (!isNodeExist(node.right)) {
            return node;
        }
        return getMax(node.right);
    }

    private static void inOrderTraversal(Node node) {
        if (!isNodeExist(node)) {
            return;
        }
        inOrderTraversal(node.left);
        System.out.println("\n[ " + node + " ]\n");
        inOrderTraversal(node.right);
    }

    private static void postOrderTraversal(Node node) {
        if (!isNodeExist(node)) {
            return;
        }
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.println("[ " + node + " ]");
    }

    private static void directOrderTraversal(Node node) {
        if (!isNodeExist(node)) {
            return;
        }
        System.out.println("[ " + node + " ]");
        directOrderTraversal(node.left);
        directOrderTraversal(node.right);
    }

    private static int getChildrenCount(Node node) {
        if (!isNodeExist(node)) {
            return -1;
        }
        int count = 0;
        if (isNodeExist(node.left)) count++;
        if (isNodeExist(node.right)) count++;
        return count;
    }

    private static Node getChildOrNull(Node node) {
        if (isNodeExist(node.left)) return node.left;
        if (isNodeExist(node.right)) return node.right;
        return null;
    }


    private static Node searchParent(Node node, int value) {
        if (!isNodeExist(node)) {
            return null;
        }
        if (node.value == value) {
            return null;
        }

        if (isNodeExist(node.left) && node.left.value == value || isNodeExist(node.right) && node.right.value == value)
            return node;

        if (isNodeExist(node.left) && value < node.value) return searchParent(node.left, value);

        if (isNodeExist(node.right) && value > node.value) return searchParent(node.right, value);

        return null;
    }

    private static boolean remove(Node root, final int value) {
        if (root == null) return false;

        Node parentOfTheDeleteNode = searchParent(root, value);

        Node current_root = root;

        while (current_root.value != value) {
            if (current_root.value < value) {
                current_root = current_root.right;
            } else current_root = current_root.left;
        }
        // если удаляемый узел не имеет потомков
        if (current_root.left.value == null && current_root.right.value == null) {
            current_root.value = null;

            // если удаляемый узел имеет двух потомков
        } else if (isNodeExist(current_root.left) && isNodeExist(current_root.right)) {
            Node min = getMin(current_root.right);
            int minValue = min.value;
            System.out.println("Save value " + minValue);

            remove(root, minValue);
            current_root.value = minValue;

            // удаляемый узел имеет одного потомка
        } else {
            Node child = (isNodeExist(current_root.left)) ? current_root.left : current_root.right;

            if (current_root != root) {
                if (current_root == parentOfTheDeleteNode.left) {
                    parentOfTheDeleteNode.left = child;
                } else parentOfTheDeleteNode.right = child;
            } else root = child;
        }
        return true;
    }


    public static void main(String[] args) {
        Integer[] digit = {9, 2, 5, 13, 6, 10, 14, 7, 33, 44, 3};
        Node node = new Node();
        createNode(node, 9);
        for (int i = 1; i < digit.length; i++) {
            insert(node, digit[i]);
        }
//        inOrderTraversal(node);
        directOrderTraversal(node);
//        postOrderTraversal(node);

//        System.out.println(search(node, 7));
//        System.out.println(getChildrenCount(node));
//        System.out.println("Parent for 2 " + searchParent(node, 2));
        System.out.println(remove(node, 9));
    }
}
