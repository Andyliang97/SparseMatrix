import student.TestCase;

/**
 * TESTCASE FOR HASH CLASS
 * @author Junjie Liang
 * @version November 2018
 */
public class HashTest extends TestCase {
    private Hash hash = new Hash(2);
    
    /**
     * INSERT TEST 
     */
    public void testInsert() {
        hash.add("a", "movie", null);
        hash.add("b", "movie", null);
        hash.add("c", "movie", null);
        hash.add("d", "movie", null);
        hash.add("e", "movie", null);
        hash.add("f", "movie", null);
        hash.add("g", "movie", null);
        hash.add("h", "movie", null);
        hash.add("i", "movie", null);
        hash.add("j", "movie", null);
        hash.add("k", "movie", null);
        hash.add("l", "movie", null);
        hash.add("m", "movie", null);
        hash.add("n", "movie", null);
        hash.add("o", "movie", null);
        hash.add("p", "movie", null);
        hash.add("q", "movie", null);
        hash.add("r", "movie", null);
        hash.add("s", "movie", null);
        hash.add("t", "movie", null);
        hash.add("u", "movie", null);
        hash.add("aa", "movie", null);
        hash.printHashtable("movie");
        assertEquals(58, hash.search("aa"));
        hash.delete("aa", "movie");
        assertTrue(hash.getSlot(58).isEmpty());
        hash.add("aa", "movie", null);
        assertEquals(58, hash.search("aa"));
    }
    
    /**
     * GROW TEST
     */
    public void testGrow() {
        hash.add("a", "movie", null);
        hash.add("aa", "movie", null);
        hash.add("b", "movie", null);
        hash.add("c", "movie", null);
        hash.add("d", "movie", null);
        hash.add("e", "movie", null);
        hash.add("f", "movie", null);
        hash.add("g", "movie", null);
        hash.add("h", "movie", null);
        hash.add("i", "movie", null);
        hash.add("j", "movie", null);
        hash.add("k", "movie", null);
        hash.add("l", "movie", null);
        hash.add("m", "movie", null);
        hash.add("n", "movie", null);
        hash.add("o", "movie", null);
        hash.add("p", "movie", null);
        hash.printHashtable("movie");
        assertEquals(33, hash.search("a"));
        assertEquals(34, hash.search("aa"));
    }
    
    /**
     * GET SLOT TEST
     */
    public void testgetSlot() {
        hash.add("a", "movie", null);
        assertEquals("a", hash.getSlot(hash.search("a")).getKey());
        assertNull(hash.getSlot(-1).getNode());
        assertNull(hash.getSlot(11).getNode());
    }
    
    /**
     * GET HASH SIZE & GET COUNT TEST
     */
    public void testHashsizeCount() {
        hash.add("a", "movie", null);
        hash.add("aa", "movie", null);
        hash.add("b", "movie", null);
        hash.add("c", "movie", null);
        hash.add("d", "movie", null);
        hash.add("e", "movie", null);
        assertEquals(16, hash.gethashSize());
        assertEquals(6, hash.getCount());
    }
    
    /**
     * DELETE TEST
     */
    public void testDelete() {
        hash.add("a", "movie", null);
        hash.add("aa", "movie", null);
        hash.add("b", "movie", null);
        hash.add("c", "movie", null);
        assertEquals(4, hash.getCount());
        assertEquals(hash.delete("aa", "movie").getKey(), "aa");
        assertEquals(3, hash.getCount());
        assertNull(hash.delete("aaa", "movie"));
        
    }
    
    /**
     * SEARCH TEST
     */
    public void testSearch() {
        hash = new Hash(64);
        hash.add("a", "movie", null);
        hash.add("aa", "movie", null);
        int num = hash.search("aa");
        assertEquals(num, 34);
        num = hash.search("a");
        assertEquals(num, 33);
        hash.delete("a", "movie");
        num = hash.search("aa");
        assertEquals(num, 34);
        hash.add("a", "movie", null);
        num = hash.search("aa");
        assertEquals(num, 34);
        hash = new Hash(8);
        hash.add("a", "movie", null);
        hash.add("aa", "movie", null);
        hash.add("aaa", "movie", null);
        num = hash.search("aaaa");
        assertEquals(num, -1);
    }
}