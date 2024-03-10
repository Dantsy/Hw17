package ru.otus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedianListTest {

    @Test
    void testSize() {
        MedianList<Integer> medianList = new MedianList<>();
        assertEquals(0, medianList.size());

        medianList.add(10);
        assertEquals(1, medianList.size());

        medianList.add(20);
        assertEquals(2, medianList.size());

        medianList.remove(10);
        assertEquals(1, medianList.size());

        medianList.remove(20);
        assertEquals(0, medianList.size());
    }

    @Test
    void testAdd() {
        MedianList<Integer> medianList = new MedianList<>();

        medianList.add(10);
        assertEquals("[10]", medianList.toString());

        medianList.add(20);
        assertEquals("[10, 20]", medianList.toString());

        medianList.add(30);
        assertEquals("[10, 20, 30]", medianList.toString());
    }

    @Test
    void testRemove() {
        MedianList<Integer> medianList = new MedianList<>();

        medianList.add(10);
        medianList.add(20);
        medianList.add(30);

        medianList.remove(20);
        assertEquals("[10, 30]", medianList.toString());

        medianList.remove(10);
        assertEquals("[30]", medianList.toString());

        medianList.remove(30);
        assertEquals("[]", medianList.toString());
    }

    @Test
    void testGetMedian() {
        MedianList<Integer> medianList = new MedianList<>();

        assertEquals(Double.NaN, medianList.getMedian());

        medianList.add(10);
        assertEquals(10.0, medianList.getMedian());

        medianList.add(20);
        assertEquals(15.0, medianList.getMedian());

        medianList.add(30);
        assertEquals(20.0, medianList.getMedian());

        medianList.remove(10);
        assertEquals(25.0, medianList.getMedian());

        medianList.remove(20);
        assertEquals(30.0, medianList.getMedian());

        medianList.remove(30);
        assertEquals(Double.NaN, medianList.getMedian());
    }
}