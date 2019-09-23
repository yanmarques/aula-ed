package com.org.stack;

import com.MemoryList;
import com.org.interfaces.StorageAccessor;

import java.util.ArrayList;
import com.Node;

public class Stack extends MemoryList implements StorageAccessor {
    public void insert(Node node) {
        if (! isEmpty()) {
            node.setPrevious(this.getInitial());
        }

        this.setInitial(node);
        this.incrementSize();
    }

    public Node remove() throws IllegalAccessError {
        return this.remove(true);
    }

    public Node remove(boolean forget) throws IllegalAccessError {
        if (isEmpty()) {
            throw new IllegalAccessError("Stack is already empty.");
        }

        Node current = this.getInitial();
        this.setInitial(current.getPrevious());

        if (forget) {
            current.setPrevious(null);
        }

        this.decrementSize();
        return current;
    }

    public ArrayList<Node> toArray() {
        ArrayList<Node> stack = new ArrayList<>();
        Node running = this.getInitial();
        while (running != null) {
            stack.add(running);
            running = running.getPrevious();
        }
        return stack;
    }
}
