package com.org.stack;

import com.org.ListPathWalker;
import com.org.chained_list.DoubleChainedList;
import com.org.interfaces.ChainedList;
import com.org.interfaces.StorageAccessor;

import com.org.Node;
import com.org.interfaces.WalkIterator;

public class Stack<T> extends ListPathWalker<T> implements StorageAccessor<T> {
    public Stack() {
        super();
    }

    @Override
    public WalkIterator<T> iterator() {
        return new BackwardIterator(this.getInitial());
    }

    @Override
    public void insert(Node<T> node) {
        if (! isEmpty()) {
            node.setPrevious(this.getInitial());
        }

        this.setInitial(node);
        this.incrementSize();
    }

    @Override
    public Node<T> remove() throws IllegalAccessError {
        return this.remove(true);
    }

    public Node<T> remove(boolean forget) throws IllegalAccessError {
        if (isEmpty()) {
            throw new IllegalAccessError("Stack is already empty.");
        }

        Node<T> current = this.getInitial();
        this.setInitial(current.getPrevious());

        if (forget) {
            current.setPrevious(null);
        }

        this.decrementSize();
        return current;
    }

    @Override
    public ChainedList<T> toList() {
        DoubleChainedList<T> stack = new DoubleChainedList<>();

        for (T item : this) {
            stack.insertLast(new Node<>(item));
        }

        return stack;
    }

    public class BackwardIterator extends WalkIteratorImpl<T> {
        public BackwardIterator(Node<T> first) {
            super(first);
        }

        public BackwardIterator(boolean cleanOnStopping, Node<T> first) {
            super(cleanOnStopping, first);
        }

        @Override
        public boolean hasNext() {
            return this.hasPrevious();
        }

        @Override
        public T next() {
            return previous();
        }
    }
}
