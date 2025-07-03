package by.nikskonda.lc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC17 {

  private static final Map<Character, List<Character>> phone = Map.of(
      '2', List.of('a', 'b', 'c'),
      '3', List.of('d', 'e', 'f'),
      '4', List.of('g', 'h', 'i'),
      '5', List.of('j', 'k', 'l'),
      '6', List.of('m', 'n', 'o'),
      '7', List.of('p', 'q', 'r', 's'),
      '8', List.of('t', 'u', 'v'),
      '9', List.of('w', 'x', 'y', 'z')
  );

  public static void main(String[] args) throws Exception {
    System.out.println(letterCombinations("234"));
  }

  public static List<String> letterCombinations(String digits) {
    if (digits.length() == 0) return List.of();
    List<String> result = new ArrayList<>((digits.length() > 2 ? (int) Math.pow(3, digits.length() - 2) : 1) * 16);
    recursive(digits, 0, new StringBuilder(), result);
    return result;
  }

  private static void recursive(String digest, int num, StringBuilder sb, List<String> result) {
    if (digest.length() == num) {
      result.add(sb.toString());
      return;
    }

    for (Character c : phone.get(digest.charAt(num))) {
      StringBuilder newBuilder = sb.append(c);
      recursive(digest, num+1, newBuilder, result);
      sb.deleteCharAt(sb.length()-1);
    }
  }
}