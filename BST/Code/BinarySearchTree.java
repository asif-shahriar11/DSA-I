public class BinarySearchTree {
    private Node<Integer> root;

    // constructor
    public BinarySearchTree() {
        this.root = null;
    }

    /** insert */
    public void insertItem(int item) {
        root = insertUtil(root, item);
    }

    private Node<Integer> insertUtil(Node<Integer> root, int item) {

        // if tree is empty
        if(root == null)    return new Node<>(item);
        // if tree is not empty
        int rootValue = root.getValue();
        if(item<rootValue)   {
            root.setLeftChild(insertUtil(root.getLeftChild(), item));
            root.getLeftChild().setParent(root);
        }
        else if(item>rootValue)  {
            root.setRightChild(insertUtil(root.getRightChild(), item));
            root.getRightChild().setParent(root);
        }
        else System.out.println("Item already exists");

        return root;
    }

    /** search */
    public void searchItem(int item) {
        Node<Integer> temp = searchUtil(root, item);
        if(temp == null) System.out.println(item+" has not been found");
        else System.out.println(item+" has been found");
    }

    private Node<Integer> searchUtil(Node<Integer> root, int item) {
        if(root == null)    return null;
        int rootValue = root.getValue();
        if(rootValue == item)   return root;
        else if(item<rootValue) return searchUtil(root.getLeftChild(), item);
        else    return searchUtil(root.getRightChild(), item);
    }

    /** delete */
    public void deleteItem(int item) {
        Node<Integer> node = searchUtil(root, item);
        if(node == null) System.out.println("Item not found.");
        else {
            deleteUtil(node);
            System.out.println(item + " deleted.");
        }
    }

    private void deleteUtil(Node<Integer> node) {
        // leaf
        if(node.getLeftChild()==null && node.getRightChild()==null) {
            if(node.equals(node.getParent().getLeftChild()))  node.getParent().setLeftChild(null);    // leaf is left child of parent
            else    node.getParent().setRightChild(null);   // leaf is right child
        }
        // full node
        else if(node.getLeftChild()!=null && node.getRightChild()!=null) {
            Node<Integer> rightMin = getMinUtil(node.getRightChild());
            node.setValue(rightMin.getValue());
            deleteUtil(rightMin);
        }
        // node with single child
        else {
            Node<Integer> childNode;
            if(node.getLeftChild()!=null)   childNode = node.getLeftChild();
            else childNode = node.getRightChild();
            if(node.equals(node.getParent().getLeftChild()))   node.getParent().setLeftChild(childNode);
            else node.getParent().setRightChild(childNode);
        }
    }

    /** in order successor */
    public int getInOrderSuccessor(int item) throws Exception {
        Node<Integer> node = searchUtil(root, item);
        if(node == null)    throw new Exception(item +" does not exist.");
        return inOrderSuccessorUtil(node);
    }

    private int inOrderSuccessorUtil(Node<Integer> node) {
        if(node.getRightChild()!=null)   {
            Node<Integer> successor = getMinUtil(node.getRightChild());
            return successor.getValue();
        }
        else return Integer.MAX_VALUE;
    }

    /** in order predecessor */
    public int getInOrderPredecessor(int item) throws Exception {
        Node<Integer> node = searchUtil(root, item);
        if(node == null)    throw new Exception(item+" does not exist.");
        return inOrderPredecessorUtil(node);
    }

    private int inOrderPredecessorUtil(Node<Integer> node) {
        if(node.getLeftChild()!=null)  {
            Node<Integer> predecessor = getMaxUtil(node.getLeftChild());
            return predecessor.getValue();
        }
        else return Integer.MIN_VALUE;
    }

    /** item depth */
    public int getItemDepth(int item) { return itemDepthUtil(root, item, 0); }

    private int itemDepthUtil(Node<Integer> root, int item, int depth) {
        if(root == null)    return -1;
        int rootValue = root.getValue();
        if(rootValue == item) return depth;
        else if(item < rootValue)   return itemDepthUtil(root.getLeftChild(), item, depth+1);
        else    return itemDepthUtil(root.getRightChild(), item, depth+1);
    }

    /** maximum item */
    public int getMaxItem() { return getMaxUtil(root).getValue(); }

    private Node<Integer> getMaxUtil(Node<Integer> root) {
        while(root.getRightChild()!=null)   root = root.getRightChild();
        return root;
    }

    /** minimum item */
    public int getMinItem() { return getMinUtil(root).getValue(); }

    private Node<Integer> getMinUtil(Node<Integer> root) {
        while(root.getLeftChild()!=null)   root = root.getLeftChild();
        return root;
    }

    /** bst height */
    public int getHeight() { return heightUtil(root)-1; }

    private int heightUtil(Node<Integer> root) {
        if(root == null)    return 0;
        int leftHeight = heightUtil(root.getLeftChild());
        int rightHeight = heightUtil(root.getRightChild());
        return (leftHeight > rightHeight) ? (leftHeight+1) : (rightHeight+1);
    }

    /** bst size */
    public int getSize() { return sizeUtil(root); }

    private int sizeUtil(Node<Integer> root) {
        if(root == null)    return 0;
        return 1 + sizeUtil(root.getLeftChild()) + sizeUtil(root.getRightChild());
    }

    /** in order print */
    public void printInOrder() {
        System.out.print("In order traverse: ");
        //System.out.println(root.getValue());
        inOrderTraverse(root);
        System.out.println();
    }

    private void inOrderTraverse(Node<Integer> root) {
        if(root!=null) {
            inOrderTraverse(root.getLeftChild());
            System.out.print(root.getValue()+" ");
            inOrderTraverse(root.getRightChild());
        }
    }

    /** pre order print */
    public void printPreOrder() {
        System.out.print("Pre order traverse: ");
        preOrderTraverse(root);
        System.out.println();
    }

    private void preOrderTraverse(Node<Integer> root) {
        if(root!=null) {
            System.out.print(root.getValue()+" ");
            preOrderTraverse(root.getLeftChild());
            preOrderTraverse(root.getRightChild());
        }
    }

    /** post order print */
    public void printPostOrder() {
        System.out.print("Post order traverse: ");
        postOrderTraverse(root);
        System.out.println();
    }

    private void postOrderTraverse(Node<Integer> root) {
        if(root!=null) {
            postOrderTraverse(root.getLeftChild());
            postOrderTraverse(root.getRightChild());
            System.out.print(root.getValue()+" ");
        }
    }
}
