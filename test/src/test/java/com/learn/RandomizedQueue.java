package com.learn;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    @Override
    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("Attempt to enqueue null item");
        }
        if (size == items.length) resize(2 * items.length);
        if (size == 0) {
            items[size++] = item;
        }
        else {
            int idx = StdRandom.uniform(size);
            Item temp = items[idx];
            items[idx] = item;
            items[size++] = temp;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Trying to dequeue from empty RandomizeQueue");
        }
        if (size == items.length / 4) resize(items.length / 2);
        int idx = getRandom();
        Item item = items[idx];
        items[idx] = items[--size];
        items[size] = null;
        return item;
    }

    private void changeSize(boolean shrink) {
        if (!shrink) {
            if (size == items.length) {
                resize(2 * items.length);
            }
        }
        else {
            if (size == items.length/4 && size > 0) {
                resize(items.length/2);
            }
        }
    }

    private void resize(int capacity) {
        if (capacity >= size) {
            Item[] newItems = (Item[]) new Object[capacity];
            System.arraycopy(items, 0, newItems, 0, size);
            items = newItems;
        }
    }

    // return a random item (but do not remove it)
    public Item sample() {
        int idx = getRandom();
        return items[idx];
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        queue.enqueue("test1");
        queue.enqueue("test2");
        queue.enqueue("test3");
        String s = queue.dequeue();
        System.out.println(s);
        System.out.println("Sample is " + queue.sample());
        s = queue.dequeue();
        System.out.println(s);
        s = queue.dequeue();
        System.out.println(s);
    }

    private int getRandom() {
        return StdRandom.uniform(0, size);
    }

    private class RandomQueueIterator implements Iterator<Item> {
        private final Item[] iterItems;
        private int n;

        public RandomQueueIterator() {
            iterItems = (Item[]) new Object[size];
            System.arraycopy(items, 0, iterItems, 0, size);
        }

        @Override
        public boolean hasNext() {
            return n < size;
        }

        @Override
        public Item next() {
            if (n == size) {
                throw new NoSuchElementException();
            }
            if (hasNext()) {
                return iterItems[n++];
            }
            return null;
        }
    }
}
