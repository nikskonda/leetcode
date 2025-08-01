package by.nikskonda.lc;

import java.util.ArrayList;
import java.util.List;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC118 {

  public List<List<Integer>> generate(int numRows) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> prevLine =List.of(1, 1);

    result.add(List.of(1));
    if (numRows > 1) {
      result.add(prevLine);
    }

    for (int i = 2; i < numRows; i++) {
      List<Integer> line = new ArrayList<>(i);
      line.add(1);

      for (int j = 1; j < i; j++) {
        line.add(prevLine.get(j-1) + prevLine.get(j));
      }

      line.add(1);
      result.add(line);
      prevLine = line;
    }
    return result;
  }

}