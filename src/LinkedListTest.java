import student.TestCase;

/**
 * LINKED LIST TEST CASE
 * @author Junjie Liang
 * @version November 2018
 */
public class LinkedListTest extends TestCase {
    private LinkedList<Integer> list = new LinkedList<Integer>();

    /**
     * TEST FOR INSERT
     */
    public void testInsert() {
        list.append(2);
        list.insertAtHead(1);
        list.append(3);
        list.append(4);
        list.append(5);
        list.append(6);
        list.print();
        assertTrue(systemOut().getHistory().contains("1 2 3 4 5 6"));
        list.removeByValue(6);
        list.print();
        assertTrue(systemOut().getHistory().contains("1 2 3 4 5"));
        list.removeLast();
        list.print();
        list.removeFirst();
        list.print();
        System.out.println(list.getSize());
        System.out.println(list.getPos(1));
    }
    
    /**
     * TEST FOR IS EMPTY
     */
    public void testEmpty() {
        assertTrue(list.isEmpty());
        list.append(3);
        list.append(4);
        list.append(5);
        assertFalse(list.isEmpty());
    }
    
    /**
     * TEST FOR GETTING SIZE
     */
    public void testSize() {
        list.append(3);
        list.append(4);
        list.append(5);
        list.append(6);
        assertEquals(4, list.getSize());
        assertEquals(4, list.testSize());
        list.removeFirst();
        list.removeFirst();
        assertEquals(2, list.getSize());
        assertEquals(2, list.testSize());
        list.append(3);
        list.append(4);
        list.append(5);
        list.append(6);
        list.removeLast();
        list.removeLast();
        assertEquals(4, list.getSize());
        assertEquals(4, list.testSize());
    }
    
    /**
     * TEST FOR GETTING POSITION
     */
    public void testgetPos() {
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        assertEquals(3, list.getPos(4));
        list.removeLast();
        list.removeLast();
        list.removeLast();
        list.removeLast();
        assertEquals(-1, list.getPos(10));
    }
    
    /**
     * TEST FOR CLEAR METHOD
     */
    public void testclear() {
        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        assertEquals(4, list.getSize());
        assertNull(list.clear());
        assertEquals(0, list.getSize());
    }
    
    /**
     * TEST FOR GET LAST METHOD
     */
    public void testgetLast() {
        assertNull(list.getLast());
        list.append(1);
        list.append(2);
        list.append(3);
        assertEquals(3, list.getLast().intValue());
    }
    
    /**
     * TEST FOR INSERT AT HEAD METHOD
     */
    public void testInsertatHead() {
        list.insertAtHead(1);
        assertEquals(1, list.getFirst().intValue());
        assertEquals(1, list.get(0).intValue());
    }
    
    /**
     * TEST FOR REMOVE METHOD
     */
    public void testremove() {
        assertNull(list.removeFirst());
        assertNull(list.removeLast());
        list.append(1);
        assertEquals(1, list.removeFirst().intValue());
        list.append(1);
        assertEquals(1, list.removeLast().intValue());
        assertNull(list.removeLast());
        assertNull(list.removeByValue(2));
        list.append(1);
        list.append(2);
        list.print();
        assertEquals(2, list.removeByValue(2).intValue());
        list.print();
        assertEquals(1, list.removeByValue(1).intValue());
        list.append(1);
        list.append(2);
        assertNull(list.removeByValue(3));
    }
    
    /**
     * TEST FOR GET METHOD
     */
    public void testGet() {
        assertNull(list.get(2));
        list.append(1);
        assertNull(list.get(2));
        list.append(2);
        assertNull(list.get(2));
        list.append(3);
        assertNotNull(list.get(2));
    }
    
    /**
     * TEST FOR INSERT AT SMALLEST METHOD
     */
    public void testInsertAtSmallest() {
        list.insertAtSmallest(5);
        list.insertAtSmallest(4);
        list.insertAtSmallest(3);
        list.insertAtSmallest(6);
        list.insertAtSmallest(10);
        list.insertAtSmallest(1);
        list.insertAtSmallest(11);
        list.print();
        assertTrue(systemOut().getHistory().contains("1 3 4 5 6 10 11"));
    }
   
    /**
     * TEST FOR GET FIRST NODE AND GET LAST NODE
     */
    public void testGetFirstLastNode() {
        list.append(3);
        list.append(4);
        list.append(5);
        Node<Integer> tempnode = list.getFirstNode();
        assertEquals(tempnode.getItem().intValue(), 3);
        tempnode = list.getNextNode(tempnode);
        assertEquals(tempnode.getItem().intValue(), 4);
        assertNull(list.getNextNode(null));
    }
    
    /**
     * TEST FOR GET FIRST ITEM
     */
    public void testGetFirst() {
        assertNull(list.getFirst());
        list.append(3);
        assertEquals(list.getFirst().intValue(), 3);
    }
    
    /**
     * TEST FOR REMOVE LAST METHOD
     */
    public void testremoveLast() {
        list.append(3);
        list.append(4);
        list.append(5);
        list.append(6);
        list.append(7);
        assertEquals(7, list.removeLast().intValue());
    }
    
   


}
