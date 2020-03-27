package DataStructure;

import java.util.HashSet;
import java.util.Set;



public class LinkedList {
  class Node {
    Integer data;
    Node next;

    Node(int d) {
      data = d;
      next = null;
    }
  }
  private Node head = null;
  public int loopLength = 0;
  public Integer dataAtLoopStart = null;
  private boolean loopExists = false;
  public String everOrOdd = null;
  public Node middle = null;

  public Node headOfLinkedList() {
    return head;
  }

  public int add(int d) {
    Node newNode = new Node(d);
    if (head == null) {
      head = newNode;
    } else {
      Node temp = head;
      while (temp.next != null) temp = temp.next;
      temp.next = newNode;
    }
    return head.data;
  }

  public int addAtStart(int d) {
    Node newNode = new Node(d);
    if (head == null) {
      head = newNode;
    } else {
      newNode.next = head;
      head = newNode;
    }
    return head.data;
  }

  public int deleteAtStart() {
    if (head == null) {
      System.out.printf("Empty Linked list");
      return Integer.parseInt(null);
    } else {
      int d = head.data;
      d = head.data;
      head = head.next;
      return d;
    }
  }

  public int deleteAtLast() {
    int d;
    if (head == null) {
      System.out.printf("Empty Linked list");
      return Integer.parseInt(null);
    } else if (head.next == null) {
      d = head.data;
      head = null;
      return d;
    } else {
      Node temp1 = head;
      Node temp2 = head.next;
      while (temp2.next != null) {
        temp1 = temp1.next;
        temp2 = temp2.next;
      }
      d = temp2.data;
      temp1.next = null;
    }
    return d;
  }

  public void display() {
    Node temp = head;
    System.out.println("The List is: ");
    while (temp != null) {
      System.out.print(temp.data + " -> ");
      temp = temp.next;
    }
    System.out.println("Null");
  }

  public void reverseDisplay(Node head) {
    if (head != null) {
      Node temp = head;
      if (temp.next != null) reverseDisplay(temp.next);
      System.out.print(temp.data + "<- ");
    }
  }

  public void deleteDuplicate() {
    Set track = new HashSet();
    Node prev = head;
    Node temp = prev.next;
    while (temp != null) {
      if (track.contains(temp.data)) {
        if (temp.next != null) prev.next = temp.next;
        else prev.next = null;
      } else {
        track.add(temp.data);
        prev = prev.next;
      }
      temp = temp.next;
    }
  }

  public Integer getMiddleNode() {
    if (head == null) return null;
    Node temp1 = head;
    Node temp2 = head;
    while (temp1.next != null) {
      temp1 = temp1.next.next;
      if (temp1 == null) break; // to handle even number of nodes
      temp2 = temp2.next;
    }
    middle = temp2;
    return temp2.data;
  }

  public void create(int[] array) {
    int size = array.length;
    int i = 0;
    while (i < size) {
      add(array[i++]);
    }
  }

  public void deleteList() {
    // In java its easy to delete a linked list bc of automatic garbage collection will take care
    // but in C++ we have to free() every pointer of linked list by iterating through the LL
    head = null;
  }

  public void reverse() {
    Node prev = null;
    Node curr = head;
    Node next = null;
    while (curr != null) {
      next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }
    head = prev;
  }

  public void add1ToLinkedList() {
    if (head != null) {
      reverse();
      Node temp = head;
      int carry = 1;
      while (temp != null) {
        if (temp.data + carry == 10) {
          temp.data = 0;
          carry = 1;
        } else {
          temp.data += carry;
          carry = 0;
        }
        temp = temp.next;
      }
      if (carry == 1) {
        add(1);
      }
      reverse();
    }
  }

  public void append(Node head1, Node head2) {
    if (head1 != null) {
      while (head1.next != null) {
        head1 = head1.next;
      }
      head1.next = head2;
    }
  }

  public void createLoop() {
    if (head != null) {
      Node temp = head;
      while (temp.next != null) temp = temp.next;
      temp.next = head.next;
    }
  }

  /**
   * detect loop in linked list and if loop exists find the length of loop and the also give the
   * data at starting node of loop.
   *
   * @return
   */
  public boolean loopDetection() {
    if (head != null) {
      Node fast = head.next;
      Node slow = head;
      // wrogn approach (increment fast by one,compare fast and slow if not equal then again
      // increment fast again and compare if not equal then now increment slow.
      /*while (fast != null && fast != slow) {
        if (fast.next == null) break;
        else if (fast.next.next == null) break;
        else {
          fast = fast.next.next;
          slow = slow.next;
        }
      }
      if(fast == slow) loopExists = true;*/

      // correct approach
      while (fast != null && slow != null) {
        fast = fast.next;
        if (fast == slow) {
          loopExists = true;
          break;
        }
        if (fast == null) {
          loopExists = false;
          break;
        }
        fast = fast.next;
        if (fast == slow) {
          loopExists = true;
          break;
        }
        if (fast == null) {
          loopExists = false;
          break;
        }
        slow = slow.next;
      }
      System.out.println("fast data " + fast.data);
      // dataAtLoopStart = slow.data;
      if (loopExists) {
        loopLength = 1;
        slow = slow.next;
        while (slow != fast) {
          slow = slow.next;
          loopLength++;
        }
        slow = head;
        /*System.out.println(fast.data);
        while(slow != fast){
          slow = slow.next;
          fast = fast.next;
        }
        dataAtLoopStart = slow.data;*/
      }
    }
    return loopExists;
  }

  public String evenOrOdd() {
    String ans = "";
    if (head == null) ans = "Even";
    else {
      Node temp = head;
      while (temp.next != null && temp.next.next != null) {
        temp = temp.next.next;
      }
      if (temp.next == null) ans = "Odd";
      else if (temp.next.next == null) ans = "Even";
    }
    return ans;
  }

  public void reverseAfterMid() {
    if (head != null) {
      Node temp = head;
      Node prev = null;
      Node curr = middle.next;
      Node next = null;
      while (curr != null) {
        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
      }
      middle.next = prev;
    }
  }

  public boolean isPallindrome() {
    boolean check = true;
    if (head != null) {
      reverseAfterMid();
      Node temp = head;
      Node temp2 = middle.next;
      {
        while (temp2 != null) {
          if (temp.data != temp2.data) {
            check = false;
            break;
          }
          temp = temp.next;
          temp2 = temp2.next;
        }
      }
    }
    reverseAfterMid();
    return check;
  }

  private Node getKthNode(int k, Node head) {
    if (head == null) return null;
    Node temp = head;
    while (k > 0 && temp != null) {
      temp = temp.next;
      k--;
    }
    if (temp == null) return null;
    else return temp;
  }

  private boolean hasKnodes(int k, Node head) {
    Node temp = head;
    while (temp != null && k > 0) {
      temp = temp.next;
      k--;
    }
    if (temp == null) return false;
    else return true;
  }

  public void reverseInGroup(Integer k) {
    Node curr = head, prev = null, next = null, newHead, nextGroup = null;
    newHead = getKthNode(k - 1, head);
    if (newHead == null) {
      System.out.println("Not enough Node");
      return;
    }
    nextGroup = getKthNode(k, head);
    while (nextGroup != null) {

      // checking wheather k more nodes are remaining or not
      if (!hasKnodes(k - 1, nextGroup))
        prev =
            nextGroup; // if this is the last group with sufficient no. of nodes then it need not to
                       // be reversed
      else
        prev =
            getKthNode(
                k - 1,
                nextGroup); // if not then it will also get reversed and in that case prev shoul
                            // point to last node of this group only

      // Reversing a Group
      while (curr != nextGroup) {
        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
      }
      nextGroup = getKthNode(k, curr);
    }
    // handling data of last group when there is still k no of nodes remaining to be reversed
    prev = null;
    if (curr != null
        && hasKnodes(
            k - 1,
            curr)) // hasKnodes(k-1, curr) checks that there is still k no of nodes remaining to be
                   // reversed and if not the remainng will not be reversed
    while (curr != null) {
        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
      }
    // if(curr.next != null)
    head = newHead;
  }

  private void covertToCircularLL() {
    if (head != null) {
      Node temp = head;
      while (temp.next != null) temp = temp.next;
      temp.next = head;
    }
  }

  public int getTheLeader(int m) {
    covertToCircularLL();
    Node temp = head;
    Node prev = null;
    int count = m - 1;
    while (temp.next != temp) {
      while (count > 0) {
        prev = temp;
        temp = temp.next;
        count--;
      }
      prev.next = temp.next;
      temp =temp.next;
      count = m - 1;
    }
    return temp.data;
  }
}
