package com.org.interfaces;

import com.Node;

public interface List {
    Node getInitial();
    Node getLast();
    int getSize();
    boolean isEmpty();
    void clear();
}
