import org.junit.Before;
import org.junit.Test;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Iterator;
import static org.junit.Assert.*;

public class MinPQTest {

    private MinPQ<Integer> minPQ;

    @Before
    public void setUp() {
        minPQ = new MinPQ<>();
    }

    @Test
    public void testInsertAndDelMin() {
        minPQ.insert(3);
        minPQ.insert(1);
        minPQ.insert(4);
        minPQ.insert(2);

        assertEquals(1, (int) minPQ.delMin());
        assertEquals(2, (int) minPQ.delMin());
        assertEquals(3, (int) minPQ.delMin());
        assertEquals(4, (int) minPQ.delMin());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(minPQ.isEmpty());
        minPQ.insert(1);
        assertFalse(minPQ.isEmpty());
        minPQ.delMin();
        assertTrue(minPQ.isEmpty());
    }

    @Test
    public void testSize() {
        assertEquals(0, minPQ.size());
        minPQ.insert(1);
        assertEquals(1, minPQ.size());
        minPQ.insert(2);
        assertEquals(2, minPQ.size());
        minPQ.delMin();
        assertEquals(1, minPQ.size());
    }

    @Test
    public void testMin() {
        minPQ.insert(5);
        minPQ.insert(2);
        minPQ.insert(8);
        assertEquals(2, (int) minPQ.min());
    }

    @Test(expected = NoSuchElementException.class)
    public void testMinThrowsExceptionWhenEmpty() {
        minPQ.min();
    }

    @Test(expected = NoSuchElementException.class)
    public void testDelMinThrowsExceptionWhenEmpty() {
        minPQ.delMin();
    }

    @Test
    public void testConstructorWithInitialCapacity() {
        MinPQ<Integer> minPQWithCapacity = new MinPQ<>(5);
        minPQWithCapacity.insert(3);
        minPQWithCapacity.insert(1);
        assertEquals(1, (int) minPQWithCapacity.delMin());
    }

    @Test
    public void testConstructorWithComparator() {
        Comparator<Integer> reverseComparator = (a, b) -> b - a;
        MinPQ<Integer> minPQWithComparator = new MinPQ<>(reverseComparator);
        minPQWithComparator.insert(3);
        minPQWithComparator.insert(1);
        assertEquals(3, (int) minPQWithComparator.delMin());
    }

    @Test
    public void testConstructorWithArray() {
        Integer[] keys = {4, 2, 5, 1, 3};
        MinPQ<Integer> minPQFromArray = new MinPQ<>(keys);
        assertEquals(1, (int) minPQFromArray.delMin());
        assertEquals(2, (int) minPQFromArray.delMin());
        assertEquals(3, (int) minPQFromArray.delMin());
        assertEquals(4, (int) minPQFromArray.delMin());
        assertEquals(5, (int) minPQFromArray.delMin());
    }

    @Test
    public void testIterator() {
        minPQ.insert(3);
        minPQ.insert(1);
        minPQ.insert(4);
        minPQ.insert(2);

        Iterator<Integer> iterator = minPQ.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, (int) iterator.next());
        assertEquals(2, (int) iterator.next());
        assertEquals(3, (int) iterator.next());
        assertEquals(4, (int) iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorRemoveThrowsException() {
        minPQ.insert(1);
        Iterator<Integer> iterator = minPQ.iterator();
        iterator.next();
        iterator.remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNextThrowsExceptionWhenNoMoreElements() {
        minPQ.insert(1);
        Iterator<Integer> iterator = minPQ.iterator();
        iterator.next();
        iterator.next();
    }
}
