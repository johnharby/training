package com.learn;

import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node front;
    private Node back;

    // construct an empty deque
    public Deque() {
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("addFirst called with null item");
        }
        Node prev = front;
        front = new Node();
        front.next = prev;
        front.item = item;
        size++;
        if (back == null) {
            back = front;
        }
        else {
            front.next.prev = front;
        }
    }
    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("addLast called with null item");
        }
        Node prior = back;
        back = new Node();
        back.item = item;
        back.prev = prior;
        size++;
        if (front == null) {
            front = back;
        }
        else {
            back.prev.next = back;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty - can't remove first");
        }
        Item item = front.item;
        size--;
        if (size == 0) {
            back = null;
            front = null;
        }
        else {
            front = front.next;
            front.prev = null;
        }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty - can't remove last");
        }
        Item item = back.item;
        size--;
        if (size == 0) {
            front = null;
            back = null;
        }
        else {
            back = back.prev;
            back.next = null;
        }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("testfirst");
        deque.addFirst("testfirst2");
        deque.addLast("testlast");
        deque.addLast("testlast2");
        System.out.println("Size is:  " + deque.size());

        for (String s : deque) {
            System.out.println(s);
        }

        System.out.println(deque.removeFirst() + " " + deque.removeLast());
    }

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    private class DequeIterator<Item> implements Iterator<Item> {
        private Node node = front;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Iterator found no elements");
            }
            Item item = (Item) node.item;
            node = node.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}