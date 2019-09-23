package com.org.chained_list;

import com.Node;

public class SimpleChainedList extends ForwardChainedMemory {
    public void insert(int position, Node node) throws ArrayIndexOutOfBoundsException {
        this.ensurePositionExists(position, true);

        if (position == 0) {
            node.setNext(this.getInitial());
            this.setInitial(node);
        } else {
            Node actual = this.get(position - 1);
            node.setNext(actual.getNext());
            actual.setNext(node);
        }

        this.incrementSize();
    }

    public Node remove(int position) throws ArrayIndexOutOfBoundsException {
        this.ensurePositionExists(position, true);

        Node target;

        if (position == 0) {
            target = this.getInitial();
            this.setInitial(this.getInitial().getNext());
        } else {
            Node actual = this.get(position - 1);
            target = actual.getNext();
            actual.setNext(target.getNext());
        }

        this.decrementSize();

        return target;
    }
}
