import student.TestCase;

/**
 * TEST CASE FOR KEYRATE CLASS
 * @author Junjie Liang
 * @version November 2018
 *
 */
public class KeyRateTest extends TestCase {
    private KeyRate test = new KeyRate();
    
    /**
     * SET UP
     */
    public void setUp() {
        test = new KeyRate("Venom", 2);
    }
    
    /**
     * TEST FOR GET KEY AND SET KEY
     */
    public void testgetsetKey() {
        assertEquals("Venom", test.getKey());
        test.setKey("iron man");
        assertEquals("iron man", test.getKey());
    }
    
    /**
     * TEST FOR GET SCORE AND SET SCORE
     */
    public void testgetsetScore() {
        assertEquals("2.0", Double.toString(test.getScore()));
        test.setScore(1);
        assertEquals("1.0", Double.toString(test.getScore()));
    }
    
    /**
     * TEST FOR IS EMPTY
     */
    public void testIsEmpty() {
        assertFalse(test.isEmpty());
        test = new KeyRate();
        assertTrue(test.isEmpty());
        test.setKey("");
        test.setScore(-1);
        assertTrue(test.isEmpty());
        test.setKey(" ");
        assertFalse(test.isEmpty());
        test.setKey("");
        test.setScore(0);
        assertFalse(test.isEmpty());
    }
}