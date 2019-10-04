package com.org.chained_list;

import com.org.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleChainedListTest {
    private DoubleChainedList<Integer> list;

    @BeforeEach
    public void setUp() {
        this.list = new DoubleChainedList<>();
    }

    @AfterEach
    public void teardown() {
        this.list.clear();
        this.list = null;
    }

    @Test
    public void insertFirstPositionWhenEmpty() {
        Node<Integer> first = new Node<>(1);
        this.list.insert(0, first);

        assertEquals(this.list.getInitial(), this.list.getLast());
        assertEquals(this.list.get(0), first);
    }

    @Test
    public void plugFirstPositionWithExistingNode() {
        Node<Integer> willBecameLast = new Node<>(2);
        Node<Integer> willBecameFirst = new Node<>(1);

        this.list.insert(0, willBecameLast);
        this.list.insert(0, willBecameFirst);

        assertEquals(this.list.getInitial(), willBecameFirst);
        assertEquals(this.list.getLast(), willBecameLast);
        assertEquals(this.list.get(0), willBecameFirst);
        assertEquals(this.list.get(1), willBecameLast);
    }

    @Test
    public void insertsBetweenExistentPositions() {
        Node<Integer> first = new Node<>(1);
        Node<Integer> middle = new Node<>(2);
        Node<Integer> last = new Node<>(3);
        Node<Integer> rebel = new Node<>(4);

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
    public void sizeIncrementsOnInsert() {
        int expected = 3;

        this.list.insert(0, new Node<>(1));
        this.list.insert(1, new Node<>(2));
        this.list.insert(2, new Node<>(3));

        assertEquals(this.list.getSize(), expected);
    }

    @Test
    public void removeMiddlePosition() throws ArrayIndexOutOfBoundsException {
        Node<Integer> first = new Node<>(1);
        Node<Integer> middle = new Node<>(2);
        Node<Integer> last = new Node<>(3);

        this.list.insert(0, first);
        this.list.insert(1, middle);
        this.list.insert(2, last);

        assertEquals(this.list.remove(1), middle);
        assertEquals(this.list.get(0), this.list.getInitial());
        assertEquals(this.list.get(1), last);
    }

    @Test
    public void removeLastPosition() throws ArrayIndexOutOfBoundsException {
        Node<Integer> first = new Node<>(1);
        Node<Integer> middle = new Node<>(2);
        Node<Integer> last = new Node<>(3);

        this.list.insert(0, first);
        this.list.insert(1, middle);
        this.list.insert(2, last);

        assertEquals(this.list.remove(2), last);
        assertEquals(this.list.get(0), this.list.getInitial());
        assertEquals(this.list.get(1), middle);
    }

    @Test
    public void removeFirstPosition() throws ArrayIndexOutOfBoundsException {
        Node<Integer> first = new Node<>(1);
        Node<Integer> middle = new Node<>(2);
        Node<Integer> last = new Node<>(3);

        this.list.insert(0, first);
        this.list.insert(1, middle);
        this.list.insert(2, last);

        assertEquals(this.list.remove(0), first);
        assertEquals(this.list.get(0), this.list.getInitial());
        assertEquals(this.list.get(1), last);
    }

    @Test
    public void insertOnFirstPositionWhenEmpty() {
        Node<Integer> uniqueNode = new Node<>(1);
        this.list.insertLast(uniqueNode);

        assertEquals(this.list.getInitial(), uniqueNode);
        assertEquals(this.list.getLast(), uniqueNode);
    }

    @Test
    public void removeFirstPositionWithOneNode() {
        Node<Integer> node = new Node<>(1);

        this.list.insertLast(node);
        Node<Integer> removed = this.list.removeFirst();

        assertEquals(removed, node);
        assertNull(this.list.getLast());
        assertTrue(this.list.isEmpty());
    }

    @Test
    public void removeLastPositionWithOneNode() {
        Node<Integer> node = new Node<>(1);

        this.list.insertLast(node);
        Node<Integer> removed = this.list.removeLast();

        assertEquals(removed, node);
        assertNull(this.list.getLast());
        assertTrue(this.list.isEmpty());
    }

    @Test
    public void iterationMapEachItem() {
        Integer first = 1;
        Integer last = 2;

        list.insertLast(new Node<>(first));
        list.insertLast(new Node<>(last));

        int index = 0;
        for (Integer item : list) {
            switch (index) {
                case 0:
                    assertEquals(first, item);
                    break;
                case 1:
                    assertEquals(last, item);
                    break;
            }
            index++;
        }

        assertEquals(2, index);
    }
}
