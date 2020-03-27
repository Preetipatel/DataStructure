package DataStructure;

import java.util.*;

public class Queue<T> {
  private ArrayList<T> q;
  int front = -1;
  int rear = -1;

  public Queue() {
    q = new ArrayList<T>();
  }

  public void inQueue(T t) {
    if (q.isEmpty()) front++;
    q.add(t);
    rear++;
  }

  public T deQueue() {
    if (q.isEmpty()) {
      System.out.println("Empty Queue!!");
      return null;
    } else {
      T deletedElement = q.remove(0);
      return deletedElement;
    }
  }

  public void display() {
    if (front == -1 || q.isEmpty()){
      System.out.println("Empty Queue!!!!");
      return;
    }
    else{
      System.out.println("The Queue is: ");
      for (int i = 0; i < q.size(); i++) {
        System.out.println(q.get(i));
      }
    }

  }
  public boolean isEmpty() {
    if (q.isEmpty()) return true;
    else return false;
  }

  public int size() {
    return q.size();
  }
}
