import student.TestCase;
/**
 * TEST CASE FOR SMNode
 * @author Junjie Liang
 * @version November 2018
 *
 */
public class SMNodeTest extends TestCase {
    //private SMNode testNode123 = new SMNode();
    private SMNode testNode = new SMNode(5, 5, null, null, null, null, 100);
    
    /**
     * SET UP
     */
    public void setUp() {
        //NOTHING
    }
    
    /**
     * TEST FOR GET COL, SET COL, GET ROW, SET ROW METHOD
     */
    public void testGetSetColRow() {
        testNode = new SMNode(5, 5, null, null, null, null, 100);
        SMNode testNode1 = new SMNode(1, 1, 10);
        SMNode testNode2 = new SMNode(2, 2, 20);
        SMNode testNode3 = new SMNode(3, 3, 30);
        SMNode testNode4 = new SMNode(4, 4, 40);
        testNode.setnextCol(testNode1);
        testNode.setpreCol(testNode2);
        testNode.setnextRow(testNode3);
        testNode.setpreRow(testNode4);
        assertEquals(testNode.getnextCol().getItem(), 10);
        assertEquals(testNode.getpreCol().getItem(), 20);
        assertEquals(testNode.getnextRow().getItem(), 30);
        assertEquals(testNode.getpreRow().getItem(), 40);
        
    }
    
    /**
     * ANOTHER TEST FOR COLUMN AND ROW
     */
    public void testGetSetColRow2() {
        testNode = new SMNode(5, 6, null, null, null, null, 100);
        assertEquals(testNode.getRow(), 5);
        assertEquals(testNode.getCol(), 6);
    }
    
    /**
     * TEST FOR GET ITEM AND SET ITEM METHOD
     */
    public void testGetSetItem() {
        assertEquals(testNode.getItem(), 100);
        testNode.setItem(1001);
        assertEquals(testNode.getItem(), 1001);
    }
    
    /**
     * TEST FOR COMPARE TO METHOD
     */
    public void testCompareTo() {
        testNode = new SMNode(5, -1, null, null, null, null, 100);
        SMNode testNode1 = new SMNode(6, -1, 110);
        SMNode testNode2 = new SMNode(5, -1, 90);
        SMNode testNode3 = new SMNode(4, -1, 100);
        
        assertEquals(1, testNode.compareTo(testNode1));
        assertEquals(1, testNode.compareTo(testNode2));
        assertEquals(-1, testNode.compareTo(testNode3));
        
        testNode = new SMNode(-1, 5, null, null, null, null, 100);
        testNode1 = new SMNode(-1, 6, 110);
        testNode2 = new SMNode(-1, 5, 90);
        testNode3 = new SMNode(-1, 4, 100);
        
        assertEquals(1, testNode.compareTo(testNode1));
        assertEquals(1, testNode.compareTo(testNode2));
        assertEquals(-1, testNode.compareTo(testNode3));
    }
}