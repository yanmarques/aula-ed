package com.org.circle;

import com.org.Node;
import com.org.interfaces.WalkIterator;

public class PositionedCircularList<T> extends DoubleCircledList<T> {
    public Node<T> forward() {
        return this.get(this.getCurrentPosition() + 1);
    }

    public Node<T> backward() {
        return this.get(this.getCurrentPosition() - 1);
    }

    @Override
    public WalkIterator<T> iterator() {
        return new LinkedIterator<>(this.getInitial());
    }

    @Override
    public Node<T> get(int position) throws ArrayIndexOutOfBoundsException {
        int backwardLength = (this.getSize() - position) + this.getCurrentPosition();

        if (position > this.getCurrentPosition() && backwardLength > position) {
            this.forwardTo(position);
            return this.getCurrentNode();
        }

        this.backwardTo(position);
        return this.getCurrentNode();
    }

    @Override
    protected void ensurePositionExists(int position) throws ArrayIndexOutOfBoundsException {
        //
    }

    protected class LinkedIterator<T> extends WalkIteratorImpl<T> {
        private LinkedIterator(Node<T> first) {
            super(first);
        }

        private LinkedIterator(boolean cleanOnStop, Node<T> first) {
            super(cleanOnStop, first);
        }

        @Override
        protected void forwardOperation() {
            if (this.position() >= PositionedCircularList.this.getSize()) {
                this.setCurrentPosition(0);
            }

            super.forwardOperation();
        }

//        @Override
//        protected void backwardOperation() {
//            if (this.getCurrentPosition() < 0) {
//                this.setCurrentPosition(this.getSize() - 1);
//            }
//
//            super.backwardOperation();
//        }
    }
}
