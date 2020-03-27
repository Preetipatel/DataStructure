package DataStructure;

public class BinarySearchTree extends BinaryTree {
  private Node root = null;

  public void add(int data) {
    root = add(root, data);
  }

  private Node add(Node root, int data) {
    if (root == null) return new Node(data);
    if (root.data >= data) root.left = add(root.left, data);
    else root.right = add(root.right, data);
    return root;
  }

  public void create(int[] a) {
    int i = 0;
    while (i < a.length) add(a[i++]);
  }

  public void display() {
    super.display(root);
  }

  public void display2DUtil() {
    super.display2DUtil(root, 2);
  }

  public Node getRoot() {
    return root;
  }

  public int getHeight() {
    return super.getHeight(root);
  }

  public void inOrderTraversal() {
    super.inOrderTraversal(root);
    super.inOrderIterative(root);
  }

  public Node getDeepestNode() {
    Node deepest = getDeepestNode(root);
    System.out.println("The deepest nodeData is " + deepest.data);
    return deepest;
  }

  protected Node getDeepestNode(Node root) {
    if (root == null) {
      System.out.println("Null Tree!!");
      return null;
    } else {
      Queue<Node> q = new Queue<>();
      q.inQueue(root);
      while (!q.isEmpty()) {
        root = q.deQueue();
        if (root.left != null) q.inQueue(root.left);
        if (root.right != null) q.inQueue(root.right);
      }
      return root;
    }
  }

  public int getMaxSumLevel() {
    return super.getMaxSumLevel(root);
  }

  public void MirrorOfTree() {
    root = MirrorOfTree(root);
  }

  public boolean isIdentical(Node anotherRoot) {
    return isIdentical(root, anotherRoot);
  }

  public void printAncestors() {
    System.out.println("The ancestors of the tree are: ");
    printAncestors(root);
    System.out.println(" ");
  }

  public void zigzagTraversal() {
    System.out.println("The zizag traversal of the tree is: ");
    zigzagTraversal(root);
    System.out.println(" ");
  }

  public void rightView() {
    System.out.println("The right view of the tree is: ");
    rightView(root);
    System.out.println(" ");
  }

  public int diametreOfTree() {
    return diametreOfTree(root);
  }

  public boolean isMirror(Node anotherRoot) {
    return isMirror(root, anotherRoot);
  }

  public boolean isSymmetric() {
    return isSymmetric(root);
  }

  public boolean search(Node root, int key) {
    if (root == null) return false;
    if (root.data == key) return true;
    if (root.data >= key) return search(root.left, key);
    return search(root.right, key);
  }

  public int findMax() {
    Node n = findMaxbst(root);
    return n.data;
  }

  private Node findMaxbst(Node root) {
    if (root == null) return null;
    while (root.right != null) root = root.right;
    return root;
  }

  public int findMin() {
    Node n = findMinbst(root);
    return n.data;
  }

  private Node findMinbst(Node root) {
    if (root == null) return null;
    while (root.left != null) root = root.left;
    return root;
  }

  public void delete(int key) {
    root  = delete(root, key);
  }

  public Node delete(Node root, int key) {
    if (root == null) {
      return root;
    }
    if (root.data > key) root.left = delete(root.left, key);
    if (root.data < key) root.right = delete(root.right, key);
    else {
      if (root.left == null && root.right == null) {
        root = null;
        return root;
      }
      if(root.left == null)
        return root.right;
      if(root.right == null)
        return root.left;

      else {
        Node temp = findMaxbst(root.left);
        root.data = temp.data;
        root.left = delete(root.left, temp.data);
      }
    }
    return root;
  }
}
