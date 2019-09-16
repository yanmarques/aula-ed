package com.org.chained_list;

import com.Node;

public class DoubleChainedList extends ForwardChainedMemory {
    @Override
    public void insert(int position, Node node) throws ArrayIndexOutOfBoundsException {
        this.ensurePositionExists(position, true);

        if (position == 0) {
            this.insertFirst(node);
            return;
        }

        if (position == this.getSize()) {
            this.insertLast(node);
            return;
        }

        Node current = this.get(position - 1);
        node.setPrevious(current);
        node.setNext(current.getNext());
        current.setNext(node);
        node.getNext().setPrevious(node);

        this.incrementSize();
    }

    public void insertFirst(Node node) {
        if (this.isEmpty()) {
            this.setInitial(node);
            this.setLast(node);
        } else {
            node.setNext(this.getInitial());
            this.getInitial().setPrevious(node);
            this.setInitial(node);
        }

        this.incrementSize();
    }

    public void insertLast(Node node) {
        node.setPrevious(this.getLast());
        this.getLast().setNext(node);
        this.setLast(node);

        this.incrementSize();
    }

    @Override
    public Node remove(int position) throws ArrayIndexOutOfBoundsException {
        this.ensurePositionExists(position, true);

        if (position == 0) {
            return this.removeFirst();
        }

        if (position == (this.getSize() - 1)) {
            return this.removeLast();
        }

        Node target = this.get(position);
        Node left = target.getPrevious();
        Node right = target.getNext();

        left.setNext(right);
        right.setPrevious(left);

        this.decrementSize();
        return target;
    }

    public Node removeFirst() {
        Node target = this.getInitial();
        Node right = target.getNext();

        right.setPrevious(null);
        this.setInitial(right);

        this.decrementSize();
        return target;
    }

    public Node removeLast() {
        Node target = this.getLast();
        Node left = target.getPrevious();

        left.setNext(null);
        this.setLast(left);

        this.decrementSize();
        return target;
    }
}
