package com;

public class Node {
    private int value;
    private Node previous, next;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node previous, Node next) {
        this.value = value;
        this.previous = previous;
        this.next = next;
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

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node clone() {
        return new Node(getValue(), getPrevious(), getNext());
    }
}
