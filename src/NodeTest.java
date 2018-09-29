import student.TestCase;
/**
 * TEST CASE FOR NODE
 * @author Junjie Liang
 * @version November 2018
 *
 */
public class NodeTest extends TestCase {
    private Node<Integer> testNode = new Node<Integer>();
    
    /**
     * SET UP
     */
    public void setUp() {
        //nothing
    }
    
    /**
     * CONSTRUCTOR TESTING
     */
    public void testConstructor() {
        Node<Integer> testNode1 = new Node<Integer>(1);
        testNode = new Node<Integer>(10, testNode1);
        assertEquals(1, testNode.getnext().getItem().intValue());
    }
    
    /**
     * TEST FOR SET ITEM AND GET ITEM
     */
    public void testsetgetItem() {
        testNode.setItem(200);
        assertEquals(200, testNode.getItem().intValue());
    }
    
    /**
     * TEST FOR SET NEXT AND GET NEXT
     */
    public void testsetgetNext() {
        Node<Integer> testNode1 = new Node<Integer>(1);
        testNode.setnext(testNode1);
        assertEquals(testNode1, testNode.getnext());
    }
}

