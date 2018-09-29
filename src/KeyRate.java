/**
 * THIS IS A CLASS THAT CAN STORE KEY NAME
 * AND SCORE
 * @author Junjie Liang
 * @version November 2018
 *
 */
public class KeyRate {
    private String key;
    private double score;
    
    /**
     * DEFAULT CONSTRUCTOR
     * 
     */
    KeyRate() {
        this.key = "";
        this.score = -1;
    }
    
    /**
     * CONSTRUCTOR
     * @param key key value 
     * @param score the score after calculation
     */
    KeyRate(String key, double score) {
        this.key = key;
        this.score = score;
    }
    
    /**
     * SET KEY FOR KEYRATE CLASS
     * @param key given key name
     */
    public void setKey(String key) {
        this.key = key;
    }
    
    /**
     * GET KEY
     * @return key got from current object
     */
    public String getKey() {
        return this.key;
    }
    
    /**
     * SET SCORE FOR KEYRATE CLASS
     * @param score given score
     */
    public void setScore(double score) {
        this.score = score;
    }
    
    /**
     * GET SCORE FROM OBJECT
     * @return the score got from object
     */
    public double getScore() {
        return this.score;
    }
    
    /**
     * CHECK IF THE OBJECT IS EMPTY
     * @return true if empty, otherwise false.
     */
    public boolean isEmpty() {
        return (key.equals("")  && score == -1);
    }
}
