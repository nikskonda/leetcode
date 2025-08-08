package by.nikskonda.lc;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC3363 {

  public static void main(String[] args) throws Exception {
    System.out.println(maxCollectedFruits(new int[][]{
        new int[]{1, 2, 3, 4},
        new int[]{5, 6, 8, 7},
        new int[]{9, 10, 11, 12},
        new int[]{13, 14, 15, 16}
    }));
  }
//     j=0  j=1  j=2  j=3  j=4
//i=0  0,0  0,1  0,2  0,3  0,4
//i=1  1,0  1,1  1,2  1,3  1,4   j>=row-i
//i=2  2,0  2,1  2,2  2,3  2,4   i<=j
//i=3  3,0  3,1  3,2  3,3  3,4
//i=4  4,0  4,1  4,2  4,3  4,4

  public static int maxCollectedFruits(int[][] fruits) {
    int sum = 0;
    int size = fruits.length;
    for (int i = 0; i < size; i++) {
      sum += fruits[i][i];
      fruits[i][i] = 0;
    }
    for (int i = 1; i < size; i++) {
      for (int j = Math.max(size - i - 1, i); j < size; j++) {
        int step1 = j - 1;
        int step2 = j;
        int step3 = j + 1;
        int row = i - 1;
        int value1 = isValidForSecondBoy(row, step1, size) ? fruits[row][step1] : 0;
        int value2 = isValidForSecondBoy(row, step2, size) ? fruits[row][step2] : 0;
        int value3 = isValidForSecondBoy(row, step3, size) ? fruits[row][step3] : 0;
        fruits[i][j] += Math.max(Math.max(value1, value2), value3);
      }
    }
    sum += fruits[size - 1][size - 1];
    for (int i = size / 2 - 1; i < size; i++) {
      fruits[i][i] = 0;
    }

    for (int j = 1; j < size; j++) {
      for (int i = Math.max(size - j - 1, j); i < size; i++) {
        int step1 = i - 1;
        int step2 = i;
        int step3 = i + 1;
        int col = j - 1;
        int value1 = isValidForThirdBoy(step1, col, size) ? fruits[step1][col] : 0;
        int value2 = isValidForThirdBoy(step2, col, size) ? fruits[step2][col] : 0;
        int value3 = isValidForThirdBoy(step3, col, size) ? fruits[step3][col] : 0;
        fruits[i][j] += Math.max(Math.max(value1, value2), value3);
      }
    }

    sum += fruits[size - 1][size - 1];
    return sum;
  }

  private static boolean isValidForSecondBoy(int i, int j, int size) {
    return j < size && j >= size - 1 - i && i <= j;
  }
  private static boolean isValidForThirdBoy(int i, int j, int size) {
    return i < size && i >= size - 1 - j && i >= j;
  }
}