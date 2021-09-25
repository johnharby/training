package com.learn.algorithms2;

import java.util.ArrayList;
import java.util.List;

public class CircularList {
    private Node root;

    public boolean isCircular() {
        Node node = root;
        List<Node> l = new ArrayList<>();
        l.add(root);
        while(node != null) {
            if (l.contains(node.next)) {
                return true;
            }
            else {
                l.add(node.next);
                node = node.next;
            }
        }
        return false;
    }

    private static class Node {
        private Node next;
        private String value;

        @Override
        public boolean equals(Object other) {
            if (!(other instanceof Node)) {
                return false;
            }
            Node otherNode = (Node) other;
            return this.value.equals(otherNode.value);
        }

        @Override
        public int hashCode() {
            int ret = 17;
            ret += 31 * value.hashCode();
            return ret;
        }

    }

    public static void main(String[] args) {
        CircularList cl = new CircularList();
        Node root = new Node();
        root.value = "test1";
        Node node1 = new Node();
        node1.value = "test2";
        root.next = node1;
        cl.root = root;
        Node node2 = new Node();
        node2.value = "test3";
        node2.next = node1;
        node1.next = node2;
        System.out.println(cl.isCircular());
    }


}

