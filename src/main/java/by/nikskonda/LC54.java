package by.nikskonda;

import java.util.ArrayList;
import java.util.List;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC54 {

  public static void main(String[] args) throws Exception {
    System.out.println(spiralOrder(new int[][] {
        {1, 2, 3, 4},
        {12,13,14,5},
        {11,16,15,6},
        {10,9, 8, 7}
    }));
    System.out.println(spiralOrder(new int[][] {
        {1, 2, 3, 4},
        {10,11,12,5},
        {9, 8, 7,6}
    }));
  }

  public static List<Integer> spiralOrder(int[][] matrix) {
    int size = matrix.length * matrix[0].length;
    List<Integer> result = new ArrayList<>(size);

    int i = 0;
    int j = -1;
    int minI = 0;
    int maxI = matrix.length-1;
    int minJ = 0;
    int maxJ = matrix[minI].length-1;

    int direction = 0;

    while (result.size()<size) {
      if (direction % 4 == 0) {
        while (++j <= maxJ) {
          result.add(matrix[i][j]);
        }
        j--;
        minI++;
        direction++;
      }
      if (direction % 4 == 1 && result.size()<size) {
        while (++i <= maxI) {
          result.add(matrix[i][j]);
        }
        i--;
        maxJ--;
        direction++;
      }
      if (direction % 4 == 2 && result.size()<size) {
        while (--j >= minJ) {
          result.add(matrix[i][j]);
        }
        j++;
        maxI--;
        direction++;
      }
      if (direction % 4 == 3 && result.size()<size) {
        while (--i >= minI) {
          result.add(matrix[i][j]);
        }
        i++;
        minJ++;
        direction++;
      }
    }
    return result;
  }

}