package by.nikskonda.lc;

import java.util.Random;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class Main2 {

  public static void main(String[] args) throws Exception {
    Random random = new Random();
    for (int i = 0; i < 100; i++) {
//      int num1 = random.nextInt(1000000);
//      int num2 = random.nextInt(1000000);
      int num1 = 99999;
      int num2 = 999;
      int expected = num1 + num2;

      ListNode listNode1 = toListNode(num1);
      ListNode listNode2 = toListNode(num2);
      ListNode result = addTwoNumbers(listNode1, listNode2);
      ListNode temp = result.next;
      int res = result.val;
      while (temp != null) {
        res=res + temp.val*10;
        temp = temp.next;
      }
      System.out.println("---");
      System.out.println(listNode1);
      System.out.println(listNode2);
      System.out.println(result);
      if (res != expected) {
        System.out.println("ERROR");
        System.out.println(expected);
      }
    }

  }

  private static ListNode toListNode(int num) {
    if (num == 0) {
      return null;
    }
    return new ListNode(num % 10, toListNode(num/10));
  }

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    return calcNext(l1, l2, false);
  }

  private static ListNode calcNext(ListNode l1, ListNode l2, boolean moreTen) {
    if (l1 == null && l2 == null) {
      if (moreTen) {
        return new ListNode(1);
      } else {
        return null;
      }
    }
    if (l1 == null) {
      if (!moreTen) return l2;
      return addNode(null, l2.next, l2.val+1);
    }
    if (l2 == null) {
      if (!moreTen) return l1;
      return addNode(l1.next, null, l1.val+1);
    }
    int value = l1.val + l2.val;
    return addNode(l1.next, l2.next, moreTen ? value + 1 : value);
  }

  private static ListNode addNode(ListNode next1, ListNode next2, int value) {
    if (value > 9) {
      return new ListNode(value - 10, calcNext(next1, next2, true));
    } else {
      return new ListNode(value, calcNext(next1, next2, false));
    }
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
      if (next == null) {
        return val+ "";
      }
      return val + "->"+ next;
    }
  }
}