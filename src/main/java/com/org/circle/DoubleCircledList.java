package com.org.circle;

import com.org.Node;
import com.org.chained_list.DoubleChainedList;

public class DoubleCircledList<T> extends DoubleChainedList<T> {
    @Override
    public Node<T> get(int position) throws ArrayIndexOutOfBoundsException {
        position = this.parsePosition(position);
        return super.get(position);
    }

    public Node<T> moveTo(int position) {
        position = this.parsePosition(position);
        return super.offsetGet(position);
    }

    public void insertFirst(Node<T> node) {
        if (this.isEmpty()) {
            this.fillEmpty(node);
        } else {
            node.setNext(this.getInitial());
            node.setPrevious(this.getLast());
            this.getInitial().setPrevious(node);
            this.setInitial(node);
            this.getLast().setNext(node);
        }

        this.incrementSize();
    }

    public void insertLast(Node<T> node) {
        if (this.isEmpty()) {
            this.fillEmpty(node);
        } else {
            node.setPrevious(this.getLast());
            node.setNext(this.getInitial());
            this.getLast().setNext(node);
            this.getInitial().setPrevious(node);
            this.setLast(node);
        }

        this.incrementSize();
    }

    public Node<T> forward() {
        this.forwardTo(this.getCurrentPosition() + 1);
        return this.getCurrentNode();
    }

    public Node<T> backward() {
        this.backwardTo(this.getCurrentPosition() - 1);
        return this.getCurrentNode();
    }

    public int parsePosition(int position) {
        if (position < 0) {
            position = position * -1;

            if (this.getSize() > position) {
                position = this.getSize() - position;
            } else {
                position -= this.getSize();
                while (position >= this.getSize()) {
                    position -= this.getSize();
                }
            }
        } else {
            if (position >= this.getSize()) {
                position -= this.getSize();
                while (position >= this.getSize()) {
                    position -= this.getSize();
                }
            }
        }

        return position;
    }

    @Override
    protected void ensurePositionExists(int position) throws ArrayIndexOutOfBoundsException {
        //
    }

    protected void fillEmpty(Node<T> node) {
        node.setPrevious(node);
        node.setNext(node);

        this.setInitial(node);
        this.setLast(node);
    }
}
