package hw7.AutocompleteSuggestions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Dictionary{

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int wordsToAddInDictionary = Integer.parseInt(tokenizer.nextToken());
        int searchRequests = Integer.parseInt(tokenizer.nextToken());

        Dictionary dictionary = new Dictionary();

        tokenizer = new StringTokenizer(reader.readLine());
        for(int i = 0; i < wordsToAddInDictionary; i++){
            String currWord = tokenizer.nextToken();
            dictionary.insertWord(currWord);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < searchRequests; i++){
            tokenizer = new StringTokenizer(reader.readLine());
            String prefix = tokenizer.nextToken();
            Node endOfPrefix = dictionary.getPrefixEnd(prefix);
            int wordsFound = dictionary.countWords(endOfPrefix);
            sb.append(wordsFound + "\n");

        }

        System.out.print(sb.toString());
        reader.close();
    }

    private Node root;

    public Dictionary(){
        root = new Node();
    }

    public void insertWord(String newWord){
        Node currNode = root;

        for(int i = 0; i < newWord.length(); i++){
            char currLetter = newWord.charAt(i);
            int currLettersIndex = currLetter - 'a';

            if(currNode.children[currLettersIndex] == null){
                Node temp = new Node();
                currNode.children[currLettersIndex] = temp;
                currNode = temp;

            } else {
                currNode = currNode.children[currLettersIndex];

            }
        }
        currNode.isWordEnd = true;
    }

    public Node getPrefixEnd(String prefix){
        Node currNode = root;

        for(int i = 0; i < prefix.length(); i++){

            char currLetter = prefix.charAt(i);
            int currLetterIndex = currLetter - 'a';

            if(currNode.children[currLetterIndex] != null){
                currNode = currNode.children[currLetterIndex];


            } else return null;
        }

        if(currNode == null)
            return null;

        return currNode;
    }

    public int countWords(Node endOfPrefix){

        int wordsFound = 0;
        if(endOfPrefix != null) {
            if (endOfPrefix.isWordEnd)
                wordsFound++;

            for (int i = 0; i < 26; i++) {

                if (endOfPrefix.children[i] != null)
                    wordsFound += countWords(endOfPrefix.children[i]);

            }
        }
        return wordsFound;
    }
}

class Node {
    Node[] children;
    boolean isWordEnd;

    public Node(){
        this.children = new Node[26];
        this.isWordEnd = false;
    }
}