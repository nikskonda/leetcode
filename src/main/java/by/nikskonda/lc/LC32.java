package by.nikskonda.lc;

import java.util.Stack;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC32 {

  public static void main(String[] args) throws Exception {

//    System.out.println(longestValidParentheses2(")(()()))"));
//    System.out.println(longestValidParentheses2("((())()"));
    System.out.println(longestValidParentheses2(")))))(((()("));
//    System.out.println(longestValidParentheses2(")()((())()))(())("));
  }

  public static int longestValidParentheses2(String s) {
    if (s.length()<2) return 0;

    Stack<Integer> positions = new Stack<>();
    int max = 0;

    for (int i = 0; i < s.length(); i++) {
      boolean currentIsClosed = s.charAt(i) == ')';
      if (currentIsClosed) {
        if (positions.isEmpty()) {
          positions.add(i);
        } else {
          positions.pop();
          max = positions.isEmpty() ? i : Math.max(max, i - positions.peek());
        }
      } else {
        positions.push(i);
      }
    }
    return max;
  }

  public static int longestValidParentheses(String s) {
    if (s.length()<2) return 0;
    Node start = new Node(s.charAt(0) == '(', 0);
    Node pointer = start;
    for (int i = 1; i < s.length(); i++) {
      Node newNode = new Node(s.charAt(i) == '(', i);
      newNode.prev = pointer;
      pointer.next = newNode;
      pointer = newNode;
    }

    pointer = start;
    while (pointer != null) {
      if (!pointer.openBracket && pointer.prev != null && pointer.prev.openBracket) {
        Node temp = pointer.prev.prev;
        if (temp == null && pointer.next == null) {
          return s.length();
        }
        if (temp == null) {
          //change start
          pointer = pointer.next;
          pointer.prev = null;
          start = pointer;
        } else if (pointer.next == null) {
          //change end
          temp.next = new Node(true, pointer.position+1);
          break;
        } else {
          //swap
          pointer = pointer.next;
          temp.next = pointer;
          pointer.prev = temp;
        }
      } else {
        pointer = pointer.next;
      }
    }
    pointer = start;
    int max = pointer.position;
    while (pointer.next != null) {
      max = Math.max(max, pointer.next.position - pointer.position - 1);
      pointer = pointer.next;
    }
    return max;
  }

  public static class Node {

    boolean openBracket;
    int position;
    Node next;
    Node prev;

    public Node(boolean openBracket, int position) {
      this.openBracket = openBracket;
      this.position = position;
    }
  }
}