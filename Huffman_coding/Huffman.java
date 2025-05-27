import java.io.*;
import java.util.*;

public class Huffman {

    //Building the Huffman tree
    //Return the first element in the List. That the root of the huffman tree
    
    public static BinaryTree<Pair> HuffmanCoding (ArrayList<BinaryTree<Pair>> ListS){
        ArrayList<BinaryTree<Pair>> ListT = new ArrayList<>();
        while(!ListS.isEmpty()){

            Random random = new Random();

            BinaryTree<Pair> A = new BinaryTree<>();
            BinaryTree<Pair> B = new BinaryTree<>();

            if(ListT.isEmpty()){ //If T is empty
                A = ListS.get(0); //Remove the first element of S
                ListS.remove(0);  //Data will be shifted. Old 2nd is now 1rst
                B = ListS.get(0); // Remove the new first element of S
                ListS.remove(0);
            }
            else{
                //If T is not empty

                //i) Find the smaller weight tree of the trees in front of S and in front of T.
                //        This is A. Dequeue it.
                if(!ListS.isEmpty() && (ListT.isEmpty() || ListS.get(0).getData().getProbability() < ListT.get(0).getData().getProbability())){
                    A = ListS.get(0);
                    ListS.remove(0);
                } else if (!ListT.isEmpty()){
                    A = ListT.get(0);
                    ListT.remove(0);
                }

                //ii) Find the smaller weight tree of the trees in front of S and in front of T.
                //       This is B. Dequeue it.
                if(!ListS.isEmpty() && (ListT.isEmpty() || ListS.get(0).getData().getProbability() < ListT.get(0).getData().getProbability())){
                    B = ListS.get(0);
                    ListS.remove(0);
                } else if (!ListT.isEmpty()){
                    B = ListT.get(0);
                    ListT.remove(0);
                }
            }

            if(A.getData() != null && B.getData() != null) {
                BinaryTree<Pair> P = new BinaryTree<>();
                Pair pair = new Pair((char) (random.nextInt(26)+65), A.getData().getProbability() + B.getData().getProbability());
                P.makeRoot(pair);

                //Attach A and B to P
                //If A.probability is less than B.probability, attach A to the left of P and B to the Right and vice versa
                if (A.getData().getProbability() < B.getData().getProbability()) {
                    P.attachLeft(A);
                    P.attachRight(B);
                } else {
                    P.attachLeft(B);
                    P.attachRight(A);
                }
                ListT.add(P);
            }
        }

        buildTree(ListT);

        return ListT.get(0);
    }

    private static void buildTree(ArrayList<BinaryTree<Pair>> ListT) {
        while(ListT.size()>1){
            Random random = new Random();

            BinaryTree<Pair> A = new BinaryTree<>();
            BinaryTree<Pair> B = new BinaryTree<>();

            A = ListT.get(0); 
            ListT.remove(0);  
            B = ListT.get(0); 
            ListT.remove(0);

            if(A.getData() != null && B.getData() != null) {
                BinaryTree<Pair> P = new BinaryTree<>();
                Pair pair = new Pair((char) (random.nextInt(26) + 'a'), A.getData().getProbability() + B.getData().getProbability());
                P.makeRoot(pair);

                //Attach A and B to P
                //If A.probability is less than B.probability, attach A to the left of P and B to the Right and vice versa
                if (A.getData().getProbability() < B.getData().getProbability()) {
                    P.attachLeft(A);
                    P.attachRight(B);
                } else {
                    P.attachLeft(B);
                    P.attachRight(A);
                }
                ListT.add(P);
            }
        }
    }

    public static ArrayList<Character> Encode(char[] Words, String[] Encoder){

        ArrayList<Character> Output = new ArrayList<>();
        for(char x : Words){ //For each char in the array,
            if(x == ' '){ //if the Char is ' ', print it and append the empty String to list of characters in the encoded message
                System.out.print(" ");
                Output.add(' ');
            } else{ // Else
                int index = x - 'A';
                if(index >= 0 && index < Encoder.length && Encoder[index] != null){
                    String value = Encoder[index];
                    System.out.print(value);
                    for(char code : value.toCharArray()){
                        Output.add(code); //Add every binary char of the string being encoded in the Output list
                    }
                }
                
            }
        }

        return Output;
    }

    public static ArrayList<Character> Decode(ArrayList<Character> Output, BinaryTree<Pair> huffmanTree){
        ArrayList<Character> Decoded = new ArrayList<>();
        BinaryTree<Pair> current = huffmanTree;  // Start at the root of the tree

        for (char Out : Output) {
            // Traverse left for '0', right for '1'
            if (Out == '0' && current.getLeft() != null) {
                current = current.getLeft();
            } else if (Out == '1' && current.getRight() != null) {
                current = current.getRight();
            } else if(Out == ' ') {
                Decoded.add(' ');
                current = huffmanTree;
            } else if (Out == '0' && current.getLeft() == null) {
                Decoded.add(current.getData().getValue());
                current = huffmanTree;
            }  else if (Out == '1' && current.getRight() == null) {
                current = current.getRight();
                Decoded.add(current.getData().getValue());
                current = huffmanTree;
            }

            // If we've reached a leaf node, retrieve the character
            if (current.getLeft() == null && current.getRight() == null) {
                Decoded.add(current.getData().getValue());  // Append the character
                current = huffmanTree;  // Reset to the root for the next sequence
            }
        }
        return Decoded;
    }
    
    public static void main (String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        Scanner kb = new Scanner(System.in);
        String filePath = "LettersProbability.txt";

        File fileName = new File(filePath);

        Scanner file = new Scanner(fileName);

        ArrayList<BinaryTree<Pair>> ListS = new ArrayList<>();

        while(file.hasNextLine()){
            
            String line = file.nextLine();
            String[] parts = line.split("\\s+");
            char value = parts[0].charAt(0);
            double probability = Double.parseDouble(parts[1]);
            
            Pair pair = new Pair(value, probability);
            BinaryTree<Pair> data = new BinaryTree<>();
            data.makeRoot(pair);
            ListS.add(data);
        }

        //Build our Huffman tree
        BinaryTree<Pair> huffmanTree = HuffmanCoding(ListS);
        String[] Encoder = findEncoding(huffmanTree);

        file.close();

        System.out.println("Enter 1 to encode a message or 2 to decode");

        int ans = kb.nextInt();
        kb.nextLine();

        if (ans == 1){
            System.out.print("Enter a line of text: ");
            String  Message = kb.nextLine();
            /*Converting the string "Message" gotten through the console into an array of Characters*/
            char[] Words = Message.toCharArray();

            for(int i=0; i<Words.length; i++){
                char x = Words[i];
                if(x == Character.toUpperCase(x)){
                    continue;
                } else {
                    x = Character.toUpperCase(x);
                }
                Words[i] = x;
            }

            //Encoding
            //Printing the Encoded Message and creating a list of all charcaters in that message
            System.out.print("Here's the encoded line: ");
            Encode(Words, Encoder);
            System.out.println(); 
            kb.close();
        } else if (ans == 2){
            System.out.print("Enter a line of text (1s, 0s and Spaces characters only): ");
            String  Message = kb.nextLine();

            char[] Code = Message.toCharArray(); // Converting input into character

            for(char x : Code){  
                /*
                 * This is to check if the user enters invalid character
                 * If the user enters invalid character (a for example), The code will 
                 * Print a message, then throw an error
                 */
                if (x != '1' && x != '0' && x != (' ')){
                    System.out.println("Invalid character " + x + " Please only enter 1s, 0s, and Spaces");
                    kb.close();
                    throw new UnsupportedEncodingException ();
                }
            }

            ArrayList<Character> Output = new ArrayList<Character>();
            /*
             * Our input will be in th form 1100 1011
             * Each digit is a direction in our tree (1 for left and 0 for right)
             * Paste each digit into the Arraylist
             */
            for(char x : Code){
                Output.add(x); 
            }
             //Decoding
            ArrayList<Character> Decoded = Decode(Output, huffmanTree);//Created an ArrayList containing all the decoded letters form the encoded message
    
            System.out.println("Decoding...");
            //Display the decode message
            System.out.print("The decoded line is: ");
            for(char code : Decoded) System.out.print(code);

            kb.close();
        }else {
            System.out.println("Thank you. Enjoy your day :)");
            kb.close();
        }
        kb.close();

    }

    private static String[] findEncoding(BinaryTree<Pair> bt){ 
        String[] result = new String[26]; 
        findEncoding(bt, result, ""); 
        return result; 
    } 
         
    private static void findEncoding(BinaryTree<Pair> bt, String[] a, String prefix){ 
        // test is node/tree is a leaf 
        if (bt.getLeft()==null && bt.getRight()==null){ 
            a[bt.getData().getValue() - 65] = prefix; 
        } 
        // recursive calls 
        else{ 
            findEncoding(bt.getLeft(), a, prefix+"0"); 
            findEncoding(bt.getRight(), a, prefix+"1"); 
        } 
    }
}
