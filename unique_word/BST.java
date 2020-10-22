// This class represents an object containing the left and right child of each node and their stored data
class Node {

    String unique_word;
    int word_frequency;
    Node left_child;
    Node right_child;

    /*Class Constructor
    Requirements: String containing the word to be stored in the node
    Promises: sets unique_word to input_string and word_frequency to 1*/
    Node(String input_string)
    {
        unique_word = input_string;
        word_frequency = 1;
    }

}

// This class contains the binary search tree implementation
public class BST {

    // Tree first node (root)
    Node root;



    /*
    Requirements: A String containing the word to be inserted in the binary search tree
    Promises: Creates a new node containing the word and insert it at the correct position in the tree */
public void add_node(String new_word)
    {

        Node recent_node = new Node(new_word);

        if (root == null) // if binary search tree does not exist, create a new node as root
            root = recent_node;

        else {
            Node traversal_node = root;
            Node recent_parent;

            while (true) {
                recent_parent = traversal_node;

                if (new_word.compareTo(traversal_node.unique_word) < 0) { // if word is smaller than current word, go to left sub-tree
                    traversal_node = traversal_node.left_child;

                    if (traversal_node == null) { // if end of sub-tree is reached insert node at current position
                        recent_parent.left_child = recent_node;
                        break;
                    }
                }
                else if (new_word.compareTo(traversal_node.unique_word) > 0) { // if word is greater than current word, go to right sub-tree
                    traversal_node = traversal_node.right_child;

                    if (traversal_node == null) { // if end of sub-tree is reached insert node at current position
                        recent_parent.right_child = recent_node;
                        break;
                    }
                }
                else { // if word already exists, just increase its frequency
                    traversal_node.word_frequency++;
                    break;
                }
            }
        }
    }



    /*method for in-order traversal of a binary search tree
 Requirements: A node from which to start traversing
 Promises: Print words in the tree by visiting left sub-tree then root then right sub-tree
*/
public void inorder_print(Node start_node)
    {

        if (start_node == null) // stop printing if no node found
            return;

        inorder_print(start_node.left_child);
        System.out.print(start_node.unique_word + " ");
        inorder_print(start_node.right_child);
    }




    /*method for post-order traversal of a binary search tree
 Requirements: A node from which to start traversing
 Promises: Print words in the tree by visiting left sub-tree then right sub-tree then the root
*/
public void postorder_print(Node start_node)
    {

        if (start_node == null) // stop printing if no node found
            return;

        postorder_print(start_node.left_child);
        postorder_print(start_node.right_child);
        System.out.print(start_node.unique_word + " ");
    }




    /*method for pre-order traversal of a binary search tree
 Requirements: A node from which to start traversing
 Promises: Print words in the tree by visiting the root then left sub-tree then right sub-tree 
*/
public void preorder_print(Node start_node)
    {

        if (start_node == null) // stop printing if no node found
            return;

        System.out.print(start_node.unique_word + " ");
        preorder_print(start_node.left_child);
        preorder_print(start_node.right_child);
    }




    /*method for getting the number of words (nodes) in a binary search tree
 Requirements: A node from which to start traversing
 Promises:  return the total number of nodes in the tree
*/
public int number_of_words(Node start_node)
    {
        int words_counter = 1;

        if (start_node == null)
            return 0;

        words_counter += number_of_words(start_node.left_child);
        words_counter += number_of_words(start_node.right_child);

        return words_counter;
    }



    /*
 Requirements: A node from which to start traversing
 Promises:return the total number of words (nodes) with frequency equal to 1
*/
public int number_unique_words(Node start_node)
    {
        int words_counter = 0;

        if (start_node == null)
            return 0;

        if (start_node.word_frequency == 1) // only return 1 if the frequency is equal to 1
            words_counter = 1;

        words_counter += number_unique_words(start_node.left_child);
        words_counter += number_unique_words(start_node.right_child);

        return words_counter;
    }



    /*
 Requirements: A node from which to start traversing
 Promises:return the highest frequency in the binary search tree
*/
public int get_highest_frequency(Node start_node)
    {

        if (start_node != null) { // if not base case

            //return the maximum of the three frequencies namely: root, left sub-tree, right sub-tree
            int max_frequency = start_node.word_frequency;
            int left_frequency = get_highest_frequency(start_node.left_child);
            int right_frequency = get_highest_frequency(start_node.right_child);

            // compare values and set max_frequency to the highest frequency found
            if (left_frequency > max_frequency)
                max_frequency = left_frequency;
            if (right_frequency > max_frequency)
                max_frequency = right_frequency;
            return max_frequency;
        }
        else
            return 0;
    }



    /*
 Requirements: A node from which to start traversing the tree and a frequency to search for
 Promises: prints each word with matching frequency on a separate line in the format -word = frequency-
*/
    void print_most_repeated(Node start_node, int frequency)
    {

        if (start_node == null) // stop printing if no node found
            return;

        //using recursive post-order traversal to search and print words with matching frequency
        print_most_repeated(start_node.left_child, frequency);

        print_most_repeated(start_node.right_child, frequency);

        if (start_node.word_frequency == frequency)
            System.out.println(start_node.unique_word + " = " + frequency + " times");
    }



    /*
 Requirements: A string containing a word to search for in the tree
 Promises: returns the node containing the word, prints the process of searching and whether the word was found or not*/
public Node look_trace_node(String input_string)
    {

        Node traversal_node = root; // start searching at the root of the binary search tree
        System.out.println("\nStarting at the root node:");
        while (!(traversal_node.unique_word.equals(input_string))) { //keep traversing tree while word is not found

            if (input_string.compareTo(traversal_node.unique_word) < 0) { // if word is smaller than current word, search in left subtree
                System.out.println(input_string + " < " + traversal_node.unique_word + " ? Yes -> Go to the left child.");
                traversal_node = traversal_node.left_child;
            }

            // if word is smaller than current word, search in left sub-tree
            else {
                System.out.println(input_string + " < " + traversal_node.unique_word + " ? NO; " + input_string + " > " + traversal_node.unique_word + " ? Yes -> Go to the right child.");
                traversal_node = traversal_node.right_child;
            }

            // if tree is finished and node not found return null
            if (traversal_node == null) {
                System.out.println("------------------------------------------------\nTree traversal done.\n" + "Word Not Found!\n------------------------------------------------\n");
                return null;
            }
        }
        System.out.println(input_string + " < " + input_string + " ? No -> " + input_string + " > " + input_string + " ? No -> Found it!");

        return traversal_node;
    }


public Node look_for_node(String input_string)
    {

        Node traversal_node = root; // start searching at the root of the binary search tree

        while (!(traversal_node.unique_word.equals(input_string))) { //keep traversing tree while word is not found

            if (input_string.compareTo(traversal_node.unique_word) < 0) // if word is smaller than current word, search in left sub-tree
                traversal_node = traversal_node.left_child;

            // if word is smaller than current word, search in left sub-tree
            else
                traversal_node = traversal_node.right_child;

            // if tree is finished and node not found return null
            if (traversal_node == null)
                return null;
        }

        return traversal_node;
    }
}