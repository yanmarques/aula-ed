package com.org.interfaces;

import com.org.Node;

public interface PathWalker<T> extends List<T> {
    void forwardTo(int position) throws UnsupportedOperationException;
    void backwardTo(int position) throws UnsupportedOperationException;

    Node<T> getCurrentNode();
    int getCurrentPosition();
}
