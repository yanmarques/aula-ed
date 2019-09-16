package com.simple_chained_list.org;

import com.queue.org.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleChainedListTest {
    private SimpleChainedList list;

    @BeforeEach
    public void createList() {
        this.list = new SimpleChainedList();
    }

    @AfterEach
    public void teardown() {
        this.list = null;
    }

    @Test
    public void insertsOnFirstPosition() throws ArrayIndexOutOfBoundsException {
        Node first = new Node(1);
        this.list.insert(0, first);
        assertEquals(this.list.get(0), first);
    }

    @Test
    public void insertsMany() throws ArrayIndexOutOfBoundsException {
        Node first = new Node(1);
        Node middle = new Node(2);
        Node last = new Node(3);

        this.list.insert(0, first);
        this.list.insert(1, middle);
        this.list.insert(2, last);

        assertEquals(this.list.get(1), middle);
        assertEquals(this.list.get(2), last);
    }

    @Test
    public void insertsBetweenExistentPositions() throws ArrayIndexOutOfBoundsException {
        Node first = new Node(1);
        Node middle = new Node(2);
        Node last = new Node(3);
        Node rebel = new Node(4);

        this.list.insert(0, first);
        this.list.insert(1, middle);
        this.list.insert(2, last);

        // Inserts the node in the middle position
        this.list.insert(1, rebel);

        assertEquals(this.list.getSize(), 4);
        assertEquals(this.list.get(1), rebel);
        assertEquals(this.list.get(2), middle);
    }

    @Test
    public void insertsInFirstPositionWithNonEmptyList() throws ArrayIndexOutOfBoundsException {
        Node first = new Node(1);
        Node last = new Node(2);
        Node rebel = new Node(3);

        this.list.insert(0, first);
        this.list.insert(1, last);

        // Inserts the node in the middle position
        this.list.insert(0, rebel);

        assertEquals(this.list.getSize(), 3);
        assertEquals(this.list.get(0), rebel);
        assertEquals(this.list.get(1), first);
    }

    @Test
    public void removeMiddlePosition() throws ArrayIndexOutOfBoundsException {
        Node first = new Node(1);
        Node middle = new Node(2);
        Node last = new Node(3);

        this.list.insert(0, first);
        this.list.insert(1, middle);
        this.list.insert(2, last);

        assertEquals(this.list.remove(1), middle);
        assertEquals(this.list.get(0), this.list.getInitial());
        assertEquals(this.list.get(1), last);
    }

    @Test
    public void removeLastPosition() throws ArrayIndexOutOfBoundsException {
        Node first = new Node(1);
        Node middle = new Node(2);
        Node last = new Node(3);

        this.list.insert(0, first);
        this.list.insert(1, middle);
        this.list.insert(2, last);

        assertEquals(this.list.remove(2), last);
        assertEquals(this.list.get(0), this.list.getInitial());
        assertEquals(this.list.get(1), middle);
    }

    @Test
    public void removeFirstPosition() throws ArrayIndexOutOfBoundsException {
        Node first = new Node(1);
        Node middle = new Node(2);
        Node last = new Node(3);

        this.list.insert(0, first);
        this.list.insert(1, middle);
        this.list.insert(2, last);

        assertEquals(this.list.remove(0), first);
        assertEquals(this.list.get(0), this.list.getInitial());
        assertEquals(this.list.get(1), last);
    }

    @Test
    public void countListLength() throws ArrayIndexOutOfBoundsException {
        Node first = new Node(1);
        Node middle = new Node(2);
        Node last = new Node(3);

        this.list.insert(0, first);
        this.list.insert(1, middle);
        this.list.insert(2, last);

        assertEquals(this.list.getSize(), 3);
    }

    @Test
    public void blockInsertWithNegativePosition() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> this.list.insert(-1, new Node(1)));
    }

    @Test
    public void blockInsertWithOutOfListBounds() {
        this.list.insert(0, new Node(1));
        int invalidPosition = this.list.getSize() + 1;

        assertThrows(ArrayIndexOutOfBoundsException.class, () -> this.list.insert(invalidPosition, new Node(1)));
    }
}
