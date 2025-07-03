package by.nikskonda.lc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class Main37 {

  public static void main(String[] args) {
    char[][] board1 = {
        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
        {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
        {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };
    char[][] board2 = new char[][]{
        {'.', '.', '9', '7', '4', '8', '.', '.', '.'},
        {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
        {'5', '.', '7', '.', '.', '.', '2', '4', '.'},
        {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
        {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
        {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
        {'.', '.', '.', '2', '7', '5', '9', '.', '.'}
    };

    char[][] board3 = {
        {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
        {'.', '7', '2', '1', '9', '5', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '6', '.'},
        {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
        {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
        {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
        {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
        {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
        {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };
    char[][] board = board3;
    solveSudoku(board);

    for (int i = 0; i < 9; i++) {
      if (i == 3 || i == 6) {
        System.out.println();
      }
      for (int j = 0; j < 9; j++) {
        if (j == 3 || j == 6) {
          System.out.print("  ");
        }
        System.out.print(board[i][j] + " ");
      }
      System.out.println();

    }
  }

  public static void solveSudoku(char[][] board) {
    StateSudoku baseState = new StateSudoku(board);
    TreeSet<Pair> pairs = baseState.redusePairs();
    if (!baseState.isSolved()) {
      Pair randomP = pairs.pollFirst();
      for (Integer value : randomP.possibleValues) {
        StateSudoku clone = new StateSudoku(baseState.board);
        clone.setValue(value, randomP);
        if (solveRecursively(clone)) {
          baseState.board = clone.board;
          break;
        }
      }
    }
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        board[i][j] = baseState.board[i][j];
      }
    }
  }

  public static boolean solveRecursively(StateSudoku state) {
    TreeSet<Pair> pairs = state.redusePairs();
    if (state.isSolved()) {
      return true;
    } else {
      Pair randomP = pairs.pollFirst();
      if (randomP.possibleValues.isEmpty()) {
        return false;
      }
      for (Integer value : randomP.possibleValues) {
        StateSudoku clone = new StateSudoku(state.board);
        clone.setValue(value, randomP);
        if (solveRecursively(clone)) {
          state.board = clone.board;
          return true;
        }
      }
      return false;
    }
  }

  private static class StateSudoku {

    char[][] board = new char[9][9];
    List<Set<Integer>> boxes = new ArrayList<>(9);
    List<Set<Integer>> rows = new ArrayList<>(9);
    List<Set<Integer>> columns = new ArrayList<>(9);
    Set<Pair> pairs = new HashSet<>(81);

    public StateSudoku(char[][] board) {
      for (int i = 0; i < 9; i++) {
        boxes.add(new HashSet<>(9));
        rows.add(new HashSet<>(9));
        columns.add(new HashSet<>(9));
      }
      for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
          this.board[i][j] = board[i][j];

          if (board[i][j] != '.') {
            int value = board[i][j] - 48;
            rows.get(i).add(value);
            columns.get(j).add(value);
            boxes.get(i / 3 * 3 + j / 3).add(value);
          } else {
            pairs.add(new Pair(i, j));
          }
        }
      }
    }

    public boolean isSolved() {
      return pairs.isEmpty();
    }

    public void setValue(int value, Pair pair) {
      board[pair.i][pair.j] = (char) (value + 48);
      pairs.remove(pair);
      rows.get(pair.i).add(value);
      columns.get(pair.j).add(value);
      boxes.get(pair.i / 3 * 3 + pair.j / 3).add(value);
    }

    public boolean testValue(int value, Pair pair) {
      return !rows.get(pair.i).contains(value)
          && !columns.get(pair.j).contains(value)
          && !boxes.get(pair.i / 3 * 3 + pair.j / 3).contains(value);
    }

    public TreeSet<Pair> redusePairs() {
      TreeSet<Pair> result = new TreeSet<>((p1, p2) -> {
        int size1 = p1.possibleValues.size();
        int size2 = p2.possibleValues.size();
        int sizeCompare = Integer.compare(size1, size2);
        return sizeCompare == 0
            ? Integer.compare(p1.hashCode(), p2.hashCode())
            : sizeCompare;
      });
      boolean needRandom = false;
      while (!this.isSolved() && !needRandom) {
        result.clear();
        needRandom = true;

        for (Pair pair : new ArrayList<>(this.pairs)) {
          if (pair.possibleValues.isEmpty()) {
            for (int testValue = 1; testValue < 10; testValue++) {
              if (this.testValue(testValue, pair)) {
                pair.possibleValues.add(testValue);
              }
            }
          } else {
            pair.possibleValues.removeIf(testValue -> !this.testValue(testValue, pair));
          }
          if (pair.possibleValues.size() == 1) {
            needRandom = false;
            setValue(pair.possibleValues.iterator().next(), pair);
          } else {
            result.add(pair);
          }
        }
      }
      return result;
    }
  }

  private static class Pair {

    int i;
    int j;
    Set<Integer> possibleValues;

    public Pair(int i, int j) {
      this.i = i;
      this.j = j;
      this.possibleValues = new HashSet<>();
    }


    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Pair pair = (Pair) o;
      return i == pair.i && j == pair.j;
    }

    @Override
    public int hashCode() {
      return i * 10 + j;
    }
  }
}