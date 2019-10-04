package com.org.chained_list;

import com.org.ListPathWalker;
import com.org.Node;
import com.org.interfaces.ChainedList;

import java.util.Iterator;

abstract public class PositionedMemoryAccess<T> extends ListPathWalker<T> implements ChainedList<T> {

    abstract protected Node<T> offsetGet(int position) throws ArrayIndexOutOfBoundsException;

    public Node<T> get(int position) {
        if (position == 0) {
            return this.getInitial();
        }

        if (position == (this.getSize() - 1)) {
            return this.getLast();
        }

        return this.offsetGet(position);
    }
}
