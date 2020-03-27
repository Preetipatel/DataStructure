package DataStructure;

import java.util.HashMap;

public class BinaryTree {
  class Node {
    Integer data;
    Node right;
    Node left;

    Node(int d) {
      data = d;
      right = null;
      left = null;
    }
  }

  public Node root;

  public Node getRoot() {
    return root;
  }

  // level order insertion
  public void add(int d) {
    if (root == null) {
      root = new Node(d);
      return;
    }
    Queue<Node> q = new Queue<>();
    q.inQueue(root);
    Node temp = null;
    while (!q.isEmpty()) {
      temp = q.deQueue();
      if (temp.left == null) {
        temp.left = new Node(d);
        break;
      } else q.inQueue(temp.left);
      if (temp.right == null) {
        temp.right = new Node(d);
        break;
      } else q.inQueue(temp.right);
    }
  }

  public void create(int a[]) {
    int i = 0;
    while (i < a.length) {
      add(a[i]);
      i++;
    }
  }

  // leveel order display
  public void display() {
    display(root);
  }

  protected void display(Node root) {
    if (root != null) {
      System.out.println("The level order display of tree is: ");
      Queue<Node> q = new Queue<>();
      q.inQueue(root);
      while (!q.isEmpty()) {
        Node temp = q.deQueue();
        System.out.print(temp.data + " ");
        if (temp.left != null) q.inQueue(temp.left);
        if (temp.right != null) q.inQueue(temp.right);
      }
    }
    System.out.println("");
  }

  public void reverseDisplay() {
    if (root != null) {
      System.out.println("The reverse level order display of tree is: ");
      Queue<Node> q = new Queue<>();
      Stack<Integer> st = new Stack<>();
      q.inQueue(root);
      while (!q.isEmpty()) {
        Node temp = q.deQueue();
        st.push(temp.data);
        if (temp.left != null) q.inQueue(temp.left);
        if (temp.right != null) q.inQueue(temp.right);
      }
      while (!st.isEmpty()) System.out.print(st.pop() + " ");
    }
  }

  // Function to print binary tree in 2D
  // It does reverse inorder traversal
  public void display2DUtil() {
    display2DUtil(root, 2);
  }

  protected void display2DUtil(Node root, int space) {
    final int COUNT = 10;
    // Base case
    if (root == null) return;

    // Increase distance between levels
    space += COUNT;

    // Process right child first
    display2DUtil(root.right, space);

    // Print current node after space
    // count
    System.out.print("\n");
    for (int i = COUNT; i < space; i++) System.out.print(" ");
    System.out.print(root.data + "\n");

    // Process left child
    display2DUtil(root.left, space);
  }

  public int getHeight() {
    if (root != null) {
      System.out.print("Height of the (from iteration): " + getHeightIterative(root) + "\n");
      return getHeight(root);
    } else {
      System.out.println("Null tree!!");
      return -1;
    }
  }

  protected int getHeightIterative(Node temp) {
    int height = 1;
    Queue<Node> q = new Queue<>();

    // inserting nodes of first level i e only root
    q.inQueue(temp);

    // inserting null after end of the first level as a marker
    q.inQueue(null);

    while (!q.isEmpty()) {
      temp = q.deQueue();
      if (temp == null) {
        if (q.isEmpty()) break;
        q.inQueue(null);
        height++;
      } else {
        if (temp.left != null) q.inQueue(temp.left);
        if (temp.right != null) q.inQueue((temp.right));
      }
    }
    return height;
  }

  protected int getHeight(Node target) {
    if (target == null) return 0;
    return Math.max(getHeight(target.left), getHeight(target.right)) + 1;
  }

  public int getDepth() {
    return getDepth(root);
  }

  protected int getDepth(Node target) {
    if (root == null) return 0;
    {
      int depth = getHeight(root) - getHeight(target);
      return depth;
    }
  }

  public void inOrderTraversal() {
    inOrderTraversal(root);
    System.out.println("\n Iterative: ");
    inOrderIterative(root);
  }

  protected void inOrderTraversal(Node root) {
    if (root == null) return;
    inOrderTraversal(root.left);
    System.out.print(root.data + " ");
    inOrderTraversal(root.right);
  }

  protected void inOrderIterative(Node root) {
    Stack<Node> s = new Stack<>();
    while (true) {
      while (root != null) {
        s.push(root);
        root = root.left;
      }
      if (s.isEmpty()) break;
      else {
        root = s.pop();
        System.out.print(root.data + " ");
        root = root.right;
      }
    }
  }

  public void postOrderTraversal() {
    postOrderTraversal(root);
  }

  protected void postOrderTraversal(Node root) {
    if (root == null) return;
    postOrderTraversal(root.left);
    postOrderTraversal(root.right);
    System.out.print(root.data + " ");
  }

  protected void postOrderIterative(Node root) {
    Stack<Node> s = new Stack<>();
    while (true) {
      while (root != null) {
        s.push(root);
        root = root.left;
      }
      if (!s.isEmpty()) break;
      else {
        root = s.pop();
        System.out.print(root.data + " ");
      }
    }
  }

  public void preOrderTraversal() {
    preOrderTraversal(root);
    System.out.println("\n Iterative: ");
    preOrderIterative(root);
  }

  protected void preOrderTraversal(Node root) {
    if (root == null) return;
    System.out.print(root.data + " ");
    preOrderTraversal(root.left);
    preOrderTraversal(root.right);
  }

  protected void preOrderIterative(Node root) {
    Stack<Node> s = new Stack<>();
    while (true) {
      while (root != null) {
        System.out.print(root.data + " ");
        s.push(root);
        root = root.left;
      }
      if (s.isEmpty()) break;
      else root = s.pop().right;
    }
  }

  public int findMax() {
    return findMax(root);
  }

  protected int findMax(Node root) {
    int max = -2147483648;
    if (root != null) {
      int lmax = findMax(root.left);
      int rmax = findMax(root.right);
      if (lmax > rmax) max = lmax;
      else max = rmax;
      if (root.data > max) max = root.data;
    }
    return max;
  }

  public boolean search(int key) {
    return search(root, key);
  }

  protected boolean search(Node root, int key) {
    // boolean result = false;
    if (root == null) return false;
    else if (root.data == key) return true;
    else if (search(root.left, key) == true) return true;
    else return search(root.right, key);
  }

  public Integer getLeafCount() {
    if (root != null) {
      return getLeafCount(root);
    } else {
      System.out.println("Null  Tree !! ");
      return null;
    }
  }

  protected Integer getLeafCount(Node root) {
    int count = 0;
    Queue<Node> q = new Queue<>();
    q.inQueue(root);
    while (!q.isEmpty()) {
      root = q.deQueue();
      if (root.left != null) q.inQueue(root.left);
      if (root.right != null) q.inQueue(root.right);
      else if (root.right == null && root.left == null) count++;
    }
    return count;
  }

  public int getMaxSumLevel() {
    return getMaxSumLevel(root);
  }

  protected int getMaxSumLevel(Node root) {
    if (root == null) {
      System.out.println("Null Tree!!");
      return -1;
    } else {
      Queue<Node> q = new Queue<>();
      q.inQueue(root);
      q.inQueue(null);
      int level = 1;
      int maxLevel = 0;
      int maxSum = Integer.MIN_VALUE;

      while (!q.isEmpty()) {
        root = q.deQueue();
        int levelSum = 0;
        while (root != null && !q.isEmpty()) {
          levelSum += root.data;
          if (root.left != null) q.inQueue(root.left);
          if (root.right != null) q.inQueue(root.right);
          root = q.deQueue();
        }
        if (root == null) {
          if (maxSum < levelSum) {
            maxSum = levelSum;
            maxLevel = level;
          }
          if (!q.isEmpty()) q.inQueue(null);
          level++;
        }
      }
      System.out.println("The maximum sum is: " + maxSum);
      return maxLevel;
    }
  }

  public boolean isPathWithTheSumExists(int sum) {
    return isPathWithTheSumExists(root, sum);
  }

  private boolean isPathWithTheSumExists(Node root, int sum) {
    if (root == null) return false;
    if (root.data == sum) return true;
    else {
      boolean result = isPathWithTheSumExists(root.left, (sum - root.data));
      if (!result) result = isPathWithTheSumExists(root.right, sum - root.data);
      return result;
    }
  }

  public void MirrorOfTree() {
    root = MirrorOfTree(root);
  }

  protected Node MirrorOfTree(Node root) {
    if (root == null || (root.left == null && root.right == null)) return null;
    {
      Node temp = root.left;
      root.left = root.right;
      root.right = temp;
      MirrorOfTree(root.left);
      MirrorOfTree(root.right);
    }
    return root;
  }

  public boolean isIdentical(Node anotherRoot) {
    return isIdentical(root, anotherRoot);
  }

  protected boolean isIdentical(Node root1, Node root2) {
    boolean left = false;
    boolean right = false;
    if (root1 == null && root2 == null) return true;
    if ((root1 == null && root2 != null) || (root1 != null && root2 == null)) return false;
    if (root1.data == root2.data) {
      left = isIdentical(root1.left, root2.left);
      if (left == false) return false;
      right = isIdentical(root1.right, root2.right);
      if (right == false) return false;
      else return true;
    } else return false;
  }

  public boolean isMirror(Node anotherRoot) {
    return isMirror(root, anotherRoot);
  }

  protected boolean isMirror(Node root1, Node root2) {

    if (root1 == null && root2 == null) return true;
    if(root1 == null || root2 == null) return false;

    return (isMirror(root1.left, root2.right) && isMirror(root1.right, root2.left));
  }
   public boolean isSymmetric(){
    return isSymmetric(root );
   }
   protected boolean isSymmetric(Node root){
    if(root == null) return true;
    return isMirror(root.right, root.left);
   }

  public void printAncestors() {
    System.out.println("The ancestors of the tree are: ");
    printAncestors(getRoot());
    System.out.println(" ");
  }

  protected void printAncestors(Node root) {
    if (root == null) return;
    if (root.left != null) {
      System.out.print(root.data + " ");
      printAncestors(root.left);
    }
    if (root.right != null) printAncestors(root.right);
  }

  HashMap<Integer, Integer> map = new HashMap<>();

  public void verticalColumnSum() {
    verticalColumnSum(root, 0);
    System.out.println("The vertical sum of the tree is: ");
    // Array<> keySet = map.keySet().toArray();

    for (int col : map.keySet()) {
      System.out.print(map.get(col) + " ");
    }
  }

  protected void verticalColumnSum(Node root, int col) {
    if (root != null) {
      verticalColumnSum(root.left, col - 1);

      if (map.containsKey(col)) {
        // int oldValue = map.get(col);
        map.put(col, map.get(col) + root.data);
      } else map.put(col, root.data);

      verticalColumnSum(root.right, col + 1);
    }
  }

  public void zigzagTraversal() {
    System.out.println("The zizag traversal of the tree is: ");
    zigzagTraversal(root);
    System.out.println(" ");
  }

  protected void zigzagTraversal(Node root) {
    Stack<Node> current = new Stack<>();
    Stack<Node> next = new Stack<>();
    Stack<Node> temp = new Stack<>();
    boolean l2r = true;
    current.push(root);

    while (true) {
      while (!current.isEmpty()) {
        root = current.pop();
        System.out.print(" " + root.data);
        if (l2r) {
          if (root.left != null) next.push(root.left);
          if (root.right != null) next.push(root.right);
        } else if (!l2r) {
          if (root.right != null) next.push(root.right);
          if (root.left != null) next.push(root.left);
        }
      }
      if (next.isEmpty()) break;
      l2r = !l2r;
      temp = current;
      current = next;
      next = temp;
    }
  }

  public void LCA(int a, int b) {
    if (LCA(root, a, b) == null) {
      System.out.println("Either the tree is Empty of the given node doesnt exist!!");
    } else System.out.println("The lca of the " + a + " and " + b + " is: " + LCA(root, a, b).data);
  }

  protected Node LCA(Node root, int a, int b) {
    if (root == null) return null;
    if (root.data == a || root.data == b) return root;
    else {
      Node left = LCA(root.left, a, b);
      Node right = LCA(root.right, a, b);
      if (left != null && right != null) return root;
      else return (left != null ? left : right);
    }
  }

  public void rightView() {
    System.out.println("The right view of the tree is: ");
    rightView(root);
    System.out.println(" ");
  }

  protected void rightView(Node root) {
    Queue<Node> q = new Queue<>();
    q.inQueue(root);
    q.inQueue(null);
    Node prev = root;
    while (!q.isEmpty()) {
      root = q.deQueue();
      if (root == null) {
        System.out.print(" " + prev.data);
        if (q.isEmpty()) break;
        else q.inQueue(null);
      } else {
        if (root.left != null) q.inQueue(root.left);
        if (root.right != null) q.inQueue(root.right);
      }
      prev = root;
    }
  }

  HashMap<Integer, Integer> top = new HashMap<>();

  public void topView() {
    System.out.println("The top view of the tree is: ");
    topView(root, 0);
    for (int col : top.keySet()) {
      System.out.print(top.get(col) + " ");
    }
    top.clear();
  }

  protected void topView(Node root, int col) {
    if (root == null) return;
    else {
      // if (!top.containsKey(col))
      top.put(col, root.data);

      topView(root.left, col - 1);
      topView(root.right, col + 1);
    }
  }

  public boolean isSumTree() {
    return isSumTree(root);
  }

  public int sumOfTree(Node root) {
    if (root == null) return 0;
    int leftSum = sumOfTree(root.left);
    int rightSum = sumOfTree(root.right);
    return leftSum + rightSum + root.data;
  }

  protected boolean isSumTree(Node root) {
    if (root == null) return true;
    // for handling leaf node without this condition will not satisfy for leaf node
    if (root.left == null && root.right == null) return true;
    if (isSumTree(root.left) && isSumTree(root.right)) {
      int leftSum = sumOfTree(root.left);
      int rightSum = sumOfTree(root.right);
      if (root.data == leftSum + rightSum) return true;
    }
    return false;
  }

  public int diametreOfTree() {
    return diametreOfTree(root);
  }

  protected int diametreOfTree(Node root) {
    if (root == null) return 0;
    if (root.left == null && root.right == null) return 0;
    int leftD = diametreOfTree(root.left);
    int rightD = diametreOfTree(root.right);
    // if(root.left==null || root.right==null) return 1;
    // System.out.println("sub tree height: "+getHeight(root.left)+ " "+ getHeight(root.right) );
    // return Math.max(Math.max(diametreOfTree(root.left), diametreOfTree(root.right)),
    // (getHeight(root.left)+getHeight(root.right)));
    return Math.max(Math.max(leftD, rightD), getHeight(root.left) + getHeight(root.right));
  }

  public static int pIndex = 0;

  public Node createTreeFromPreorderAndInorder(int[] preorder, int[] inorder) {

    Node root = createTreeFromPreorderAndInorder(preorder, inorder, 0, preorder.length);
    pIndex = 0;
    return root;
  }

  protected Node createTreeFromPreorderAndInorder(
      int[] preorder, int[] inorder, int iStart, int iEnd) {
    if (iStart >= iEnd) {
      System.out.println("break condition and decreasing preorderindex: ");
      pIndex = pIndex - 1;
      return null;
    }
    if (pIndex >= preorder.length) return null;
    {
      int inorderIndex = searchInArray(inorder, preorder[pIndex]);
      System.out.println("Index of the the preorder element in inorder is: " + inorderIndex);
      Node root = new Node(inorder[inorderIndex]);
      System.out.println("root created root.data is ***" + root.data);
      pIndex++;
      System.out.println("Current preorder index " + pIndex);
      root.left = createTreeFromPreorderAndInorder(preorder, inorder, iStart, inorderIndex);
      pIndex++;
      System.out.println("Current preorder index " + pIndex);
      root.right = createTreeFromPreorderAndInorder(preorder, inorder, inorderIndex + 1, iEnd);
      // pIndex++;
      return root;
    }
  }

  private int searchInArray(int[] arr, int key) {
    int i = 0;
    for (i = 0; i < arr.length; i++) {
      if (arr[i] == key) return i;
    }
    return -1;
  }
}
