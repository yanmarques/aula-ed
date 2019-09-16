package com.simple_chained_list.org;

import com.queue.org.Node;

public class SimpleChainedList {
    private Node initial;
    private int size = 0;

    public void insert(int position, Node node) throws ArrayIndexOutOfBoundsException {
        this.ensurePositionExists(position);

        if (position == 0) {
            node.setNext(this.initial);
            this.initial = node;
        } else {
            Node actual = this.get(position - 1);
            node.setNext(actual.getNext());
            actual.setNext(node);
        }

        this.size++;
    }

    public Node getInitial() {
        return initial;
    }

    public int getSize() {
        return size;
    }

    public Node remove(int position) throws ArrayIndexOutOfBoundsException {
        this.ensurePositionExists(position);

        Node target;

        if (position == 0) {
            target = this.initial;
            this.initial = this.initial.getNext();
        } else {
            Node actual = this.get(position - 1);
            target = actual.getNext();
            actual.setNext(target.getNext());
        }

        return target;
    }

    public Node get(int position) {
        Node actual = this.initial;
        int counter = 0;
        while (counter < position) {
            actual = actual.getNext();
            counter++;
        }
        return actual;
    }

    private void ensurePositionExists(int position) throws ArrayIndexOutOfBoundsException {
        if (position < 0 || position > this.size) {
            throw new ArrayIndexOutOfBoundsException("Position " + position + " is not valid.");
        }
    }
}
