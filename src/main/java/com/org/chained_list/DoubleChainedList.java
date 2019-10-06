package com.org.chained_list;

import com.org.Node;

public class DoubleChainedList<T> extends PositionedMemoryAccess<T> {
    private boolean resetOverride = false;

    @Override
    public void insert(int position, Node<T> node) throws ArrayIndexOutOfBoundsException {
        this.ensurePositionExists(position);

        if (position == 0) {
            this.insertFirst(node);
            return;
        }

        if (position == this.getSize()) {
            this.insertLast(node);
            return;
        }

        Node current = this.get(position - 1);
        node.setPrevious(current);
        node.setNext(current.getNext());
        current.setNext(node);
        node.getNext().setPrevious(node);

        this.incrementSize();
    }

    public void insertFirst(Node<T> node) {
        if (this.isEmpty()) {
            this.setInitial(node);
            this.setLast(node);
        } else {
            node.setNext(this.getInitial());
            this.getInitial().setPrevious(node);
            this.setInitial(node);
        }

        this.incrementSize();
    }

    public void insertLast(Node<T> node) {
        if (this.isEmpty())  {
            this.insertFirst(node);
        } else {
            node.setPrevious(this.getLast());
            this.getLast().setNext(node);
            this.setLast(node);
            this.incrementSize();
        }
    }

    @Override
    public Node<T> remove(int position) throws ArrayIndexOutOfBoundsException {
        this.ensurePositionExists(position);

        if (position == 0) {
            return this.removeFirst();
        }

        if (position == (this.getSize() - 1)) {
            return this.removeLast();
        }

        Node<T> target = this.get(position);
        Node<T> left = target.getPrevious();
        Node<T> right = target.getNext();

        left.setNext(right);
        right.setPrevious(left);

        this.decrementSize();
        return target;
    }

    public Node<T> removeFirst() {
        this.ensurePositionExists(0);

        Node<T> target = this.getInitial();
        Node<T> right = target.getNext();

        if (right == null) {
            this.setLast(null);
        } else{
            right.setPrevious(null);
        }

        this.setInitial(right);

        this.decrementSize();
        return target;
    }

    public Node<T> removeLast() {
        this.ensurePositionExists(this.getSize());

        Node<T> target = this.getLast();
        Node<T> left = target.getPrevious();

        if (left == null) {
            this.setInitial(null);
        } else {
            left.setNext(null);
        }

        this.setLast(left);

        this.decrementSize();
        return target;
    }

    @Override
    protected Node<T> offsetGet(int position) throws ArrayIndexOutOfBoundsException {
        boolean forwarding = false;
        boolean shouldReset = false;
        int firstPath, secondPath;

        if (position > this.getCurrentPosition()) {
            firstPath = this.getSize() - position;
            secondPath = position - this.getCurrentPosition();
            if (secondPath < firstPath) {
                forwarding = true;
            } else {
                shouldReset = true;
            }
        } else {
            firstPath = position;
            secondPath = this.getCurrentPosition() - position;
            if (firstPath < secondPath) {
                forwarding = shouldReset = true;
            }
        }

        this.resetOverride = shouldReset;
        this.binaryPointerMove(forwarding, position);
        this.resetOverride = false;

        return this.getCurrentNode();
    }

    @Override
    protected void resetToInitialNode() {
        if (this.getCurrentNode() == null || this.resetOverride) {
            super.resetToInitialNode();
        }
    }

    @Override
    protected void resetToLastNode() {
        if (this.getCurrentNode() == null || this.resetOverride) {
            super.resetToLastNode();
        }
    }

    protected void binaryPointerMove(boolean forwarding, int position) {
        if (forwarding) {
            this.forwardTo(position);
        } else {
            this.backwardTo(position);
        }
    }
}
