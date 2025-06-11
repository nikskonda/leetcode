package by.nikskonda;

import java.util.List;
import java.util.concurrent.Callable;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC19 {


  public static void main(String[] args) throws Exception {

//    System.out.println(removeNthFromEnd(create(1, 2, 3, 4, 5, 6, 7), 1));
    System.out.println(removeNthFromEnd(create(1, 2), 2));
  }

  public static ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode coursor1 = head;
    int nodesFromEnd = 0;

    ListNode coursor2 = head;
    while (coursor2 != null) {
      if (nodesFromEnd == n + 1) {
        coursor1 = coursor1.next;
      } else {
        nodesFromEnd++;
      }
      coursor2 = coursor2.next;
    }
    if (coursor1.next == null) {
      return new ListNode();
    }
    if (nodesFromEnd == n) {
      return head.next;
    }
    coursor1.next = coursor1.next.next;
    return head;
  }

  private static ListNode create(int... list) {
    ListNode tail = new ListNode(list[list.length-1]);
    for (int i = list.length-2; i >= 0; i--) {
      int value = list[i];
      tail = new ListNode(value, tail);
    }
    return tail;
  }

  public static class ListNode {

    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }

    @Override
    public String toString() {
      ListNode coursor = this;
      StringBuilder stringBuilder = new StringBuilder("[");

      while (coursor != null) {
        stringBuilder.append(coursor.val).append(", ");
        coursor = coursor.next;
      }

      return stringBuilder.append("]").toString();
    }
  }

}