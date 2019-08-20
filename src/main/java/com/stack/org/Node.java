package com.stack.org;

public class Node {
    private int value;
    private Node previous;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node previous) {
        this.value = value;
        this.previous = previous;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node clone() {
        return new Node(getValue(), getPrevious());
    }
}
