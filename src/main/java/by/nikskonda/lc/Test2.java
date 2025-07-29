package by.nikskonda.lc;

import java.util.LinkedList;
import java.util.Queue;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class Test2 {
//  You are given a matrix of size MxN, consisting of integers. Each number in the matrix
//  represents a color.
//  A block is defined as a group of adjacent cells that have the same color and are next
//  to each other either vertically or horizontally (not diagonally).
//
//  Write a Java function that takes a matrix as input and returns the number of such blocks.
  public static void main(String[] args) {
    int[][] matrix = {
        {1, 1, 2, 3},
        {1, 2, 2, 1},
        {3, 3, 1, 1},
        {3, 1, 1, 2} // expected 6 blocks
    };
    System.out.println("Count bloks " + countBlocks(matrix));
  }
  public static int countBlocks(int[][] matrix) {

    return 0;
  }
}