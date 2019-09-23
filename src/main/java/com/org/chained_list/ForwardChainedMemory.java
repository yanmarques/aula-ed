package com.org.chained_list;

import com.MemoryList;
import com.Node;
import com.org.interfaces.ChainedList;

abstract public class ForwardChainedMemory extends MemoryList implements ChainedList {
    public  Node get(int position) throws ArrayIndexOutOfBoundsException {
        return this.forwardToPosition(position, true);
    }

    protected Node forwardToPosition(int position, boolean checkPositive) {
        this.ensurePositionExists(position, checkPositive);

        if (position == this.getSize()) {
            return this.getLast();
        }

        Node currentNode = this.getInitial();
        int counter = 0;
        while (counter < position) {
            currentNode = currentNode.getNext();
            counter++;
        }

        return currentNode;
    }
}
