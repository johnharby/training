package com.interview;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayList implements List {
    private int[] elements;
    private int size;

    public ArrayList() {
        elements = new int[10];
    }

    public ArrayList(int initCapacity) {
        if (initCapacity < 0)
            throw new IllegalArgumentException("Invalid Capacity: "+
                    initCapacity);
        elements = new int[initCapacity];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return elements.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Integer)) {
            throw new IllegalArgumentException("This structure only supports int types");
        }
        int x = (int) o;
        for (int i = 0; i < elements.length; ++i) {
            if (elements[i] == x) {
                return true;
            }
        }
        return false;
    }

    public void append(int value) {
        int n = elements.length;
        if (n == size) {
            resize();
        }
        elements[size] = value;
    }

    @Override
    public Object set(int index, Object element) {
        return null;
    }

    private void resize() {
        int[] tmp = new int[elements.length * 2];
        System.arraycopy(elements, 0, tmp, 0, elements.length);
        elements = tmp;
    }

    @Override
    public void clear() {
        elements = new int[elements.length];
    }

    @Override
    public Object get(int index) {
        return elements[index];
    }


    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(Object o) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public void add(int index, Object element) {

    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    public static void main(String[] args) {
        ArrayList al = new ArrayList();
        al.append(10);
        System.out.println(al.get(0));
    }
}
