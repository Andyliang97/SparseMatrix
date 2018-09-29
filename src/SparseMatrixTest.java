import java.io.IOException;

//import org.junit.rules.ExpectedException;

import student.TestCase;

/**
 * TEST CASE FOR SPARSE MATRIX
 * @author Junjie Liang
 *@version November 2018
 */
public class SparseMatrixTest extends TestCase {
    private SparseMatrix sm = new SparseMatrix();
    //public final ExpectedException exception = ExpectedException.none();
    
    /**
     * Initialization 
     * @throws IOException
     */
    public void testInit() throws IOException {
        String[] args = {"6", "P2SampleInput.txt"};
        assertNotNull(sm);
        MovieReview.main(args);
        String[] argss = {"6", "test.txt"};
        MovieReview.main(argss);
    }
    
    /**
     * TEST FOR INSERT METHOD
     */
    public void testInsert() {
        sm.print();
        SMNode[] returnItem = new SMNode[2];
        sm.insert(null, null, 9);
        sm.insert(null, null, 8);
        sm.insert(null, null, 7);
        returnItem = sm.insert(null, null, 6);
        sm.insert(null, null, 5);
        sm.print();
        
        sm.insert(returnItem[0], returnItem[1], 4);
        sm.print();
        
        sm.insert(returnItem[0], null, 3);
        sm.print();
        
        sm.insert(null, returnItem[1], 2);
        sm.print();
        
        sm.clear();
        sm.print();
        
        returnItem = sm.insert(null, null, 6);
        sm.insert(returnItem[0], null, 3);
        sm.print();
        
        sm.clear();
        
        returnItem = sm.insert(null, null, 6);
        sm.insert(null, returnItem[1], 3);
        sm.print();
        assertNotNull(sm);
        assertNull(sm.removeColByValue(null));
        assertNull(sm.removeRowByValue(null));
    }
    
    /**
     * TEST FOR LIST COLUMN METHOD
     */
    public void testListCol() {
    	LinkedList<Slot> templist = new LinkedList<Slot>();
    	SMNode[] returnItem = new SMNode[2];
    	returnItem = sm.insert(null, null, 5);
    	assertNotNull(sm.removeRowByValue(returnItem[0]));
    	sm.listcol(returnItem[1], templist);	
    }
    /**
     * TEST FOR REMOVE METHOD
     */
    public void testRemove() {
        SMNode[] returnItem = new SMNode[2];
        sm.insert(null, null, 9);
        sm.insert(null, null, 8);
        sm.insert(null, null, 7);
        returnItem = sm.insert(null, null, 6);
        sm.insert(null, null, 5);
        sm.print();
        
        sm.removeColByValue(returnItem[1]);
        sm.print();
        
        sm.clear();
        sm.removeColByValue(returnItem[1]);
        sm.print();
        assertNotNull(sm);
        assertNull(sm.removeColByValue(null));
        assertNull(sm.removeRowByValue(null));
    }
    
    /**
     * TEST FOR GET LAST NODE METHOD
     */
    public void testGetLastNode() {
        assertNull(sm.getLastNode(null, "row"));
        SMNode[] returnItem = new SMNode[2];
        sm.insert(null, null, 9);
        sm.insert(null, null, 8);
        sm.insert(null, null, 7);
        returnItem = sm.insert(null, null, 6);
        assertNotNull(sm.getLastNode(returnItem[0], "row"));
        sm.removeColByValue(returnItem[1]);
        assertNull(sm.getLastNode(returnItem[0], "rows"));
    }
    
    /**
     * TEST FOR FIND NODE METHOD
     */
    public void testfindNode() {
        //exception.expect(IllegalArgumentException.class);
        assertNull(sm.findNode(null, null));
        SMNode[] returnItem = new SMNode[2];
        returnItem = sm.insert(null, null, 6);
        SMNode temp = sm.findNode(returnItem[0], returnItem[1]);
        assertEquals(temp.getItem(), 6);
        assertNull(sm.findNode(returnItem[0], null));
        assertNull(sm.findNode(null, returnItem[1]));
    }
    
    /**
     * TEST FOR IS EMPTY AND REMOVE METHOD 
     */
    public void testcheckEmptyRemove() {
        assertFalse(sm.rowIsEmpty(null));
        assertFalse(sm.colIsEmpty(null));
        SMNode[] returnItem = new SMNode[2];
        returnItem = sm.insert(null, null, 6);
        assertFalse(sm.rowIsEmpty(returnItem[0]));
        assertFalse(sm.colIsEmpty(returnItem[1]));
        assertFalse(sm.ratingisEmpty());
        sm.removeRowByValue(returnItem[0]);
        assertTrue(sm.colIsEmpty(returnItem[1]));
        sm.clear();
        assertTrue(sm.ratingisEmpty());
        returnItem = sm.insert(null, null, 6);
        sm.removeColByValue(returnItem[1]);
        assertTrue(sm.rowIsEmpty(returnItem[0]));
        sm.clear();
        returnItem = sm.insert(null, null, 6);
        sm.insert(null, returnItem[1], 8);
        sm.insert(null, returnItem[1], 9);
        sm.insert(null, returnItem[1], 10);
        sm.insert(returnItem[0], null, 8);
        sm.insert(returnItem[0], null, 8);
        sm.insert(returnItem[0], null, 8);
        sm.insert(null, null, 7);
        sm.removeColByValue(returnItem[1]);
        sm.removeRowByValue(returnItem[0]);
        sm.clear();
        assertNull(sm.removeRowByValue(returnItem[0]));
        returnItem = sm.insert(null, null, 6);
        sm.insert(null, returnItem[1], 8);
        sm.insert(null, returnItem[1], 9);
        sm.insert(null, returnItem[1], 10);
        sm.insert(returnItem[0], null, 8);
        sm.insert(returnItem[0], null, 8);
        sm.insert(returnItem[0], null, 8);
        sm.insert(null, null, 7);
        sm.removeRowByValue(returnItem[0]);
    }
    
    /**
     * TEST FOR PRINT RATING METHOD
     */
    public void testprintingRating() {
        SMNode[] returnItem = new SMNode[2];
        returnItem = sm.insert(null, null, 6);
        sm.removeRowByValue(returnItem[0]);
        Slot tempSlot = new Slot();
        tempSlot.setKey("AI");
        tempSlot.setNode(returnItem[1]);
        LinkedList<Slot> templist = new LinkedList<Slot>();
        templist.append(tempSlot);
        sm.printRating(templist);
        assertTrue(systemOut().getHistory().
                contains("AI:"));
    }
    
    
}
