package com.org.queue;

import com.org.ListPathWalker;

import com.org.Node;
import com.org.interfaces.StorageAccessor;

public class Queue<T> extends ListPathWalker<T> implements StorageAccessor<T> {
    @Override
    public void backwardTo(int position, boolean checkPositive) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Queue can not be backwarded.");
    }

    public void insert(Node<T> node) {
        if (this.isEmpty()) {
            this.setInitial(node);
            this.setLast(node);
        } else {
            this.getLast().setNext(node);
            this.setLast(node);
        }

        this.incrementSize();
    }

    @Override
    public Node<T> remove() throws IllegalAccessError {
        if (isEmpty()) {
            throw new IllegalAccessError("Queue is already empty.");
        }

        Node<T> target = this.getInitial();
        this.setInitial(target.getNext());

        this.decrementSize();
        return target;
    }
}
