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

        traverse(root, new StringBuilder("\n") ,0, true, true);
        return printed.append("\n").substring(1);
    }

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            current = new Node(value);
            return current;
        }
        if (current.data > value) {
            current.leftNode = addRecursive(current.leftNode, value);
        } else if (current.data < value) {
            current.rightNode = addRecursive(current.rightNode, value);
        }
        return current;
    }
//    private void traverse(Node root, int space, boolean isLeft, boolean isRoot, Set<Integer> verticalLines) {

    private void traverse(Node root, StringBuilder lineSB, int space, boolean isLeft, boolean isRoot) {
        //BaseCase
        if (root == null) {
            return;
        }

        int dataSize = Integer.toString(root.data).length();
        int depth = lineSB.length();
        int i = "\n │".indexOf(lineSB.charAt(depth-1));
        int j = (root.leftNode != null ? 2 : 0) + (root.rightNode != null ? 1:0);

        lineSB.append(" ".repeat((dataSize +1)));


        space += String.valueOf(root.data).length();
        space += isRoot ? -1 : 1;

        traverse(root.leftNode, lineSB, space, true, false);
        lineSB.setLength(depth -1);

//        printed.append(lineSB);
//
//        printed.append(!isRoot && isLeft ? "┌" : "");
//        printed.append(!isRoot && !isLeft ? "└" : "");
//        printed.append(root.data);
//        if (root.rightNode != null && root.leftNode != null) {
//            printed.append("┤");
//        } else if (root.leftNode != null) {
//            printed.append("┘");
//        } else if (root.rightNode != null) {
//            printed.append("┐");
//        }

        printed.append(lineSB);
        printed.append("\n┌└".charAt(i));
        printed.append(root.data);
        printed.append(" ┐┘┤".charAt(j));
        printed.setLength(root.rightNode==null && root.leftNode==null ? printed.length()-1: printed.length());

        lineSB.append("\n│ ".charAt(i));
        lineSB.append(" ".repeat(dataSize));
        lineSB.append(" │ │".charAt(j));

        traverse(root.rightNode, lineSB, space, false, false);

    }

}

