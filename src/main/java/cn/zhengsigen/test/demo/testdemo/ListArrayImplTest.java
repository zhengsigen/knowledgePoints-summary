package cn.zhengsigen.test.demo.testdemo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ListArrayImplTest {
    private DSList listArray;

    @Before
    public void start() {
        // listArray = new ImplementArray();
        //listArray = new ImplementLinked();
        listArray = new ImplementDoubleLinked();
    }

    @After
    public void end() {
        listArray = null;
    }

    @Test
    public void testAddObject() {
        listArray.add("a");
        assertEquals(1, listArray.size());
        listArray.add(1);
        assertEquals(2, listArray.size());
        listArray.add(true);
        assertEquals(3, listArray.size());
        listArray.add(false);
        assertEquals(4, listArray.size());
        listArray.add('a');
        assertEquals(5, listArray.size());
        assertArrayEquals(new Object[]{"a", 1, true, false, 'a'}, listArray.toArray());
    }

    @Test
    public void testAddIntObject() {
        assertEquals(0, listArray.size());
        listArray.add("a");
        assertEquals(1, listArray.size());
        listArray.add(1);
        assertEquals(2, listArray.size());
        listArray.add(true);
        assertEquals(3, listArray.size());
        listArray.add(false);
        assertEquals(4, listArray.size());
        listArray.add('a');
        assertEquals(5, listArray.size());
        listArray.add(2, "aa");
        assertEquals(6, listArray.size());
        assertArrayEquals(new Object[]{"a", 1, "aa", true, false, 'a'}, listArray.toArray());
    }

    @Test
    public void testAddAll() {
        assertArrayEquals(new Object[]{}, listArray.toArray());
        listArray.add("a");
        listArray.add("a1");
        listArray.add("a2");
        listArray.add("a3");
        assertArrayEquals(new Object[]{"a", "a1", "a2", "a3"}, listArray.toArray());
        listArray.add("a4");
        assertArrayEquals(new Object[]{"a", "a1", "a2", "a3", "a4"}, listArray.toArray());
    }

    @Test
    public void testIndexOf() {
        assertArrayEquals(new Object[]{}, listArray.toArray());
        listArray.add("a");
        listArray.add("a1");
        listArray.add("a2");
        listArray.add("a2");
        listArray.add("a");
        assertEquals(0, listArray.indexOf("a"));
        assertEquals(2, listArray.indexOf("a2"));
    }

    @Test
    public void testLastIndexOf() {
        assertArrayEquals(new Object[]{}, listArray.toArray());
        listArray.add("a");
        listArray.add("a1");
        listArray.add("a2");
        listArray.add("a2");
        listArray.add("a");
        assertEquals(4, listArray.lastIndexOf("a"));
        assertEquals(3, listArray.lastIndexOf("a2"));
    }

    @Test
    public void testRemoveInt() {
        assertArrayEquals(new Object[]{}, listArray.toArray());
        listArray.add("a");
        listArray.add("a1");
        listArray.add("a2");
        listArray.add("a2");
        listArray.add("a");
        assertEquals("a2", listArray.remove(2));
        assertEquals(4, listArray.size());
        assertArrayEquals(new Object[]{"a", "a1", "a2", "a"}, listArray.toArray());
    }

    @Test
    public void testRemoveObject() {
        listArray.add("a");
        listArray.add("a1");
        listArray.add("a2");
        listArray.add("a3");
        assertTrue(!listArray.remove("a4"));
        assertEquals(4, listArray.size());
        assertTrue(listArray.remove("a2"));
        assertTrue(!listArray.contains("a2"));
        assertEquals(3, listArray.size());
    }

    @Test
    public void testSet() {
        listArray.add("a");
        listArray.add("a1");
        listArray.add("a2");
        listArray.add("a3");
        listArray.set(2, "2");
        assertTrue(!listArray.contains("a2"));
        assertTrue(listArray.contains("a1"));
        assertTrue(listArray.contains("a3"));
        assertTrue(listArray.contains("a"));
    }

    @Test
    public void testContains() {
        listArray.add("a");
        listArray.add("a1");
        listArray.add("a2");
        listArray.add("a3");
        assertTrue(listArray.contains("a2"));
        assertTrue(listArray.contains("a1"));
        assertTrue(listArray.contains("a3"));
        assertTrue(listArray.contains("a"));
    }

    @Test
    public void testGet() {
        listArray.add("a");
        listArray.add("a1");
        listArray.add("a2");
        listArray.add("a3");
        assertEquals("a2", listArray.get(2));
        assertEquals("a", listArray.get(0));
    }

    @Test
    public void testSize() {
        assertEquals(0, listArray.size());
        listArray.add("a");
        assertEquals(1, listArray.size());
        listArray.add(1);
        assertEquals(2, listArray.size());
        listArray.add(true);
        assertEquals(3, listArray.size());
    }

    @Test
    public void testToArray() {
        assertArrayEquals(new Object[]{}, listArray.toArray());
        listArray.add("a");
        listArray.add("a1");
        listArray.add("a2");
        listArray.add("a3");
        assertArrayEquals(new Object[]{"a", "a1", "a2", "a3"}, listArray.toArray());
        listArray.add("a4");
        assertArrayEquals(new Object[]{"a", "a1", "a2", "a3", "a4"}, listArray.toArray());
    }

    @Test
    public void testSubList() {
        listArray.add("a");
        listArray.add("a1");
        listArray.add("a2");
        listArray.add("a3");
        assertArrayEquals(new Object[]{"a1", "a2"}, listArray.subList(1, 3));
    }
}
