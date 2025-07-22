package by.nikskonda.lc;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * TheClass
 *
 * @author Mikita Shkonda Date: 2025-03-24
 */
public class LC1110 {

  public static void main(String[] args) throws Exception {

    System.out.println(delNodes(new TreeNode(1,
            new TreeNode(2, new TreeNode(4), new TreeNode(5)),
            new TreeNode(3, new TreeNode(6), new TreeNode(7))),

        new int[]{1, 2, 5}));
  }

  public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
    LinkedList<TreeNode> resultList = new LinkedList<>();

    Set<Integer> toDelete = new HashSet<>(to_delete.length);
    for (int i = 0; i < to_delete.length; i++) {
      toDelete.add(to_delete[i]);
    }
    Queue<TreeNode> queue = new LinkedList<>();

    queue.add(root);
    while (!queue.isEmpty()) {
      TreeNode head = queue.poll();
      if (toDelete.contains(head.val)) {
        if (head.left != null) queue.add(head.left);
        if (head.right != null) queue.add(head.right);
      } else {
        checkRecursive(head.left, head, toDelete, queue);
        checkRecursive(head.right, head, toDelete, queue);
        resultList.add(head);
      }
    }
    return resultList;
  }

  private static void checkRecursive(TreeNode child, TreeNode parent, Set<Integer> toDelete, Queue<TreeNode> queue) {
    if (child == null) {
      return;
    }
    if (toDelete.contains(child.val)){
      if (hasChild(child)) {
        if (child.left != null) queue.add(child.left);
        if (child.right != null) queue.add(child.right);
      }
      if (parent.left == child) parent.left = null;
      else parent.right = null;
    } else {
      checkRecursive(child.left, child, toDelete, queue);
      checkRecursive(child.right, child, toDelete, queue);
    }
  }

  private static boolean hasChild(TreeNode node) {
    return node.right != null || node.left != null;
  }


  /**
   * Definition for a binary tree node.
   */
  public static class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
      this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }
  }

}