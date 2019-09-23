package com.org.queue;

import com.MemoryList;

import com.Node;
import com.org.interfaces.StorageAccessor;

public class Queue extends MemoryList implements StorageAccessor {
    public void insert(Node node) {
        if (this.isEmpty()) {
            this.setInitial(node);
            this.setLast(node);
        } else {
            this.getLast().setNext(node);
            this.setLast(node);
        }

        this.incrementSize();
    }

    public Node remove() {
        Node target = this.getInitial();
        this.setInitial(target.getNext());

        this.decrementSize();
        return target;
    }
}
