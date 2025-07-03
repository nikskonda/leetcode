package by.nikskonda.lc;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC48 {

  public static void main(String[] args) throws Exception {
    int[][] matrix = {
        {1, 1, 1, 1},
        {2, 2, 2, 2},
        {3, 3, 3, 3},
        {4, 4, 4, 4}
    };
//    int[][] matrix = {
//        {1, 2, 3},
//        {4, 5, 6},
//        {7, 8, 9}
//    };
    int[][] matrix2 = {
        {10, 7, 4, 1},
        {11, 8, 5, 2},
        {12, 9, 6, 3},
        {0, 0 ,0 ,0}
    };
    rotate(matrix);
    System.out.println("RESULT:");
    print(matrix);
  }

  private static void print(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static void rotate(int[][] matrix) {
    int size = matrix.length - 1;
    int level = matrix.length / 2;
    for (int i = 0; i < level; i++) {
      for (int j = i; j < size - i; j++) {
        int temp = matrix[i][j];
        matrix[i][j] = matrix[size-j][i];
        matrix[size-j][i] = matrix[size-i][size-j];
        matrix[size-i][size-j] = matrix[j][size-i];
        matrix[j][size-i] = temp;
      }
    }
  }
}