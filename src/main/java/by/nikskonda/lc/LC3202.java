package by.nikskonda.lc;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC3202 {

  public static void main(String[] args) throws Exception {
    System.out.println(maximumLength(new int[]{1, 2, 3, 4, 5, 6, 7}, 3));
    System.out.println(maximumLength(new int[]{1,4,2,3,1,4}, 3));
  }


  public static int maximumLength(int[] nums, int k) {
    int[][] matrix = new int[k][k];
    int max = 0;
    for (int i = 0; i < nums.length; i++) {
      int value = nums[i] % k;
      for (int j = 0; j < k; j++) {
        int x = j;
        int y = value;
        matrix[x][y] = matrix[y][x] + 1;
        if (max < matrix[x][y]) max = matrix[x][y];
      }
    }
    return max;
  }
}