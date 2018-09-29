import student.TestCase;
/**
 * TEST CASE FOR SLOT
 * @author Junjie Liang
 * @version November 2018
 *
 */
public class SlotTest extends TestCase {
    private Slot testSlot = new Slot("AI", null, false);
    
    /**
     * SET UP
     */
    public void setUp() {
        //nothing
    }
    
    /**
     * TEST FOR CONSTRUCTOR
     */
    public void testConstructor() {
        Slot copySlot = new Slot(testSlot);
        assertEquals(copySlot.getKey(), testSlot.getKey());
    }
    
    /**
     * TEST FOR IS EMPTY METHOD
     */
    public void testisEmpty() {
        Slot tempSlot = new Slot();
        assertTrue(tempSlot.isEmpty());
        tempSlot.setKey("COOL");
        assertFalse(tempSlot.isEmpty());
        tempSlot.setEmpty();
        assertTrue(tempSlot.isEmpty());
        SMNode testNode = new SMNode(5, 5, 100);
        tempSlot.setNode(testNode);
        assertFalse(tempSlot.isEmpty());
    }
    
    /**
     * TEST FOR SET NODE AND GET NODE METHOD
     */
    public void testSetgetNode() {
        testSlot.setNode(null);
        assertNull(testSlot.getNode());
    }
    
    /**
     * TEST FOR SET TOMBSTONE AND GET TOMBSTONE 
     */
    public void testsetgetTomb() {
        testSlot.setTombstone();
        assertTrue(testSlot.getTombstone());
    }
    
    /**
     * TEST FOR PRINT RATING METHOD
     */
    public void testPrintrating() {
        testSlot.setEmpty();
        testSlot.printrating();
        SMNode testNode = new SMNode(5, 5, 100);
        testSlot = new Slot("AII", testNode, true);
        testSlot.printrating();
        assertTrue(systemOut().getHistory().
                contains("AII: 5"));
    }
    
    /**
     * TEST FOR COMPARE TO METHOD
     */
    public void testcompareTo() {
        Slot tempSlot = new Slot();
        SMNode testNode = new SMNode(5, -1, 100);
        SMNode testNode2 = new SMNode(6, -1, 100);
        SMNode testNode3 = new SMNode(7, -1, 100);
        testSlot.setNode(null);
        assertEquals(-100, testSlot.compareTo(tempSlot));
        tempSlot.setNode(testNode);
        assertEquals(-100, testSlot.compareTo(tempSlot));
        tempSlot.setNode(null);
        testSlot.setNode(testNode2);
        assertEquals(-100, testSlot.compareTo(tempSlot));
        Slot copySlot = new Slot(testSlot);
        assertEquals(0, testSlot.compareTo(copySlot));
        testSlot.setNode(testNode3);
        assertEquals(1, testSlot.compareTo(copySlot));
        testSlot.setNode(testNode);
        assertEquals(-1, testSlot.compareTo(copySlot));
        
        testNode = new SMNode(-1, 5, 100);
        testNode2 = new SMNode(-1, 6, 100);
        testNode3 = new SMNode(-1, 7, 100);
        testSlot.setNode(testNode2);
        copySlot = new Slot(testSlot);
        assertEquals(0, testSlot.compareTo(copySlot));
        testSlot.setNode(testNode3);
        assertEquals(1, testSlot.compareTo(copySlot));
        testSlot.setNode(testNode);
        assertEquals(-1, testSlot.compareTo(copySlot));
        testSlot.setEmpty();
        copySlot.setEmpty();
        assertEquals(-100, testSlot.compareTo(copySlot));
        testSlot.setKey("cool");
        assertEquals(-100, testSlot.compareTo(copySlot));
        
    }
    
    
}

