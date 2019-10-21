package com.org.circle;

import com.org.Node;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleCircleListTest {
    private DoubleCircledList<Integer> list;

    @BeforeEach
    public void setUp() {
        this.list = new DoubleCircledList<>();
    }

    @AfterEach
    public void teardown() {
        this.list.clear();
        this.list = null;
    }

    @Test
    public void movesListBackwarding() {
        this.list.insertLast(new Node<>(1));
        this.list.insertLast(new Node<>(2));
        this.list.insertLast(new Node<>(3));
        this.list.insertLast(new Node<>(4));

        this.list.forwardTo(3);
        Node<Integer> third = this.list.backward();
        Node<Integer> second = this.list.backward();

        assertEquals(3, (int) third.getValue());
        assertEquals(2, (int) second.getValue());
    }

    @Test
    public void movesBackwardOnStartGoesToEnd() {
        this.list.insertLast(new Node<>(1));
        this.list.insertLast(new Node<>(2));

        Node<Integer> end = this.list.backward();

        assertEquals(2, (int) end.getValue());
    }

    @Test
    public void movesForwardAtEndGoesToBegin() {
        this.list.insertLast(new Node<>(1));
        this.list.insertLast(new Node<>(2));

        this.list.forwardTo(0);

        this.list.forwardTo(1);
        Node<Integer> first = this.list.forward();

        assertEquals(1, (int) first.getValue());
    }

    @Test
    public void traverseBackwardingToBegin() {
        this.list.insertLast(new Node<>(1));
        this.list.insertLast(new Node<>(2));
        this.list.insertLast(new Node<>(3));

        assertEquals(1, (int) this.list.get(-3).getValue());
        assertEquals(2, (int) this.list.get(1).getValue());
    }

    @Test
    public void traverseForwardingToBegin() {
        this.list.insertLast(new Node<>(1));
        this.list.insertLast(new Node<>(2));
        this.list.insertLast(new Node<>(3));

        assertEquals(1, (int) this.list.get(3).getValue());
        assertEquals(2, (int) this.list.get(1).getValue());
    }
}
