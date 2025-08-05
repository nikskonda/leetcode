package by.nikskonda.lc;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC2106 {

  public static void main(String[] args) throws Exception {
    System.out.println(maxTotalFruits(
        new int[][]{
            new int[]{0, 9},
//            new int[] {1, 2},
            new int[]{4, 1},
            new int[]{5, 7},
            new int[]{6, 2},
            new int[]{7, 4},
//            new int[] {9, 1},
            new int[]{10, 9},
        }, 5, 4
    ));
    System.out.println(maxTotalFruits(
        new int[][]{
            new int[]{5, 10}
        }, 5, 0
    ));
  }

  public static int maxTotalFruits(int[][] fruits, int startPos, int k) {
    int result = 0;
    int left = Math.max(0, startPos - k);
    int right = startPos + k;
    int[] axis = new int[right - left + 1];

    for (int[] fruit : fruits) {
      int pos = fruit[0];
      int value = fruit[1];
      if (pos >= left && pos <= right) {
        axis[pos - left] = value;
      }
    }
    startPos = Math.min(k, startPos);

    for (int i = startPos-2; i >= 0; i--) {
      axis[i] += axis[i + 1];
    }
    for (int i = startPos+2; i < axis.length; i++) {
      axis[i] += axis[i - 1];
    }
    cacheBaseValue = axis[startPos];
    axis[startPos] = 0;
    cacheFirstValue = axis[0];
    cacheLastValue = axis[axis.length - 1];

    result = Math.max(result, calc(axis, k, 0, startPos));
    result = Math.max(result, calc(axis, 0, k, startPos));

    for (int i = k/2; i >= 0; i--) {
      left = i;
      right = Math.max(0, k - i * 2);

      result = Math.max(result, calc(axis, left, right, startPos));
      result = Math.max(result, calc(axis, right, left, startPos));
    }

    return result;
  }
  private static int cacheBaseValue = 0;
  private static int cacheFirstValue = 0;
  private static int cacheLastValue = 0;

  private static int calc(int[] axis, int left, int right, int startPos) {
    int leftSum = startPos - left > 0
        ? axis[startPos - left]
        : cacheFirstValue;
    int rightSum = startPos + right < axis.length
        ? axis[startPos + right]
        : cacheLastValue;
    return leftSum + rightSum + cacheBaseValue;
  }
}