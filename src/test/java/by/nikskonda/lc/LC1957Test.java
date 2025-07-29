package by.nikskonda.lc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-07-29
 */
class LC1957Test {

  private LC1957 solution = new LC1957();

  @Test
  void testMakeFancyString_oneChar_returnStringWithoutChanges() {
    String input = "a";

    assertEquals(input, solution.makeFancyString(input));
  }

  @Test
  void testMakeFancyString_twoChars_returnStringWithoutChanges() {
    String input = "aa";

    assertEquals(input, solution.makeFancyString(input));
  }

  @Test
  void testMakeFancyString_threeChars_returnStringWith2Chars() {
    String input = "aaa";
    String expected = "aa";

    assertEquals(expected, solution.makeFancyString(input));
  }

  @Test
  void testMakeFancyString_threeDiffChars_returnStringWithoutChanges() {
    String input = "abc";

    assertEquals(input, solution.makeFancyString(input));
  }

  @Test
  void testMakeFancyString_threeDiffChars2_returnStringWithoutChanges() {
    String input = "aab";

    assertEquals(input, solution.makeFancyString(input));
  }

  @Test
  void testMakeFancyString_15Chars_returnStringWith2Chars() {
    String input = "aaaaaaaaaaaaaa";
    String expected = "aa";

    assertEquals(expected, solution.makeFancyString(input));
  }

  @Test
  void testMakeFancyString_stringWith3ConsecutiveChars_returnStringWithout3ConsecutiveChars() {
    String input = "aaabaaaa";
    String expected = "aabaa";

    assertEquals(expected, solution.makeFancyString(input));
  }
}