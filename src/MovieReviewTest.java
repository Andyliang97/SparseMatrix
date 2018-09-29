import java.io.IOException;


import student.TestCase;

/**
 * TEST CASE FOR MOVIEREVIEW
 * @author Junjie LIANG
 * @version November 2018
 */
public class MovieReviewTest extends TestCase {
    
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing Here
    }

    /**
     * INITIALIZATION
     * @throws IOException 
     */
    public void testInit() throws IOException {
        MovieReview recstore = new MovieReview();
        assertNotNull(recstore);
        String[] args = {"10", "ms1.txt"};
        MovieReview.main(args);
        String[] argss = {"1", "1", "1"};
        MovieReview.main(argss);
        assertTrue(systemOut().getHistory().
                contains("Something Wrong with Parameters"));

    }

}
