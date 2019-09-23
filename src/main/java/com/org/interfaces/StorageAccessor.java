package com.org.interfaces;

import com.Node;

public interface StorageAccessor extends List {
    void insert(Node node);
    Node remove();
}
