package com.epam.rd.autocode.bstprettyprint;

import java.util.HashSet;
import java.util.Set;

public class Tree implements PrintableTree {
    Node root;
    StringBuilder printed = new StringBuilder();

    public Tree() {
        root = null;
    }

    @Override
    public void add(int i) {
        root = addRecursive(root, i);
    }

    @Override
    public String prettyPrint() {

        traverse(root, 0, true, true, 0);
//        printed.setLength(0); // Clear the StringBuilder
//        traverse(root, 0, true, true, new HashSet<>());
        return printed.toString();
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            current = new Node(value);
            return current;
        }
        if (current.data > value) {
            current.leftNode = addRecursive(current.leftNode, value);
        } else {
            current.rightNode = addRecursive(current.rightNode, value);
        }
        return current;
    }
//    private void traverse(Node root, int space, boolean isLeft, boolean isRoot, Set<Integer> verticalLines) {

    private void traverse(Node root, int space, boolean isLeft, boolean isRoot, int stick ) {
        //BaseCase
        if (root == null) {
            return;
        }

        space += String.valueOf(root.data).length();

        space += isRoot ? -1 : 1;

        traverse(root.leftNode, space, true, false, stick);


        printed.append(" ".repeat(Math.max(0, space - String.valueOf(root.data).length())));
        printed.append(!isRoot && isLeft ? "┌" : "");
        printed.append(!isRoot && !isLeft ? "└" : "");
        printed.append(root.data);
        if (root.rightNode != null && root.leftNode != null) {
            printed.append("┤");
        } else if (root.leftNode != null) {
            printed.append("┘");
        } else if (root.rightNode != null) {
            printed.append("┐");
        }
        printed.append("\n");


        traverse(root.rightNode, space, false, false, stick);

    }

}
