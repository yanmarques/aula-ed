package com.org.chained_list;

import com.org.ListPathWalker;
import com.org.Node;
import com.org.interfaces.ChainedList;
import com.org.interfaces.WalkIterator;


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

    public int index(T needle) throws RuntimeException {
        int currentPos;

        WalkIterator<T> iterator = this.iterator();
        while (iterator.hasNext()) {
            currentPos = iterator.position();

            if (needle == iterator.next()) {
                iterator.stop(); // help GC
                return currentPos;
            }
        }

        throw new RuntimeException("Item [" + needle + "] was not found on the list.");
    }

    public void remove(T needle) throws RuntimeException {
        int index = this.index(needle);
        this.remove(index);
    }
}
