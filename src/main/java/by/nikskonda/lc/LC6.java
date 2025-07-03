package by.nikskonda.lc;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC6 {

  private static final int ITERATIONS = 100;
  private static final int LENGTH = 200;
  private static final int KEK = 1000;
  private static final boolean PERF_TEST = true;
  private static String str = """
      1 2 3 4 5 6 7 8 9 10 11                     
      A           G                                          
        B       F   I                                          
          C   E       J    M                                          
            D           K                                          
      """;

  public static void main(String[] args) throws Exception {
    System.out.println("AA".equals(convert("AA", 2)));
    System.out.println("AGBFCED".equals(convert("ABCDEFG", 4)));
    System.out.println("AGBFICED".equals(convert("ABCDEFGI", 4)));
    System.out.println("AGBFICEJD".equals(convert("ABCDEFGIJ", 4)));
    System.out.println("AGBFICEJDK".equals(convert("ABCDEFGIJK", 4)));
    System.out.println("AGBFICEJMDK".equals(convert("ABCDEFGIJKM", 4)));
  }

  public static String convert(String s, int numRows) {
    if (numRows == 1) return s;
    int dist = numRows * 2 - 2;
    int len = s.length() - 1;
    int picksCount = 1 + len / dist + (len % dist > numRows - 1 ? 1 : 0);

    StringBuilder res = new StringBuilder();
    int[] picks = new int[picksCount];
    int j = 0;
    for (int i = 0; i < picksCount; i++) {
      picks[i] = j;
      j += dist;
    }

    char[] charArray = s.toCharArray();
    for (int row = 0; row < numRows; row++) {
      for (int pick = 0; pick < picksCount; pick++) {
        if (row != 0 && row != numRows - 1) {
          int left = picks[pick] - row;
          if (left >= 0 && left <= len) {
            res.append(charArray[left]);
          }
        }
        int right = picks[pick] + row;
        if (right >= 0 && right <= len) {
          res.append(charArray[right]);
        }
      }
    }
    return res.toString();
  }

}