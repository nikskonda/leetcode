package by.nikskonda.lc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class Main23 {

  public static void main(String[] args) throws Exception {
    System.out.println(mergeKLists(new ListNode[] {
        toListNode(new int[]{-8,-7,-7,-5,1,1,3,4}, 0),
        toListNode(new int[]{-2}, 0),
        toListNode(new int[]{-10,-10,-7,0,1,3}, 0),
        toListNode(new int[]{2}, 0)
    }));
    System.out.println(mergeKLists(new ListNode[] {
    }));
//    Random random = new Random();
//    for (int i = 0; i < 100; i++) {
////      int num1 = random.nextInt(1000000);
////      int num2 = random.nextInt(1000000);
//      int num1 = 99999;
//      int num2 = 999;
//      int expected = num1 + num2;
//
//      ListNode listNode1 = toListNode(num1);
//      ListNode listNode2 = toListNode(num2);
//      ListNode result = addTwoNumbers(listNode1, listNode2);
//      ListNode temp = result.next;
//      int res = result.val;
//      while (temp != null) {
//        res=res + temp.val*10;
//        temp = temp.next;
//      }
//      System.out.println("---");
//      System.out.println(listNode1);
//      System.out.println(listNode2);
//      System.out.println(result);
//      if (res != expected) {
//        System.out.println("ERROR");
//        System.out.println(expected);
//      }
//    }

  }

  public static ListNode mergeKLists(ListNode[] lists) {
    List<ListNode> list = new LinkedList<>(
        Arrays.stream(lists)
            .filter(Objects::nonNull)
            .sorted(Comparator.comparing(listNode -> listNode.val))
            .toList()
    );
    if (list.isEmpty()) return null;


    return findNext(list);
  }

  public static ListNode findNext(List<ListNode> list) {
    ListNode minNode = list.get(0);
    if (list.size() == 1) return minNode;

    int value = minNode.val;
    if (minNode.next == null) {
      list.remove(0);
      return new ListNode(value, findNext(list));
    }

    int nextVal = minNode.next.val;

    if (nextVal <= list.get(1).val) {
      list.set(0, minNode.next);
    } else if (nextVal >= list.get(list.size()-1).val) {
      list.add(minNode.next);
      list.remove(0);
    } else {
      int index = binarySearch(1, list.size()-1, nextVal, list);
      list.add(index + 1, minNode.next);
      list.remove(0);
    }
    return new ListNode(value, findNext(list));
  }

  private static int binarySearch(int start, int end, int value, List<ListNode> list) {
    if (list.get(start).val > value) return start - 1;
    if (list.get(end).val < value) return end;

    if (start == end){
      return start;
    }

    int mid = (start + end)/2;
    if (list.get(mid).val == value) return mid;

    if (list.get(mid).val > value) {
      return binarySearch(start, mid-1, value, list);
    } else {
      return binarySearch(mid+1, end, value, list);
    }
  }


  public static ListNode mergeKLists2(ListNode[] lists) {
    return findNextMin(lists);
  }

  private static ListNode findNextMin(ListNode[] lists) {
    int index = -1;
    int minValue = Integer.MAX_VALUE;
    for (int i = 0; i < lists.length; i++) {
      if (lists[i] != null && lists[i].val < minValue) {
        minValue = lists[i].val;
        index = i;
      }
    }
    if (index == -1) {
      return null;
    }
    lists[index] = lists[index].next;
    return new ListNode(minValue, findNextMin(lists));
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
      return val + " -> "+ next;
    }
  }

  private static ListNode toListNode(int[] nums, int index) {
    if (index == nums.length) {
      return null;
    }
    return new ListNode(nums[index], toListNode(nums, index+1));
  }
}