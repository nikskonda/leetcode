package by.nikskonda.lc;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-06-25
 */
class LC56Test {

  @Test
  void merge1() {
    int[][] input = new int[][]{
        new int[]{1, 3},
        new int[]{2, 6},
        new int[]{8, 10},
        new int[]{15, 18}
    };
    int [][] expected = new int[][]{
        new int[]{1, 6},
        new int[]{8, 10},
        new int[]{15, 18}
    };

    int[][] result = LC56.merge(input);
    assertTrue(result.length == expected.length);
    for (int i = 0; i < expected.length; i++) {
      assertTrue(Arrays.equals(expected[i], result[i]));
    }
  }

  @Test
  void merge2() {
    int[][] input = new int[][]{
        new int[]{8, 10},
        new int[]{15, 18},
        new int[]{1, 3},
        new int[]{2, 6},
    };
    int [][] expected = new int[][]{
        new int[]{1, 6},
        new int[]{8, 10},
        new int[]{15, 18}
    };

    int[][] result = LC56.merge(input);
    assertTrue(result.length == expected.length);
    for (int i = 0; i < expected.length; i++) {
      assertTrue(Arrays.equals(expected[i], result[i]));
    }
  }

  @Test
  void merge3() {
    int[][] input = new int[][]{
        new int[]{1, 4},
        new int[]{2, 3},
        new int[]{3, 4},
        new int[]{4, 6},
    };
    int [][] expected = new int[][]{
        new int[]{1, 6}
    };

    int[][] result = LC56.merge(input);
    assertTrue(result.length == expected.length);
    for (int i = 0; i < expected.length; i++) {
      assertTrue(Arrays.equals(expected[i], result[i]));
    }
  }
}