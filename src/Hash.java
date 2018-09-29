/**
 * HASH TABLE CLASS USED FOR PROJECT 2
 *
 * @author Junjie Liang
 * @version November 2018
 */

public class Hash {
    private Slot[] hashTable; //hash table
    private int hashSize = 0; //hash table size
    private int hashCount = 0; //number of item in the hash table
    
    /**
     * CONSTRUCTOR
     * 1. set the count number to 0, new hash table, set hash table
     * size to initial hash table size
     * 2. initialize the slot in hash table. 
     * @param initHashSize the initial hash size
     * 
     */
    public Hash(int initHashSize) {
        hashCount = 0;
        hashTable = new Slot[initHashSize];
        hashSize = initHashSize;
        for (int i = 0; i < initHashSize; i++) {
            hashTable[i] = new Slot();
        }
    }


    /**
     * Compute the hash function. Uses the "sfold" method from the OpenDSA
     * module on hash functions
     *
     * @param s
     *            The string that we are hashing
     * @param m
     *            The size of the hash table
     * @return The home slot for that string
     */
    public int h(String s, int m) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return (int)(Math.abs(sum) % m);
    }


    /**
     * QUADRATIC PROBING METHOD FOR HASH TABLE. 
     * IT TAKE THE HOMESLOT, A NUMBER I AND THE HASH 
     * TABLE SIZE AS PARAMETER
     * @param homeSlot home number
     * @param i number used to increase size
     * @param m hash table size
     * @return calculated slot number
     */
    public int p(int homeSlot, int i, int m) {
        int newSlot = (homeSlot + i * i) % m;
        return newSlot;
    }
    
    
    /**
     * ADD METHOD FOR HASH TABLE, IT TAKE THE KEY, HASH TABLE
     * NAME AND THE REFERENCE FROM SPARSE MATRIX. 
     * 1. It first check if the key already existed in the  
     * hash table. If it exist, then the it will not do anything. 
     * 2. Then it check if the number of items in hash table pass 
     * over half the hash table size. If it pass, the hash table 
     * will grow to a hash table with double original size. 
     * 3. Then it will check if there is any collision 
     * happened. If it did, do a collision quadratic probing.
     * 4. Add the item into hash table
     * 
     * @param name the key
     * @param select the hash table name, used for output
     * @param smPosition reference from sparse matrix
     */
    public void add(String name, String select, SMNode smPosition) {

        int hashNumber = h(name, hashSize);
        int homeNumber = hashNumber;
        int place = search(name); //check existence
        if (place != -1) {
            return;
        }
        if (hashCount >= hashSize / 2) { //check capacity
            grow(select);
            hashNumber = h(name, hashSize);
            homeNumber = hashNumber;
        }
      //collision quadratic probing
        for (int i = 1; !hashTable[hashNumber].isEmpty(); i++) { 
            hashNumber = p(homeNumber, i, hashSize);
        }
        //add item
        hashTable[hashNumber].setKey(name);
        hashTable[hashNumber].setNode(smPosition);
        hashCount++;
    }


    /**
     * GROW METHOD FOR HASH TABLE. IT TAKE THE HASH TABLE NAME AS PARAMETER
     * 1. It will first double the hash table size, and create 
     * a new array with that size and initialize it.
     * 2. loop through the original hash table, re-hash 
     * the key and put the item into the new hash table.
     * 3. replace the old hash table with the new one and print out the message.
     * @param select the hash table name. Use for output
     */
    public void grow(String select) {
        int hashNumber = 0;
        int homeNumber = 0;
        int j = 1;
        hashSize = 2 * hashSize;
        Slot[] newArray = new Slot[hashSize];
        for (int i = 0; i < hashSize; i++) {
            newArray[i] = new Slot();
        }
        for (int i = 0; i < hashTable.length; i++) {
            j = 1;
            if (!hashTable[i].isEmpty()) {
                hashNumber = h(hashTable[i].getKey(), hashSize);
                homeNumber = hashNumber;
                while (!newArray[hashNumber].isEmpty()) {
                    hashNumber = p(homeNumber, j, hashSize);
                    j++;
                }
                newArray[hashNumber] = new Slot(hashTable[i].getKey(),
                     hashTable[i].getNode());
            }
        }
        hashTable = newArray;
        System.out.println(select + " hash table " + "size "
                + "doubled to " + hashSize + " slots.");
            
    }

    /**
     * PRINT OUT THE HASH TABLE WITH CERTAIN FORMAT
     * @param hashtableName the hash table name. Use for output
     */
    public void printHashtable(String hashtableName) {
        System.out.println(hashtableName + ":");
        for (int i = 0; i < hashSize; i++) {
            if (!hashTable[i].isEmpty()) {
                System.out.println("|" + hashTable[i].getKey() + "| " + i);
            }
        }
        System.out.println("Total records: " + hashCount);
    }
    
    /**
     * SORT THE HASH TABLE ITEM BASED ON ITEM IN THE SPARCE MATRIX REFERENCE
     * 1. check if the slot is empty. If it is not, call the 
     * method insertAtSmalleest in linked list to put it into the list.
     * 2. return the linked list with the sorted item
     * @return a linked list with the sorted item
     */
    public LinkedList<Slot> sortList() {
        LinkedList<Slot> tempList = new LinkedList<Slot>();
        for (int i = 0; i < hashSize; i++) {
            if (!hashTable[i].isEmpty()) {
                tempList.insertAtSmallest(hashTable[i]);
            }
        }
        return tempList;
    }

    
    
    /**
     * DELETE METHOD FOR HASH TABLE. IT TAKE A THE HASH TABLE NAME,
     * AND THE KEY
     * 1. check if the item exists in the hash table
     * 2. set the slot to be empty and set the tombstone for it
     * 3. decrease the number of item in the hash table
     * 4. print ouf the delete message
     * @param name key
     * @param select hash table name
     * @return the slot got deleted
     */
    public Slot delete(String name, String select) {
        int place = search(name);
        if (place != -1) {
            //remove whatever from the spaser matrix
            Slot tempSlot = new Slot(hashTable[place]);
            hashTable[place].setEmpty();
            hashTable[place].setTombstone();
            hashCount--;
            System.out.println("|" + name
                + "| has been deleted from the " + select + " database.");
            return tempSlot;
        }
        else {
            System.out.println("|" + name + "| not deleted because it does "
                + "not exist in the " + select + " database.");
            return null;
        }

    }


    /**
     * SEARCH METHOD FOR HASH TABLE. IT TAKE THE KEY THAT NEEDS TO BE 
     * SEARCHED.
     * 1. it hash the key and get the location.
     * 2. check if the spot is empty and it is a tombstone
     * 3. if it is a tombstone, keey search with collision quadratic probing
     * 4. the key in the slot match the given key, return that number
     * @param name the key
     * @return the position of the given key in the hash table. -1 means
     *              it cannot be found
     */
    public int search(String name) {
        int hashNumber = h(name, hashSize);
        int homeNumber = hashNumber;
        for (int i = 1; (!hashTable[hashNumber].isEmpty()
            || hashTable[hashNumber].getTombstone()) && i <= hashSize; i++) {

            if (name.equals(hashTable[hashNumber].getKey())) {
                return hashNumber;
            }
            else {
                hashNumber = p(homeNumber, i, hashSize);
            }
        }
        return -1;
    }
    
    /**
     * GET SLOT METHOD FOR HASH TABLE. IT TAKE THE POSITION NUMBER 
     * AS PARAMETER
     * 1. check if the position number is valid
     * 2. return the slot at the given position
     * @param pos the position number
     * @return the slot at the given position or null slot
     */
    public Slot getSlot(int pos) {
        if (pos < 0 || pos >= hashSize) {
            Slot returnSlot = new Slot();
            return returnSlot;
        }
        else {
            return hashTable[pos];
        }
    }
    
    /**
     * GET NUMBER OF ITEM IN HASH TABLE
     * @return integer number of item in the hash table
     */
    public int getCount() {
        return hashCount;
    }

    /**
     * GET THE HASH TABLE SIZE
     * @return integer hash table size
     */
    public int gethashSize() {
        return hashSize;
    }
}