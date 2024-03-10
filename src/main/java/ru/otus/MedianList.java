package ru.otus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public final class MedianList<T extends Number> {

    public static final Logger log = LoggerFactory.getLogger(Main.class.getSimpleName());

    private final List<T> elements = new LinkedList<>();

    public int size() {
        synchronized (this) {
            return elements.size();
        }
    }

    public void add(T item) {
        synchronized (this) {
            try {
                int index = Collections.binarySearch(elements, item, Comparator.comparingDouble(Number::doubleValue));
                if (index < 0) {
                    index = -index - 1;
                }
                elements.add(index, item);
                log.info("Element added in {} iterations", 1);
                log.info("Now collection contains %s".formatted(toString()));
            } catch (Exception e) {
                log.error("Error adding element to the list", e);
            }
        }
    }

    public int insertSorted(T item, int beginIndex, int endIndex, int iterationsNumber) {
        iterationsNumber++;
        if (elements.isEmpty()) {
            elements.add(item);
        } else if (beginIndex == endIndex) {
            if (((Comparable<T>) elements.get(beginIndex)).compareTo(item) < 0) {
                elements.add(beginIndex + 1, item);
            } else {
                elements.add(beginIndex, item);
            }
        } else {
            int middleIndex = beginIndex + (endIndex - beginIndex) / 2;
            if (((Comparable<T>) elements.get(middleIndex)).compareTo(item) < 0) {
                return insertSorted(item, beginIndex, Math.max(middleIndex - 1, beginIndex), iterationsNumber);
            } else {
                return insertSorted(item, middleIndex + 1, endIndex, iterationsNumber);
            }
        }
        return iterationsNumber;
    }

    public void remove(T item) {
        synchronized (this) {
            log.info("Start removing element {}", item);
            elements.remove(item);
            log.info("Now collection contains %s".formatted(toString()));
        }
    }

    public Double getMedian() {
        synchronized (this) {
            try {
                double median = 0D;

                if (elements.size() == 0) return Double.NaN;

                if (elements.size() % 2 == 0) {
                    median = ((Number) elements.get(elements.size() / 2 - 1)).doubleValue() + ((Number) elements.get(elements.size() / 2)).doubleValue();
                    median /= 2;
                } else {
                    median = ((Number) elements.get(elements.size() / 2)).doubleValue();
                }
                return median;
            } catch (Exception e) {
                log.error("Error calculating median", e);
                return Double.NaN;
            }
        }
    }

    @Override
    public String toString() {
        return elements.toString();
    }
}