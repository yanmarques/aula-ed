package com.org.interfaces;

import com.Node;

public interface ChainedList extends List {
    void insert(int position, Node Node) throws ArrayIndexOutOfBoundsException;
    Node remove(int position) throws ArrayIndexOutOfBoundsException;
    Node get(int position) throws ArrayIndexOutOfBoundsException;
}
