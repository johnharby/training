package com.apple;

import org.assertj.core.internal.FieldByFieldComparator;
import org.eclipse.jetty.util.IncludeExclude;
import org.junit.Ignore;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class MyArrayListTest {
    /**
     * Method under test: {@link MyArrayList.ArrayListSpliterator#characteristics()}
     */
    @Test
    public void testArrayListSpliteratorCharacteristics() {
        assertEquals(16464, (new MyArrayList.ArrayListSpliterator<>(new MyArrayList<>(), 1, 1, 3)).characteristics());
    }

    /**
     * Method under test: {@link MyArrayList.ArrayListSpliterator#ArrayListSpliterator(MyArrayList, int, int, int)}
     */
    @Test
    public void testArrayListSpliteratorConstructor() {
        MyArrayList.ArrayListSpliterator<Object> actualArrayListSpliterator = new MyArrayList.ArrayListSpliterator<>(
                new MyArrayList<>(), 1, 1, 3);

        assertEquals(16464, actualArrayListSpliterator.characteristics());
        assertEquals(0L, actualArrayListSpliterator.getExactSizeIfKnown());
    }

    /**
     * Method under test: {@link MyArrayList.ArrayListSpliterator#estimateSize()}
     */
    @Test
    public void testArrayListSpliteratorEstimateSize() {
        assertEquals(0L, (new MyArrayList.ArrayListSpliterator<>(new MyArrayList<>(), 1, 1, 3)).estimateSize());
    }

    /**
     * Method under test: {@link MyArrayList.ArrayListSpliterator#estimateSize()}
     */
    @Test
    public void testArrayListSpliteratorEstimateSize2() {
        MyArrayList.ArrayListSpliterator<Object> arrayListSpliterator = new MyArrayList.ArrayListSpliterator<>(
                new MyArrayList<>(), 1, -1, 3);
        assertEquals(-1L, arrayListSpliterator.estimateSize());
        assertEquals(-1L, arrayListSpliterator.getExactSizeIfKnown());
    }

    /**
     * Method under test: {@link MyArrayList.ArrayListSpliterator#forEachRemaining(Consumer)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testArrayListSpliteratorForEachRemaining() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.apple.MyArrayList$ArrayListSpliterator.forEachRemaining(MyArrayList.java:1201)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList.ArrayListSpliterator<>(new MyArrayList<>(), 1, 1, 3)).forEachRemaining(null);
    }

    /**
     * Method under test: {@link MyArrayList.ArrayListSpliterator#tryAdvance(Consumer)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testArrayListSpliteratorTryAdvance() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.apple.MyArrayList$ArrayListSpliterator.tryAdvance(MyArrayList.java:1184)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList.ArrayListSpliterator<>(new MyArrayList<>(), 1, 1, 3)).tryAdvance(null);
    }

    /**
     * Method under test: {@link MyArrayList.ArrayListSpliterator#trySplit()}
     */
    @Test
    public void testArrayListSpliteratorTrySplit() {
        assertNull((new MyArrayList.ArrayListSpliterator<>(new MyArrayList<>(), 1, 1, 3)).trySplit());
    }

    /**
     * Method under test: {@link MyArrayList.ArrayListSpliterator#trySplit()}
     */
    @Test
    public void testArrayListSpliteratorTrySplit2() {
        MyArrayList.ArrayListSpliterator<Object> arrayListSpliterator = new MyArrayList.ArrayListSpliterator<>(
                new MyArrayList<>(), -1, 1, 3);
        assertEquals(1L, arrayListSpliterator.trySplit().getExactSizeIfKnown());
        assertEquals(1L, arrayListSpliterator.getExactSizeIfKnown());
    }

    /**
     * Method under test: {@link MyArrayList.ArrayListSpliterator#trySplit()}
     */
    @Test
    public void testArrayListSpliteratorTrySplit3() {
        MyArrayList.ArrayListSpliterator<Object> arrayListSpliterator = new MyArrayList.ArrayListSpliterator<>(
                new MyArrayList<>(), 1, -1, 3);
        assertNull(arrayListSpliterator.trySplit());
        assertEquals(-1L, arrayListSpliterator.getExactSizeIfKnown());
    }

    /**
     * Method under test: {@link MyArrayList#trimToSize()}
     */
    @Test
    public void testTrimToSize() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new MyArrayList<>()).trimToSize();
    }

    /**
     * Method under test: {@link MyArrayList#trimToSize()}
     */
    @Test
    public void testTrimToSize2() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        objectList.trimToSize();
    }

    /**
     * Method under test: {@link MyArrayList#ensureCapacity(int)}
     */
    @Test
    public void testEnsureCapacity() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new MyArrayList<>()).ensureCapacity(1);
    }

    /**
     * Method under test: {@link MyArrayList#ensureCapacity(int)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testEnsureCapacity2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.OutOfMemoryError: Java heap space
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList<>()).ensureCapacity(2147483639);
    }

    /**
     * Method under test: {@link MyArrayList#ensureCapacity(int)}
     */
    @Test
    public void testEnsureCapacity3() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        objectList.ensureCapacity(1);
    }

    /**
     * Method under test: {@link MyArrayList#isEmpty()}
     */
    @Test
    public void testIsEmpty() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        assertFalse(objectList.isEmpty());
    }

    /**
     * Method under test: {@link MyArrayList#contains(Object)}
     */
    @Test
    public void testContains() {
        assertFalse((new MyArrayList<>()).contains("42"));
        assertFalse((new MyArrayList<>()).contains(null));
    }

    /**
     * Method under test: {@link MyArrayList#contains(Object)}
     */
    @Test
    public void testContains2() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        assertTrue(objectList.contains("42"));
    }

    /**
     * Method under test: {@link MyArrayList#contains(Object)}
     */
    @Test
    public void testContains3() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add(2);
        assertFalse(objectList.contains("42"));
    }

    /**
     * Method under test: {@link MyArrayList#contains(Object)}
     */
    @Test
    public void testContains4() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        assertFalse(objectList.contains(null));
    }

    /**
     * Method under test: {@link MyArrayList#contains(Object)}
     */
    @Test
    public void testContains5() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add(null);
        assertTrue(objectList.contains(null));
    }

    /**
     * Method under test: {@link MyArrayList#indexOf(Object)}
     */
    @Test
    public void testIndexOf() {
        assertEquals(-1, (new MyArrayList<>()).indexOf("42"));
        assertEquals(-1, (new MyArrayList<>()).indexOf(null));
    }

    /**
     * Method under test: {@link MyArrayList#indexOf(Object)}
     */
    @Test
    public void testIndexOf2() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        assertEquals(0, objectList.indexOf("42"));
    }

    /**
     * Method under test: {@link MyArrayList#indexOf(Object)}
     */
    @Test
    public void testIndexOf3() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add(2);
        assertEquals(-1, objectList.indexOf("42"));
    }

    /**
     * Method under test: {@link MyArrayList#indexOf(Object)}
     */
    @Test
    public void testIndexOf4() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        assertEquals(-1, objectList.indexOf(null));
    }

    /**
     * Method under test: {@link MyArrayList#indexOf(Object)}
     */
    @Test
    public void testIndexOf5() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add(null);
        assertEquals(0, objectList.indexOf(null));
    }

    /**
     * Method under test: {@link MyArrayList#lastIndexOf(Object)}
     */
    @Test
    public void testLastIndexOf() {
        assertEquals(-1, (new MyArrayList<>()).lastIndexOf("42"));
        assertEquals(-1, (new MyArrayList<>()).lastIndexOf(null));
    }

    /**
     * Method under test: {@link MyArrayList#lastIndexOf(Object)}
     */
    @Test
    public void testLastIndexOf2() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        assertEquals(0, objectList.lastIndexOf("42"));
    }

    /**
     * Method under test: {@link MyArrayList#lastIndexOf(Object)}
     */
    @Test
    public void testLastIndexOf3() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add(2);
        assertEquals(-1, objectList.lastIndexOf("42"));
    }

    /**
     * Method under test: {@link MyArrayList#lastIndexOf(Object)}
     */
    @Test
    public void testLastIndexOf4() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        assertEquals(-1, objectList.lastIndexOf(null));
    }

    /**
     * Method under test: {@link MyArrayList#lastIndexOf(Object)}
     */
    @Test
    public void testLastIndexOf5() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add(null);
        assertEquals(0, objectList.lastIndexOf(null));
    }

    /**
     * Method under test: {@link MyArrayList#clone()}
     */
    @Test
    public void testClone() {
        assertTrue(((MyArrayList<Object>) (new MyArrayList<>()).clone()).isEmpty());
    }

    /**
     * Method under test: {@link MyArrayList#toArray()}
     */
    @Test
    public void testToArray() {
        assertEquals(0, (new MyArrayList<>()).toArray().length);
        assertEquals(1, (new MyArrayList<>()).toArray(new Object[]{"42"}).length);
    }

    /**
     * Method under test: {@link MyArrayList#toArray(Object[])}
     */
    @Test
    public void testToArray2() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        assertEquals(1, objectList.toArray(new Object[]{"42"}).length);
    }

    /**
     * Method under test: {@link MyArrayList#toArray(Object[])}
     */
    @Test
    public void testToArray3() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        objectList.add("42");
        assertEquals(2, objectList.toArray(new Object[]{"42"}).length);
    }

    /**
     * Method under test: {@link MyArrayList#elementData(int)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testElementData() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 0
        //       at com.apple.MyArrayList.elementData(MyArrayList.java:318)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList<>()).elementData(1);
    }

    /**
     * Method under test: {@link MyArrayList#elementData(int)}
     */
    @Test
    public void testElementData2() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        assertNull(objectList.elementData(1));
    }

    /**
     * Method under test: {@link MyArrayList#get(int)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testGet() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: Index: 1, Size: 0
        //       at com.apple.MyArrayList.rangeCheck(MyArrayList.java:553)
        //       at com.apple.MyArrayList.get(MyArrayList.java:329)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList<>()).get(1);
    }

    /**
     * Method under test: {@link MyArrayList#get(int)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testGet2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 0
        //       at com.apple.MyArrayList.elementData(MyArrayList.java:318)
        //       at com.apple.MyArrayList.get(MyArrayList.java:331)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList<>()).get(-1);
    }

    /**
     * Method under test: {@link MyArrayList#get(int)}
     */
    @Test
    public void testGet3() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        objectList.add("42");
        assertEquals("42", objectList.get(1));
    }

    /**
     * Method under test: {@link MyArrayList#set(int, Object)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testSet() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: Index: 1, Size: 0
        //       at com.apple.MyArrayList.rangeCheck(MyArrayList.java:553)
        //       at com.apple.MyArrayList.set(MyArrayList.java:344)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList<>()).set(1, "Element");
    }

    /**
     * Method under test: {@link MyArrayList#set(int, Object)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testSet2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 0
        //       at com.apple.MyArrayList.elementData(MyArrayList.java:318)
        //       at com.apple.MyArrayList.set(MyArrayList.java:346)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList<>()).set(-1, "Element");
    }

    /**
     * Method under test: {@link MyArrayList#set(int, Object)}
     */
    @Test
    public void testSet3() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        objectList.add("42");
        assertEquals("42", objectList.set(1, "Element"));
    }

    /**
     * Method under test: {@link MyArrayList#add(int, Object)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testAdd() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: Index: 1, Size: 0
        //       at com.apple.MyArrayList.rangeCheckForAdd(MyArrayList.java:561)
        //       at com.apple.MyArrayList.add(MyArrayList.java:373)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList<>()).add(1, "Element");
    }

    /**
     * Method under test: {@link MyArrayList#add(int, Object)}
     */
    @Test
    public void testAdd2() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new MyArrayList<>()).add(0, "42");
    }

    /**
     * Method under test: {@link MyArrayList#add(int, Object)}
     */
    @Test
    public void testAdd3() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        objectList.add(1, "Element");
    }

    /**
     * Method under test: {@link MyArrayList#add(int, Object)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testAdd4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: Index: -1, Size: 0
        //       at com.apple.MyArrayList.rangeCheckForAdd(MyArrayList.java:561)
        //       at com.apple.MyArrayList.add(MyArrayList.java:373)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList<>()).add(-1, "Element");
    }

    /**
     * Method under test: {@link MyArrayList#add(Object)}
     */
    @Test
    public void testAdd5() {
        assertTrue((new MyArrayList<>()).add("42"));
    }

    /**
     * Method under test: {@link MyArrayList#add(Object)}
     */
    @Test
    public void testAdd6() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        assertTrue(objectList.add("42"));
    }

    /**
     * Method under test: {@link MyArrayList#remove(int)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testRemove() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: Index: 1, Size: 0
        //       at com.apple.MyArrayList.rangeCheck(MyArrayList.java:553)
        //       at com.apple.MyArrayList.remove(MyArrayList.java:392)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList<>()).remove(1);
    }

    /**
     * Method under test: {@link MyArrayList#remove(int)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testRemove2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 0
        //       at com.apple.MyArrayList.elementData(MyArrayList.java:318)
        //       at com.apple.MyArrayList.remove(MyArrayList.java:395)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList<>()).remove(-1);
    }

    /**
     * Method under test: {@link MyArrayList#remove(int)}
     */
    @Test
    public void testRemove3() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        objectList.add("42");
        assertEquals("42", objectList.remove(1));
    }

    /**
     * Method under test: {@link MyArrayList#remove(int)}
     */
    @Test
    public void testRemove4() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        objectList.add("42");
        objectList.add("42");
        assertEquals("42", objectList.remove(1));
    }

    /**
     * Method under test: {@link MyArrayList#remove(Object)}
     */
    @Test
    public void testRemove5() {
        assertFalse((new MyArrayList<>()).remove("42"));
    }

    /**
     * Method under test: {@link MyArrayList#remove(Object)}
     */
    @Test
    public void testRemove6() {
        assertFalse((new MyArrayList<>()).remove((Object) null));
    }

    /**
     * Method under test: {@link MyArrayList#remove(Object)}
     */
    @Test
    public void testRemove7() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        assertTrue(objectList.remove("42"));
    }

    /**
     * Method under test: {@link MyArrayList#remove(Object)}
     */
    @Test
    public void testRemove8() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        objectList.add("42");
        assertTrue(objectList.remove("42"));
    }

    /**
     * Method under test: {@link MyArrayList#remove(Object)}
     */
    @Test
    public void testRemove9() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add(2);
        assertFalse(objectList.remove("42"));
    }

    /**
     * Method under test: {@link MyArrayList#remove(Object)}
     */
    @Test
    public void testRemove10() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        assertFalse(objectList.remove((Object) null));
    }

    /**
     * Method under test: {@link MyArrayList#remove(Object)}
     */
    @Test
    public void testRemove11() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add(null);
        assertTrue(objectList.remove((Object) null));
    }

    /**
     * Method under test: {@link MyArrayList#clear()}
     */
    @Test
    public void testClear() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new MyArrayList<>()).clear();
    }

    /**
     * Method under test: {@link MyArrayList#clear()}
     */
    @Test
    public void testClear2() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        objectList.clear();
    }

    /**
     * Method under test: {@link MyArrayList#addAll(int, Collection)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testAddAll() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: Index: 1, Size: 0
        //       at com.apple.MyArrayList.rangeCheckForAdd(MyArrayList.java:561)
        //       at com.apple.MyArrayList.addAll(MyArrayList.java:501)
        //   See https://diff.blue/R013 to resolve this issue.

        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.addAll(1, new ArrayList<>());
    }

    /**
     * Method under test: {@link MyArrayList#addAll(int, Collection)}
     */
    @Test
    public void testAddAll2() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        assertFalse(objectList.addAll(0, new ArrayList<>()));
    }

    /**
     * Method under test: {@link MyArrayList#addAll(int, Collection)}
     */
    @Test
    public void testAddAll3() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        assertFalse(objectList.addAll(1, new ArrayList<>()));
    }

    /**
     * Method under test: {@link MyArrayList#addAll(int, Collection)}
     */
    @Test
    public void testAddAll4() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        objectList.add("42");
        assertFalse(objectList.addAll(1, new ArrayList<>()));
    }

    /**
     * Method under test: {@link MyArrayList#addAll(int, Collection)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testAddAll5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: Index: -1, Size: 0
        //       at com.apple.MyArrayList.rangeCheckForAdd(MyArrayList.java:561)
        //       at com.apple.MyArrayList.addAll(MyArrayList.java:501)
        //   See https://diff.blue/R013 to resolve this issue.

        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.addAll(-1, new ArrayList<>());
    }

    /**
     * Method under test: {@link MyArrayList#addAll(int, Collection)}
     */
    @Test
    public void testAddAll6() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");

        ArrayList<Object> objectList1 = new ArrayList<>();
        objectList1.add("42");
        assertTrue(objectList.addAll(1, objectList1));
    }

    /**
     * Method under test: {@link MyArrayList#addAll(Collection)}
     */
    @Test
    public void testAddAll7() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        assertFalse(objectList.addAll(new ArrayList<>()));
    }

    /**
     * Method under test: {@link MyArrayList#addAll(Collection)}
     */
    @Test
    public void testAddAll8() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        assertFalse(objectList.addAll(new ArrayList<>()));
    }

    /**
     * Method under test: {@link MyArrayList#addAll(Collection)}
     */
    @Test
    public void testAddAll9() {
        MyArrayList<Object> objectList = new MyArrayList<>();

        ArrayList<Object> objectList1 = new ArrayList<>();
        objectList1.add("42");
        assertTrue(objectList.addAll(objectList1));
    }

    /**
     * Method under test: {@link MyArrayList#removeRange(int, int)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testRemoveRange() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ArrayIndexOutOfBoundsException: arraycopy: length -1 is negative
        //       at java.lang.System.arraycopy(Native Method)
        //       at com.apple.MyArrayList.removeRange(MyArrayList.java:534)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList<>()).removeRange(1, 1);
    }

    /**
     * Method under test: {@link MyArrayList#removeRange(int, int)}
     */
    @Test
    public void testRemoveRange2() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        objectList.removeRange(1, 1);
    }

    /**
     * Method under test: {@link MyArrayList#removeRange(int, int)}
     */
    @Test
    public void testRemoveRange3() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        objectList.removeRange(0, 1);
    }

    /**
     * Method under test: {@link MyArrayList#removeAll(Collection)}
     */
    @Test
    public void testRemoveAll() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        assertFalse(objectList.removeAll(new ArrayList<>()));
    }

    /**
     * Method under test: {@link MyArrayList#removeAll(Collection)}
     */
    @Test
    public void testRemoveAll2() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        assertFalse(objectList.removeAll(new ArrayList<>()));
    }

    /**
     * Method under test: {@link MyArrayList#removeAll(Collection)}
     */
    @Test
    public void testRemoveAll3() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");

        ArrayList<Object> objectList1 = new ArrayList<>();
        objectList1.add("42");
        assertTrue(objectList.removeAll(objectList1));
    }

    /**
     * Method under test: {@link MyArrayList#retainAll(Collection)}
     */
    @Test
    public void testRetainAll() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        assertFalse(objectList.retainAll(new ArrayList<>()));
    }

    /**
     * Method under test: {@link MyArrayList#retainAll(Collection)}
     */
    @Test
    public void testRetainAll2() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        assertTrue(objectList.retainAll(new ArrayList<>()));
    }

    /**
     * Method under test: {@link MyArrayList#retainAll(Collection)}
     */
    @Test
    public void testRetainAll3() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");

        ArrayList<Object> objectList1 = new ArrayList<>();
        objectList1.add("42");
        assertFalse(objectList.retainAll(objectList1));
    }

    /**
     * Method under test: {@link MyArrayList#listIterator()}
     */
    @Test
    public void testListIterator() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new MyArrayList<>()).listIterator();
    }

    /**
     * Method under test: {@link MyArrayList#listIterator(int)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testListIterator2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: Index: 1
        //       at com.apple.MyArrayList.listIterator(MyArrayList.java:710)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList<>()).listIterator(1);
    }

    /**
     * Method under test: {@link MyArrayList#listIterator(int)}
     */
    @Test
    public void testListIterator3() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new MyArrayList<>()).listIterator(0);
    }

    /**
     * Method under test: {@link MyArrayList#listIterator(int)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testListIterator4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: Index: -1
        //       at com.apple.MyArrayList.listIterator(MyArrayList.java:710)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList<>()).listIterator(-1);
    }

    /**
     * Method under test: {@link MyArrayList#iterator()}
     */
    @Test
    public void testIterator() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        (new MyArrayList<>()).iterator();
    }

    /**
     * Method under test: {@link MyArrayList#subList(int, int)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testSubList() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: toIndex = 1
        //       at com.apple.MyArrayList.subListRangeCheck(MyArrayList.java:875)
        //       at com.apple.MyArrayList.subList(MyArrayList.java:867)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList<>()).subList(1, 1);
    }

    /**
     * Method under test: {@link MyArrayList#subList(int, int)}
     */
    @Test
    public void testSubList2() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        assertTrue(objectList.subList(1, 1).isEmpty());
    }

    /**
     * Method under test: {@link MyArrayList#subList(int, int)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testSubList3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: fromIndex = -1
        //       at com.apple.MyArrayList.subListRangeCheck(MyArrayList.java:873)
        //       at com.apple.MyArrayList.subList(MyArrayList.java:867)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList<>()).subList(-1, 1);
    }

    /**
     * Method under test: {@link MyArrayList#subList(int, int)}
     */
    @Test
    public void testSubList4() {
        assertThrows(IllegalArgumentException.class, () -> (new MyArrayList<>()).subList(1, 0));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link MyArrayList#subListRangeCheck(int, int, int)}
     *   <li>{@link MyArrayList#subListRangeCheck(int, int, int)}
     * </ul>
     */
    @Test
    public void testSubListRangeCheck() {
        // TODO: Complete this test.
        //   Diffblue AI was unable to find a test

        MyArrayList.subListRangeCheck(1, 1, 3);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link MyArrayList#subListRangeCheck(int, int, int)}
     *   <li>{@link MyArrayList#subListRangeCheck(int, int, int)}
     * </ul>
     */
    @Test
    public void testSubListRangeCheck2() {
        assertThrows(IllegalArgumentException.class, () -> MyArrayList.subListRangeCheck(3, 1, 3));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link MyArrayList#subListRangeCheck(int, int, int)}
     *   <li>{@link MyArrayList#subListRangeCheck(int, int, int)}
     * </ul>
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testSubListRangeCheck3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: fromIndex = -1
        //       at com.apple.MyArrayList.subListRangeCheck(MyArrayList.java:873)
        //   See https://diff.blue/R013 to resolve this issue.

        MyArrayList.subListRangeCheck(-1, 1, 3);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link MyArrayList#subListRangeCheck(int, int, int)}
     *   <li>{@link MyArrayList#subListRangeCheck(int, int, int)}
     * </ul>
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testSubListRangeCheck4() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IndexOutOfBoundsException: toIndex = 1
        //       at com.apple.MyArrayList.subListRangeCheck(MyArrayList.java:875)
        //   See https://diff.blue/R013 to resolve this issue.

        MyArrayList.subListRangeCheck(1, 1, 0);
    }

    /**
     * Method under test: {@link MyArrayList#forEach(Consumer)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testForEach() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.util.Objects.requireNonNull(Objects.java:221)
        //       at com.apple.MyArrayList.forEach(MyArrayList.java:1114)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList<>()).forEach(null);
    }

    /**
     * Method under test: {@link MyArrayList#spliterator()}
     */
    @Test
    public void testSpliterator() {
        assertEquals(0L, (new MyArrayList<>()).spliterator().getExactSizeIfKnown());
    }

    /**
     * Method under test: {@link MyArrayList#removeIf(Predicate)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testRemoveIf() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.util.Objects.requireNonNull(Objects.java:221)
        //       at com.apple.MyArrayList.removeIf(MyArrayList.java:1232)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList<>()).removeIf(null);
    }

    /**
     * Method under test: {@link MyArrayList#removeIf(Predicate)}
     */
    @Test
    public void testRemoveIf2() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        assertFalse(objectList.removeIf(new IncludeExclude<>()));
    }

    /**
     * Method under test: {@link MyArrayList#removeIf(Predicate)}
     */
    @Test
    public void testRemoveIf3() {
        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        assertTrue(objectList.removeIf(new IncludeExclude<>()));
    }

    /**
     * Method under test: {@link MyArrayList#replaceAll(UnaryOperator)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testReplaceAll() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.util.Objects.requireNonNull(Objects.java:221)
        //       at com.apple.MyArrayList.replaceAll(MyArrayList.java:1276)
        //   See https://diff.blue/R013 to resolve this issue.

        (new MyArrayList<>()).replaceAll(null);
    }

    /**
     * Method under test: {@link MyArrayList#sort(Comparator)}
     */
    @Test
    public void testSort() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     FieldByFieldComparator.comparatorsByPropertyOrField
        //     FieldByFieldComparator.comparatorsByType

        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.sort(new FieldByFieldComparator());
    }

    /**
     * Method under test: {@link MyArrayList#sort(Comparator)}
     */
    @Test
    public void testSort2() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     FieldByFieldComparator.comparatorsByPropertyOrField
        //     FieldByFieldComparator.comparatorsByType

        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add("42");
        objectList.add("42");
        objectList.sort(new FieldByFieldComparator());
    }

    /**
     * Method under test: {@link MyArrayList#sort(Comparator)}
     */
    @Test
    public void testSort3() {
        // TODO: Complete this test.
        //   Reason: R002 Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     FieldByFieldComparator.comparatorsByPropertyOrField
        //     FieldByFieldComparator.comparatorsByType

        MyArrayList<Object> objectList = new MyArrayList<>();
        objectList.add(2);
        objectList.add("42");
        objectList.sort(new FieldByFieldComparator());
    }

    /**
     * Method under test: {@link MyArrayList#MyArrayList()}
     */
    @Test
    public void testConstructor() {
        assertTrue((new MyArrayList<>()).isEmpty());
        assertTrue((new MyArrayList<>(1)).isEmpty());
        assertThrows(IllegalArgumentException.class, () -> new MyArrayList<>(-1));
        assertThrows(OutOfMemoryError.class, () -> new MyArrayList<>(2147483639));
        assertTrue((new MyArrayList<>(new ArrayList<>())).isEmpty());
    }
}

