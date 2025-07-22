package by.nikskonda.lc;

import java.util.LinkedList;
import java.util.Queue;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class Test1 {

  public static void main(String[] args) throws Exception {

  }

  class Node {
    Node le;
    Node ri;
    int val;
    /*
        3 <- count returns 6
       / \
      6   9 <- count returns 2
     / \   \
    3   4   5 <- count returns 1
    */
    public int count() {
      Queue<Node> queue = new LinkedList<>();
      queue.add(this);
      int count = 0;
      while (!queue.isEmpty()) {
        count++;
        Node current = queue.poll();
        if (current.le != null) {
          queue.add(current.le);
        }
        if (current.ri != null) {
          queue.add(current.ri);
        }
      }
      return count;
    }
  }
}