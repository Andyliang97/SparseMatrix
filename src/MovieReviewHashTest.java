import java.io.IOException;

import student.TestCase;

/**
 * TEST CASE FOR THE MOVIEREVIEWHASH
 * @author Junjie Liang
 * @version November 2018
 */
public class MovieReviewHashTest extends TestCase {
    private MovieReviewHash recstore;
    
    
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        recstore = new MovieReviewHash(6);
    }

    /**
     * Initialization
     * @throws IOException 
     */
    public void testInit() throws IOException {
        assertNotNull(recstore);
        String[] args = {"6", "P2SampleInput.txt"};
        MovieReview.main(args);
    }
    
    /**
     * TEST WRONG COMMAND
     */
    public void testWrongcommand() {
        recstore.delete("wrong command");
        assertTrue(systemOut().getHistory().
                contains("Wrong Command"));
        recstore.listSelect("wrong command");
        assertTrue(systemOut().getHistory().
                contains("Wrong List Command!"));
        recstore.similarSelect("wrong command");
        assertTrue(systemOut().getHistory().
                contains("Wrong Similar Command!"));
        recstore.printSelect("wrong command");
        assertTrue(systemOut().getHistory().
                contains("Wrong Print Command!"));
        recstore.printSelect("hashtable moviesssssss");
        assertTrue(systemOut().getHistory().
                contains("Wrong Print Command!"));
    }

    /**
     * TEST ADD AND DELETE METHOD
     */
    public void testAddDelete() {
        recstore.add("A<SEP>AA<SEP>0");
        assertTrue(systemOut().getHistory().
                contains("Bad score |0|. Scores must be between 1 and 10."));
        recstore.add("A<SEP>AA<SEP>110");
        assertTrue(systemOut().getHistory().
                contains("Bad score |110|. Scores must be between 1 and 10."));
        recstore.add("A<SEP>AA<SEP>5");
        recstore.delete("movie AA");
        recstore.add("A<SEP>AA<SEP>5");
        recstore.delete("reviewer A");
    }
    
    /**
     * TEST LIST, PRINT AND SIMILAR METHOD
     */
    public void testListPrintSimilar() {
        recstore.add("A<SEP>AA<SEP>1");
        recstore.add("B<SEP>BB<SEP>2");
        recstore.add("A<SEP>BB<SEP>3");
        recstore.add("B<SEP>AA<SEP>1");
        recstore.listSelect("movie AA");
        assertTrue(systemOut().getHistory().
                contains("A: 1"));
        assertTrue(systemOut().getHistory().
                contains("B: 1"));
        recstore.listSelect("reviewer A");
        assertTrue(systemOut().getHistory().
                contains("AA: 1"));
        assertTrue(systemOut().getHistory().
                contains("BB: 3"));
        recstore.printSelect("hashtable movie");
        assertTrue(systemOut().getHistory().
                contains("|BB| 0"));
        assertTrue(systemOut().getHistory().
                contains("|AA| 1"));
        recstore.printSelect("hashtable reviewer");
        assertTrue(systemOut().getHistory().
                contains("|B| 0"));
        assertTrue(systemOut().getHistory().
                contains("|A| 5"));
        recstore.similarSelect("movie AA");
        assertTrue(systemOut().getHistory().
                contains("The movie |BB| is similar to |AA| with score 1.50"));
        recstore.similarSelect("reviewer A");
        assertTrue(systemOut().getHistory().
                contains("The reviewer |B| is similar to |A| with score 0.50"));
    }
}
