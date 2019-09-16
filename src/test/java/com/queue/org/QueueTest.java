package com.queue.org;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
    private Queue queue;

    @BeforeEach
    public void createQueue() {
        this.queue = new Queue();
    }

    @AfterEach
    public void teardown() {
        this.queue.clear();
        this.queue = null;
    }

    @Test
    public void insertsOnlyFirst() {
        Node first = new Node(1);
        this.queue.insert(first);
        assertEquals(first, this.queue.getInitial());
        assertEquals(first, this.queue.getLast());
    }

    @Test
    public void removeNodesInsertedByInsertOrder() {
        Node first = new Node(1);
        Node middle = new Node(2);
        Node last = new Node(3);
        this.queue.insert(first);
        this.queue.insert(middle);
        this.queue.insert(last);
        assertEquals(first, this.queue.remove());
        assertEquals(middle, this.queue.remove());
        assertEquals(last, this.queue.remove());
    }

    @Test
    public void countQueueLength() {
        int expected = 2;
        this.queue.insert(new Node(1));
        this.queue.insert(new Node(2));
        assertEquals(queue.getSize(), expected);
    }
}
