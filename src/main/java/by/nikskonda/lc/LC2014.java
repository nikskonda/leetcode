package by.nikskonda.lc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC2014 {

  public static void main(String[] args) {
//    getCombinations("abc", 2);
//    System.out.println(longestSubsequenceRepeatedK("absdcadqbcadbc", 3));
//    System.out.println(longestSubsequenceRepeatedK("letsleetcode", 2));
//    System.out.println(longestSubsequenceRepeatedK("ababaaaa", 3));
//    System.out.println(longestSubsequenceRepeatedK("bbabbabbbbabaababab", 3));
//    System.out.println(longestSubsequenceRepeatedK("arzwpdlarzwpl", 2));
//    System.out.println(longestSubsequenceRepeatedK("nhsbbeonhsbbeonhsbbeo", 3));
//    System.out.println(longestSubsequenceRepeatedK(
//        "qilqyegsqilheorubsqilesqilpedesqipblesqiqlesqilbesqilesqvilesqhilebscnqilesvgqilesqilesqihleiwznsqsilesqbsiklezsgsqilestqqilesqilesxaqillessqijpmletxsqifvldesqilesrgqcilaeszqzilxescqialesqilesqilmesqileshqdilesfqiletsqileysqicjlesmqilesqilnesqilesqilfesqilesqeillytesqhilwjefsqiklegskqialesqilesqiltesqieileuszeqilnesqilesqigleusqilnepsqilnesqilesuqileijksqilglesqqilrqesqrrilesdqilesqilezszqsvailuejssqiliensbqilweszxuqqilesqqilesqiliuesqilmebsqiqlesqmkilatesqilesuuqiqljesqilzeseqialesgwqjiglesqeilesqnoileesqcilesqilesqwilpemspvqkilesqieglesqilzeysqilesqilesqilesqileslqilesqiulexsqilesqxilmowesqiqlehsqiokleslfqilesqilxrebfsqilesqilesqibnlesqxiblesquilvxesiqmuiydlesqilesqileusqyilesqzkilesqosilesjlqfliglwusgetsqilesqiilesqiuleasqklicleszqilesqialetgjsqiluesqifhmlesqilegsqileshmqitlltesqilesqyidlevpsqyalilesquilesqilvesqkilesqilesqielevsqileusqilesqbilesqitgulelshyqilesqilesqileshqwvkhilecsqileysqiledsqilegswqilesqirglmesqinlesqqilesqzilesqijlesqiylwesqilesqielxeys",
//        142));
    System.out.println(longestSubsequenceRepeatedK("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq", 286));
  }

  public static String longestSubsequenceRepeatedK(String s, int k) {
    int[] array = new int[26];
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      int index = c - 'a';
      array[index] += 1;
    }
    List<String> cand = new ArrayList<>(26*8);
    Queue<String> queue = new LinkedList<>();
    for (int i = 25; i >= 0; i--) {
      while (array[i] >= k) {
        String value = String.valueOf((char) ('a' + i));
        cand.add(value);
        array[i] -= k;
        queue.add(value);
      }
    }
    if (cand.isEmpty()) {
      return "";
    }
    String answer = cand.get(0);

    while (!queue.isEmpty()) {
      String value = queue.poll();
      if (value.length() == cand.size()) break;
      for (int i = 0; i < cand.size(); i++) {
        String pattern = value + cand.get(i);
        if (isValidPattern(k, s, pattern)) {
          queue.add(pattern);
          if (pattern.length() > answer.length()) {
            answer = pattern;
          }
        }
      }

    }

    return answer;
  }

  private static boolean isValidPattern(int k, String str, String pattern) {
    boolean valid = true;
    int strt = 0;
    for (int j = 0; j < k && valid; j++) {
      strt = findPattern(strt, str, pattern);
      if (strt < 0 || strt > str.length() - pattern.length() * (k-j)) {
        valid = false;
      }
    }
    return valid;
  }

  private static int findPattern(int startIndex, String str, String pattern) {

    for (int i = 0; i < pattern.length(); i++) {
      int indexOf = str.indexOf(pattern.charAt(i), startIndex);
      if (indexOf >= startIndex) {
        startIndex = indexOf + 1;
      } else {
        return -1;
      }
    }
    return startIndex;
  }

}