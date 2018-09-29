import student.TestCase;
/**
 * TEST CASE FOR PARSER
 * @author Junjie Liang
 * @version November 2018
 *
 */
public class ParserTest extends TestCase {
    private Parser testParser = new Parser(6);
    
    /**
     * SET UP
     */
    public void setUp() {
        //nothing
    }
    
    /**
     * TEST FOR READ FILE
     */
    public void testreadFile() {
        testParser.readinFile("parsertest.txt");
        assertTrue(systemOut().getHistory().
                contains("WRONG COMMAND!"));
    }
}

