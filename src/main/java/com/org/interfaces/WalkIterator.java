package com.org.interfaces;

import com.org.Node;

import java.util.Iterator;

public interface WalkIterator<T> extends Iterator<T> {
    boolean hasPrevious();
    T previous();

    void stop();
    int position();
    Node<T> current();
    void resetNode(Node<T> nextNode, int position);
}
