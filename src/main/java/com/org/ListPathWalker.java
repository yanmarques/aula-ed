package com.org;

import com.org.interfaces.PathWalker;
import com.org.interfaces.WalkIterator;

abstract public class ListPathWalker<T> extends MemoryList<T>
        implements PathWalker<T>, Iterable<T> {

    private WalkIterator<T> cursor;

    protected ListPathWalker() {
        super();
        this.cursor = new WalkIteratorImpl<>(false);
    }

    @Override
    public int getCurrentPosition() {
        return getCursor().position();
    }

    @Override
    public Node<T> getCurrentNode() {
        return getCursor().current();
    }

    @Override
    public void forwardTo(int position) {
        this.ensurePositionExists(position);

        this.resetToInitialNode();
        while (this.getCursor().hasNext() && this.getCurrentPosition() <= position) {
            this.getCursor().next();
        }
    }

    @Override
    public WalkIterator<T> iterator() {
        return new WalkIteratorImpl<>(this.getInitial());
    }

    @Override
    public void backwardTo(int position) {
        this.ensurePositionExists(position);

        this.resetToLastNode();
        while (this.getCursor().hasPrevious() && this.getCurrentPosition() >= position) {
            this.getCursor().previous();
        }
    }

    public WalkIterator<T> getCursor() {
        return cursor;
    }

    protected void resetToInitialNode() {
        getCursor().resetNode(this.getInitial(), 0);
    }

    protected void resetToLastNode() {
        getCursor().resetNode(this.getLast(), this.getSize() - 1);
    }

    protected class WalkIteratorImpl<T> implements WalkIterator<T> {
        private Node<T> currentNode = null;
        private int currentPosition = 0;
        private boolean stopping = false;
        private boolean cleanOnStop = true;

        public WalkIteratorImpl(Node<T> first) {
            this.resetNode(first, 0);
        }

        public WalkIteratorImpl(boolean cleanOnStop, Node<T> first) {
            this(first);
            this.setCleanOnStop(cleanOnStop);
        }

        public WalkIteratorImpl(boolean cleanOnStop) {
            this.setCleanOnStop(cleanOnStop);
        }

        @Override
        public boolean hasNext() {
            return ! shouldNotStop() && current().getNext() != null;
        }

        @Override
        public T next() {
            this.forwardOperation();
            return current().getValue();
        }

        @Override
        public boolean hasPrevious() {
            return ! shouldNotStop() && current().getPrevious() != null;
        }

        @Override
        public T previous() {
            this.backwardOperation();
            return current().getValue();
        }

        public int position() {
            return currentPosition;
        }

        public Node<T> current() {
            return currentNode;
        }

        @Override
        public void stop() {
            this.stopping = true;
            if (this.cleanOnStop) {
                this.currentNode = null;
            }
        }

        public boolean shouldNotStop() {
            return this.stopping || current() == null;
        }

        protected void setCleanOnStop(boolean status) {
            this.cleanOnStop = status;
        }

        public void resetNode(Node<T> nextNode, int position) {
            Node<T> firstOne = new Node<>(null);
            firstOne.setNext(nextNode);
            firstOne.setPrevious(nextNode);
            this.currentNode = firstOne;
            this.setCurrentPosition(position);
        }

        protected void setCurrentPosition(int currentPosition) {
            this.currentPosition = currentPosition;
        }

        protected void forwardOperation() {
            this.currentNode = this.current().getNext();
            this.currentPosition++;
        }

        protected void backwardOperation() {
            this.currentNode = this.current().getPrevious();
            this.currentPosition--;
        }
    }
}
