package com;

import com.org.interfaces.List;

abstract public class MemoryList implements List {
    protected Node firstObject, lastObject;
    protected int size = 0;

    public Node getInitial() {
        return firstObject;
    }

    public Node getLast() {
        return this.lastObject;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return this.getInitial() == null;
    }

    public void clear() {
        this.firstObject = this.lastObject = null;
        this.size = 0;
    }

    protected void setInitial(Node initial) {
        this.firstObject = initial;
    }

    protected void setLast(Node last) {
        this.lastObject = last;
    }

    protected void incrementSize() {
        this.size++;
    }

    protected void decrementSize() {
        this.size--;
    }

    protected void ensurePositionExists(int position, boolean checkPositive) throws ArrayIndexOutOfBoundsException {
        if ((checkPositive && position < 0) || position > this.getSize()) {
            throw new ArrayIndexOutOfBoundsException(
                    "Position " + position + " is not valid.");
        }
    }
}
