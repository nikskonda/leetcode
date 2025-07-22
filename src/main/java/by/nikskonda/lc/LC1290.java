package by.nikskonda.lc;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC1290 {

  public static void main(String[] args) throws Exception {
    System.out.println();
  }


  public static int getDecimalValue(ListNode head) {
    int result = 0;
    while (true) {
      if (head.val == 1) {
        result += 1;
      }

      if (head.next != null) {
        head = head.next;
        result = result << 1;
      } else {
        break;
      }
    }
    return result;
  }



public static class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

}