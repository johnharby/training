package com.datastruct;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class ReverseTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Reverse.ListNode#ListNode(int)}
     *   <li>{@link Reverse.ListNode#setData(int)}
     *   <li>{@link Reverse.ListNode#setNext(Reverse.ListNode)}
     *   <li>{@link Reverse.ListNode#toString()}
     *   <li>{@link Reverse.ListNode#getData()}
     *   <li>{@link Reverse.ListNode#getNext()}
     * </ul>
     */
    @Test
    public void testListNodeConstructor() {
        Reverse.ListNode actualListNode = new Reverse.ListNode(1);
        actualListNode.setData(1);
        Reverse.ListNode listNode = new Reverse.ListNode(1);
        actualListNode.setNext(listNode);
        String actualToStringResult = actualListNode.toString();
        assertEquals(1, actualListNode.getData());
        Reverse.ListNode next = actualListNode.getNext();
        assertEquals(1, next.getData());
        assertSame(listNode, next);
        assertEquals("ListNode{data=1, next=ListNode{data=1, next=null}}", actualToStringResult);
    }

    /**
     * Method under test: {@link Reverse#reverse(Reverse.ListNode)}
     */
    @Test
    public void testReverse() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        Reverse.reverse(new Reverse.ListNode(1));
    }

    /**
     * Method under test: {@link Reverse#reverse(Reverse.ListNode)}
     */
    @Test
    public void testReverse2() {
        assertNull(Reverse.reverse(null));
    }

    /**
     * Method under test: {@link Reverse#reverse(Reverse.ListNode)}
     */
    @Test
    public void testReverse3() {
        Reverse.ListNode listNode = new Reverse.ListNode(1);
        listNode.setNext(new Reverse.ListNode(1));
        Reverse.ListNode actualReverseResult = Reverse.reverse(listNode);
        Reverse.ListNode expectedNext = actualReverseResult.next;
        Reverse.ListNode next = actualReverseResult.getNext();
        assertSame(expectedNext, next);
        assertNull(next.getNext());
    }

    /**
     * Method under test: {@link Reverse#reverseListRecursive(Reverse.ListNode)}
     */
    @Test
    public void testReverseListRecursive() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        Reverse.reverseListRecursive(new Reverse.ListNode(1));
    }

    /**
     * Method under test: {@link Reverse#reverseListRecursive(Reverse.ListNode)}
     */
    @Test
    public void testReverseListRecursive2() {
        assertNull(Reverse.reverseListRecursive(null));
    }

    /**
     * Method under test: {@link Reverse#reverseListRecursive(Reverse.ListNode)}
     */
    @Test
    public void testReverseListRecursive3() {
        Reverse.ListNode listNode = new Reverse.ListNode(1);
        listNode.setNext(new Reverse.ListNode(1));
        Reverse.ListNode actualReverseListRecursiveResult = Reverse.reverseListRecursive(listNode);
        Reverse.ListNode expectedNext = actualReverseListRecursiveResult.next;
        Reverse.ListNode next = actualReverseListRecursiveResult.getNext();
        assertSame(expectedNext, next);
        assertNull(next.getNext());
    }

    /**
     * Method under test: {@link Reverse#constructLinkedList(int[])}
     */
    @Test
    public void testConstructLinkedList() {
        Reverse.ListNode actualConstructLinkedListResult = Reverse.constructLinkedList(new int[]{1, 1, 1, 1});
        assertEquals(1, actualConstructLinkedListResult.getData());
        Reverse.ListNode expectedNext = actualConstructLinkedListResult.next;
        Reverse.ListNode next = actualConstructLinkedListResult.getNext();
        assertSame(expectedNext, next);
        assertEquals(1, next.getData());
        Reverse.ListNode next1 = next.getNext();
        assertEquals(1, next1.getData());
        Reverse.ListNode next2 = next1.getNext();
        assertEquals(1, next2.getData());
        assertNull(next2.getNext());
    }

    /**
     * Method under test: {@link Reverse#constructLinkedList(int[])}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testConstructLinkedList2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.datastruct.Reverse.constructLinkedList(Reverse.java:43)
        //   See https://diff.blue/R013 to resolve this issue.

        Reverse.constructLinkedList(null);
    }
}

