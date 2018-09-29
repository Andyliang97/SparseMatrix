import java.text.DecimalFormat;
/**
 * A CLASS STORE THE MOVIE HASH TABLE AND 
 * THE REVIEWER HASH TABLE
 * @author Junjie Liang
 * @version November 2018
 */
public class MovieReviewHash {

    private Hash movieHashtable;
    private Hash reviewHashtable;
    private SparseMatrix sMatrix;
    
    /**
     * CONSTRUCTOR. IT TAKS A INITIAL HASH SIZE
     * AS PARAMETER TO CREATE THE HASH TABLE ARRAYS
     * two hash tables will be created here
     * @param initHashsize the initial size
     */
    public MovieReviewHash(int initHashsize) {
        movieHashtable = new Hash(initHashsize);
        reviewHashtable = new Hash(initHashsize);
        sMatrix = new SparseMatrix();
        
    }
    
    /**
     * ADD METHOD TO ADD ITEM INTO HASH TABLES AND SPARSE MATRIX
     * 1. it check if the rating is valid
     * 2. add item into sparse matrix
     * 3. add record to reviewer and movie hash table
     * 4. print out the add message
     * @param info information that need to 
     * be added to the hash tables and matrix
     */
    public void add(String info) {
        String[] infoArray = info.split("<SEP>");
        if (Integer.valueOf(infoArray[2].trim()) < 1 || 
                Integer.valueOf(infoArray[2].trim()) > 10) {
            System.out.println("Bad score |" 
                + infoArray[2].trim() 
                + "|. Scores must be between 1 and 10.");
        }
        else {
            int reviewLoc = reviewHashtable.search(infoArray[0].trim());
            int movieLoc = movieHashtable.search(infoArray[1].trim());
            SMNode[] smNodeArray = sMatrix.
                    insert(reviewHashtable.getSlot(reviewLoc).getNode(), 
                    movieHashtable.getSlot(movieLoc).getNode()
                    , Integer.valueOf(infoArray[2].trim()));
            reviewHashtable.add(infoArray[0].trim(), 
                    "Reviewer", smNodeArray[0]);
            movieHashtable.add(infoArray[1].trim(), "Movie", smNodeArray[1]);
            System.out.println("Rating added: |" + infoArray[0].trim() 
                    + "|, |" + infoArray[1].trim() + "|, " 
                    + infoArray[2].trim());
    
        }
    }
        
    /**
     * DELETE METHOD FOR REMOVING RECORD FROM HASH TABLE AND SPARSE MATRIX
     * 1. check if it removes from reviewer hash table or movie hash table
     * 2. remove the corresponding ratings from the sparse matrix
     * @param info information that you need for deletion
     */
    public void delete(String info) {
        String[] infoArray = info.split(" ", 2);
        if (infoArray[0].equals("reviewer")) {
            Slot tempSlot = reviewHashtable.
                    delete(infoArray[1].trim(), "Reviewer");
            if (tempSlot != null) {
                sMatrix.removeRowByValue(tempSlot.getNode());
            }
        }
        else if (infoArray[0].equals("movie")) {
            Slot tempSlot = movieHashtable.
                    delete(infoArray[1].trim(),  "Movie");
            if (tempSlot != null) {
                sMatrix.removeColByValue(tempSlot.getNode());
            }
        }
        else {
            System.out.println("Wrong Command");
        }
        
    }

    /**
     * LIST METHOD FOR PRINTING OUT ITEM IN HASH TABLE AND SPARSE MATRIX
     * 1. check if the information exists in the reviewer/movie hash table
     * 2. print out error if it doesnt exist. otherwise print out the needed 
     * information with certain format
     * @param printCommand reviewer or movie that needs to be listed
     */
    public void listSelect(String printCommand) {
        String trimmedLine = (printCommand.trim()
                .replaceAll("\t", " ")).replaceAll(" +", " ");
        String[] splitLine = trimmedLine.split(" ", 2);
        int isExist = 0;
        if (splitLine[0].equals("reviewer")) {
            isExist = reviewHashtable.search(splitLine[1]);
            if (isExist == -1) {
                System.out.println("Cannot list, reviewer |" + splitLine[1] 
                        + "| not found in the database.");
            }
            else {
                System.out.println("Ratings for reviewer |" + splitLine[1] 
                        + "|:");
                LinkedList<Slot> tempmovieList = movieHashtable.sortList();
                sMatrix.listrow(reviewHashtable.getSlot(isExist).
                        getNode(), tempmovieList);
                
            }
        }
        else if (splitLine[0].equals("movie")) {
            isExist = movieHashtable.search(splitLine[1]);
            if (isExist == -1) {
                System.out.println("Cannot list, movie |" + splitLine[1] 
                        + "| not found in the database.");
            }
            else {
                System.out.println("Ratings for movie |" + splitLine[1] 
                        + "|:");
                LinkedList<Slot> tempreviewerList = reviewHashtable.sortList();
                sMatrix.listcol(movieHashtable.getSlot(isExist).
                        getNode(), tempreviewerList);
                
            }
        }
        else {
            System.out.println("Wrong List Command!");
        }
    }
    
    /**
     * SIMILAR METHOD FOR FINDING THE SIMILAR REVIEWER/MOVIE
     * BASED ON THE GIVEN REVIEWER/MOVIE
     * 1. check whether we need to find similar movie/reviewer
     * 2. check if it exists in the hash table
     * 3. call the similar function in sparse matrix to print 
     * out the information
     * @param printCommand information that helps to find similar reviewer/movie
     */
    public void similarSelect(String printCommand) {
        int isExist = 0;
        DecimalFormat df = new DecimalFormat("####0.00");
        LinkedList<Slot> tempList = new LinkedList<Slot>();
        String trimmedLine = (printCommand.trim()
                .replaceAll("\t", " ")).replaceAll(" +", " ");
        String[] splitLine = trimmedLine.split(" ", 2);
        if (splitLine[0].equals("reviewer")) {
            isExist = reviewHashtable.search(splitLine[1]);
            if (isExist == -1) {
                System.out.println("Reviewer |" + splitLine[1] 
                        + "| not found in the database.");
            }
            else {
                tempList = reviewHashtable.sortList();
                KeyRate temp = sMatrix.similarRow(reviewHashtable.
                        getSlot(isExist).getNode(), tempList);
                if (temp.getScore() == 10) {
                    System.out.println("There is no "
                            + "reviewer similar to |" + splitLine[1] + "|");
                }
                else {
                    System.out.println("The reviewer |" + temp.
                            getKey() + "| is similar to |" + splitLine[1] 
                                    + "| with score " + df.format(temp.
                                            getScore()));
                }
            }
        }
        else if (splitLine[0].equals("movie")) {
            isExist = movieHashtable.search(splitLine[1]);
            if (isExist == -1) {
                System.out.println("Movie |" + splitLine[1] 
                        + "| not found in the database.");
            }
            else {
                tempList = movieHashtable.sortList();
                KeyRate temp = sMatrix.similarCol(movieHashtable.
                        getSlot(isExist).getNode(), tempList);
                if (temp.getScore() == 10) {
                    System.out.println("There is no movie similar "
                            + "to |" + splitLine[1] 
                            + "|");
                }
                else {
                    System.out.println("The movie |" + temp.getKey() + "| is "
                            + "similar to |" + splitLine[1] + "| with "
                                    + "score " + df.format(temp.getScore()));
                }
            }
        }
        else {
            System.out.println("Wrong Similar Command!");
        }
    }
    
    /**
     * PRINT METHOD FOR PRINTING OUT HASHTABLE OR RATINGS
     * 1. check whether we need to print hash table or ratings
     * 2. if print ratings, sort the hash table and call print ratings
     * method in sparse matrix
     * 3. if print hash table, call print hash table method 
     * in the related hash table
     * @param printCommand command that can select what to print out
     * 
     */
    public void printSelect(String printCommand) {
        String trimmedLine = (printCommand.trim()
                .replaceAll("\t", " ")).replaceAll(" +", " ");
        String[] splitLine = trimmedLine.split(" ", 2);
        if (splitLine[0].equals("ratings")) {
            //sMatrix.print();
            if (sMatrix.ratingisEmpty()) {
                System.out.println("There are no ratings in the database");
            }
            else {
                LinkedList<Slot> temprevList = reviewHashtable.sortList();
                LinkedList<Slot> tempmovieList = movieHashtable.sortList();
                while (!temprevList.isEmpty()) {
                    temprevList.getFirst().printrating();
                    temprevList.removeFirst();
                }
                sMatrix.printRating(tempmovieList);
            }   
        }
        
        else if (splitLine[0].equals("hashtable")) {
            if (splitLine[1].equals("movie")) {
                movieHashtable.printHashtable("Movies");
            }
            else if (splitLine[1].equals("reviewer")) {
                reviewHashtable.printHashtable("Reviewers");
            }
            else {
                System.out.println("Wrong Print Command!");
            }
        }
        else {
            System.out.println("Wrong Print Command!");
        }
    }

}
