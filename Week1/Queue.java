package Week1;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Week2.Queue: custom implementation
 * @author John Mortensen
 *
 * 1. Uses custom Week2.LinkedList of Generic type T
 * 2. Implements Iterable
 * 3. "has a" Week2.LinkedList for head and tail
 */
public class Queue<T> implements Iterable<T> {
  LinkedList<T> head, tail;
  
  /**
    *  Add a new object at the end of the Week2.Queue,
    *
    * @param  data,  is the data to be inserted in the Week2.Queue.
    */
  public void add(T data) {
    // add new object to end of Week2.Queue
    LinkedList<T> tail = new LinkedList<>(data, null);
    if (head == null)  // initial condition
      this.head = this.tail = tail;
    else {  // nodes in queue
      this.tail.setNextNode(tail); // current tail points to new tail
      this.tail = tail;  // update tail
    }
  }

  public void remove(T data) {
    LinkedList<T> tail = new LinkedList<>(data, null);
    if(head == null){
      throw new RuntimeException("Deque is empty");
    }

    if(head.getNext() == null){
      tail = null;
    }else{
      // previous of next node (new first) becomes null
      head.getNext().setPrevNode(tail);
    }
    head = head.getNext();
  }
  /**
    *  Returns the head object.
    *
    * @return  this.head, the head object in Week2.Queue.
    */
  public LinkedList<T> getHead() {
    return this.head;
  }

  /**
    *  Returns the tail object.
    *
    * @return  this.tail, the last object in Week2.Queue
    */
  public LinkedList<T> getTail() {
    return this.tail;
  }

  /**
    *  Returns the iterator object.
    *
    * @return  this, instance of object
    */
  public Iterator<T> iterator() {
    return new QueueIterator<>(this);
  }
}

/**
 * Week2.Queue Iterator
 *
 * 1. "has a" current reference in Week2.Queue
 * 2. supports iterable required methods for next that returns a data object
 */
class QueueIterator<T> implements Iterator<T> {
  LinkedList<T> current;  // current element in iteration

  // Week2.QueueIterator is intended to the head of the list for iteration
  public QueueIterator(Queue<T> q) {
    current = q.getHead();
  }

  // hasNext informs if next element exists
  public boolean hasNext() {
    return current != null;
  }

  // next returns data object and advances to next position in queue
  public T next() {
    T data = current.getData();
    current = current.getNext();
    return data;
  }
}

