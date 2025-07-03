package by.nikskonda.lc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-06-26
 */
class LC2311Test {

  @Test
  void longestSubsequence() {
    String inputStr = "1010101010101";
    int inputNum = 5;

    assertEquals(8, LC2311.longestSubsequence(inputStr, inputNum));
  }

  @Test
  void longestSubsequence1() {
    String inputStr = "1010101010101";
    int inputNum = 4;

    assertEquals(7, LC2311.longestSubsequence(inputStr, inputNum));
  }

  @Test
  void longestSubsequence2() {
    String inputStr = "1010101010101";
    int inputNum = 6;

    assertEquals(8, LC2311.longestSubsequence(inputStr, inputNum));
  }

  @Test
  void longestSubsequence3() {
    String inputStr = "1010101010101";
    int inputNum = 9;

    assertEquals(8, LC2311.longestSubsequence(inputStr, inputNum));
  }

  @Test
  void longestSubsequence4() {
    String inputStr = "101010101001";
    int inputNum = 5;

    assertEquals(7, LC2311.longestSubsequence(inputStr, inputNum));
  }
  @Test
  void longestSubsequence5() {
    String inputStr = "000101010011011001011101111000111111100001011000000100010000111100000011111001000111100111101001111001011101001011011101001011011001111111010011100011110111010000010000010111001001111101100001111";
    int inputNum = 300429827;

    assertEquals(108, LC2311.longestSubsequence(inputStr, inputNum));
  }
}