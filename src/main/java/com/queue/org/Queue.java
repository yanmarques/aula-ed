package com.queue.org;

public class Queue {
    private Node initial;
    private Node last;
    private int size = 0;

    public void insert(Node node) {
        if (this.isEmpty()) {
            this.initial = node;
            this.last = node;
        } else {
            this.last.setNext(node);
            this.last = node;
        }
        this.size++;
    }

    public void clear() {
        this.initial = this.last = null;
    }

    public Node getInitial() {
        return initial;
    }

    public Node getLast() {
        return last;
    }

    public int getSize() {
        return size;
    }

    public Node remove() {
        Node target = this.initial;
        this.initial = target.getNext();
        this.size--;
        return target;
    }

    public boolean isEmpty() {
        return this.initial == null;
    }
}
