import java.util.ArrayList;

public class TrieDictionary {
    private TrieNode root;

    public TrieDictionary() {
        root = new TrieNode(' ');

    }
    //******************************************************************
    //this method takes a string as parameter and adds to the tree
    public void insert(String word) {
        //start from the root node
        TrieNode currentNode = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);// get each letter
            // create index based on each character a = 0, b=1 so on
            int index = c-'a';
            // check if the the position of the character is empty
            if(currentNode.arr[index]== null){
                //create a node with that letter
                TrieNode temp = new TrieNode(c);
                //add the newly created node to the empty spot
                currentNode.arr[index]= temp;
                //advance the current node
                currentNode = temp;
            }else{
                //if that index is not empty go to the next.
                //don't over ride just skip
                currentNode = currentNode.arr[index];
            }
        }
        //the word is complete
        currentNode.isWordComplete=true;

    }
    //**************************************************************
    //given a string parameter the method will return all the words 
    //that start with that parameter and returns an ArrayList
    public ArrayList<String> searchWithPrefix(String prefix) {
        //create an ArrayList to store the words with that prefix
        ArrayList<String> result = new ArrayList<String>();
        //call getAllwords method pass the ArrayList and the prefix
        result = getAllWords(result, prefix);
        return result;
    }
    //******************************************************************
    //the method expects an ArrayList and String as parameters
    //and starting from the node that has the String parameter
    // returns an ArrayList containing all of its children
    // with complete word.
    public ArrayList<String> getAllWords(ArrayList<String> words,
                                         String prefix) {
        //get the node with this prefix by calling getNode method
        TrieNode current = getNode(prefix);
        //if the node is empty just return
        if (current == null){
            return words;
        }
        //limit the size of the ArrayList to 10
        if(words.size()>=10){
            return words;
        }
        //if the node contains a complete word add it
        //to the list
        if (current.isWordComplete){
            words.add(prefix);

        }
        //checking all 26 indexes
        for (int i = 0; i < current.arr.length; i++) {
            //every non empty index(starting with a at 0 position b at 1
            //get all of its children recursively.
            if (current.arr[i] != null)
                //recursive getting all the words that start with
                //prefix + a, then prefix + b, and so on
                words = getAllWords(words, prefix + (char)((int)'a' + i));

        }
        //return the ArrayList containing the words
        return words;
    }
    //*********************************************************
    //given a string parameter the method will return the node
    // associated with that string
    public TrieNode getNode(String s){
        //start at the root node
        TrieNode currentNode = root;
        //follow each character
        for(int i=0; i<s.length(); i++){
            char c= s.charAt(i);
            int index = c-'a';
            //since the string is present in the tree
            //each character node will be non empty
            if(currentNode.arr[index]!=null){
                //go to the next node
                currentNode = currentNode.arr[index];
            }else{
                return null;
            }
        }

        if(currentNode==root)
            return null;
        //once you go through each character you will
        //end up at the node containing that string
        //so return the node
        return currentNode;
    }

//***************************************************************************


}
















