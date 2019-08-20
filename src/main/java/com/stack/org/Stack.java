package com.stack.org;

import java.util.ArrayList;

public class Stack {
    private Node top;
    private int size = 0;

    public void insert(Node node) {
        if (isEmpty()) {
            top = node;
        } else {
            node.setPrevious(top);
            top = node;
        }
        size++;
    }

    public Node remove(boolean forget) throws IllegalAccessError {
        if (isEmpty()) {
            throw new IllegalAccessError("Stack is already empty.");
        }
        size--;
        Node current = top;
        top = top.getPrevious();
        if (forget) {
            current.setPrevious(null);
        }
        return current;
    }

    public Node getTop() {
        if (top == null) {
            return null;
        }
        return top.clone();
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Node> toArray() {
        ArrayList<Node> stack = new ArrayList<>();
        Node running = top;
        while (running != null) {
            stack.add(running);
            running = running.getPrevious();
        }
        return stack;
    }

    public void clear() {
        top = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size <= 0;
    }
}
