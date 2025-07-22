package by.nikskonda.lc;

import static org.junit.jupiter.api.Assertions.*;

import by.nikskonda.lc.LC1290.ListNode;
import org.junit.jupiter.api.Test;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-07-14
 */
class LC1290Test {

  @Test
  void testGetDecimalValueReturn0When0() {
    //when
    ListNode listNode = new ListNode(0);
    //then
    assertEquals(0, LC1290.getDecimalValue(listNode));
  }

  @Test
  void testGetDecimalValueReturn1When1() {
    //when
    ListNode listNode = new ListNode(1);
    //then
    assertEquals(1, LC1290.getDecimalValue(listNode));
  }

  @Test
  void testGetDecimalValueReturn5When3Nodes() {
    //when
    ListNode listNode = new ListNode(1, new ListNode(0, new ListNode(1)));
    //then
    assertEquals(5, LC1290.getDecimalValue(listNode));
  }

  @Test
  void testGetDecimalValueReturn29When5Nodes() {
    //when
    ListNode listNode = new ListNode(1, new ListNode(0, new ListNode(1, new ListNode(1, new ListNode(1)))));
    //then
    assertEquals(1+2+4+16, LC1290.getDecimalValue(listNode));
  }

  @Test
  void testGetDecimalValueReturn29When30Nodes() {
    //when
    String number = "101010101001010001010010101111";
    ListNode head = null;
    for (int i = number.length() - 1; i >= 0; i--) {
      head = new ListNode(number.charAt(i)-'0', head);
    }
    //then
    assertEquals(715461807, LC1290.getDecimalValue(head));
  }
}