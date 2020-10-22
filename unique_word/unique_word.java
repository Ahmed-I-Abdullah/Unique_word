import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.InputMismatchException;

public class unique_word {

private static Scanner file_reader = new Scanner(System.in); //Static scanner (only one copy needed for the whole class) connected to the system.in InputStream


public static void main(String[] args)
    {
        try {
            String file_name = "";
            File input_file = new File(file_name);
            int wrong_counter_1 = 0, wrong_counter_2 = 0;
            int traversal_order;
            String to_search;
            Node search_result;
            String user_answer;

            while (!input_file.exists() && wrong_counter_1 < 3) { // keep asking for a correct filename if a non existing file is entered up to 3 trials then terminate

                System.out.print("Please enter the file name to create a BST with or without extension.\nFor Example: test.txt OR test\nFile Name:"); //Prompt user to type file name
                file_name = file_reader.next(); //store file entered name

                if (!(file_name.substring(file_name.length() - 4, file_name.length()).equals(".txt"))) { // if user did not enter file extension, add a .txt file extension
                    file_name += ".txt";
                }

                input_file = new File(file_name);

                if (!input_file.exists())
                    System.out.println("\nFile not found.\nPlease enter file name including extension. For Example: test.txt\n");
                else
                    System.out.println("\nReading file input. Please Wait.\n");

                wrong_counter_1++;
            }
			

            String noExtension_name = file_name.substring(0, file_name.length() - 4); // remove the extension for later printing of file name

            FileInputStream file_input_stream = new FileInputStream(input_file); //obtain input bytes from a input_file
            byte[] arr_byte = new byte[(int)input_file.length()];
            file_input_stream.read(arr_byte); // Read contents of input file into a byte array
            String str_byte = new String(arr_byte); // construct a string by passing the byte array as an argument

            String[] tokens_array = str_byte.replaceAll("[\\W&&[^']]", " ").replaceAll("'", "").toLowerCase().split("\\s+"); //replace anything other than letters with a pace bar then split the string when there is one ore more space bar


            BST my_tree = new BST();

            for (String val : tokens_array) // add all words from file to the created tree
                my_tree.add_node(val);
				

            System.out.println(">Total number of words in " + noExtension_name + " = " + my_tree.number_of_words(my_tree.root));
            System.out.println(">Number of unique words in " + noExtension_name + " = " + my_tree.number_unique_words(my_tree.root));
            System.out.println(">The word(s) which occur(s) most often and the number of times that it/they occur(s) =\n");
            my_tree.print_most_repeated(my_tree.root, my_tree.get_highest_frequency(my_tree.root));
			

            while (true) { // keep asking for a word to search for until user exits loop

                System.out.print("\n\n>Enter the word you are looking for in " + noExtension_name + " or press x to exit:");
                to_search = file_reader.next();
                if (to_search.equals("x") || to_search.equals("X")) //exit loop if user enters x
                    break;


                while (wrong_counter_2 < 3) { // keep asking user whether he wants to display the process up to 3 wrong entries
                    System.out.print("\n\n>Would you like to display the process of searching the tree to find the word? Type(yes or no):");
                    user_answer = file_reader.next();

                    if (user_answer.equals("yes") || user_answer.equals("Yes") || user_answer.equals("YES")) { //if answer is yes call function which displays the process
                        search_result = my_tree.look_trace_node(to_search);
                    }

                    else if (user_answer.equals("no") || user_answer.equals("No") || user_answer.equals("NO")) { //if answer is no call function which does not displays the process
                        search_result = my_tree.look_for_node(to_search);

                        if (search_result == null)
                            System.out.println("------------------------------------------------\nTree traversal done.\n" + "Word Not Found!\n------------------------------------------------\n");
                    }

                    else { // display an error message if user did not type yes or no
                        System.out.println("\n---------------Input incorrect. Please enter yes or no.---------------");
                        wrong_counter_2++;
                        if (wrong_counter_2 == 3)
                            System.out.println("\nToo many wrong inputs. You will be asked to search for the word again or search for another word");
                        continue;
                    }

                    if (search_result != null)
                        System.out.println("------------------------------------------------\nFound! -" + to_search + "- appears " + search_result.word_frequency + " time(s) in the file " + noExtension_name + "\n------------------------------------------------\n");

                    break;
                }
            }



            while (true) { // keep asking for a traversal method until users chooses to exit loop

                System.out.print("\n\n>Enter the BST traversal method (0=EXIT, 1=IN-ORDER, 2=PRE-ORDER, 3=POST-ORDER) for " + noExtension_name + " :");
                try {
                    traversal_order = file_reader.nextInt();

                    if (traversal_order < 0 || traversal_order > 3) // throw an exception and display an error message if user enters a number out of range
                        throw new InputMismatchException();
                }
                catch (InputMismatchException ex) {
                    System.out.println("\nInvalid input! Please choose one of the options:(0=EXIT, 1=IN-ORDER, 2=PRE-ORDER, 3=POST-ORDER)");
                    file_reader.nextLine();
                    continue;
                }
				
				// choose appropriate traversal method according to user entry
                if (traversal_order == 0) {
                    System.out.println("Program Terminating ..................");
                    break;
                }
                if (traversal_order == 1) {
                    System.out.println("\n*** In-Order Output:\n");
                    my_tree.inorder_print(my_tree.root);
                }

                else if (traversal_order == 2) {
                    System.out.println("\n*** Pre-Order Output:\n");
                    my_tree.preorder_print(my_tree.root);
                }

                else if (traversal_order == 3) {
                    System.out.println("\n*** Post-Order Output:\n");
                    my_tree.postorder_print(my_tree.root);
                }

                System.out.println("\n\n***");
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("File not found,Maximum number of trials exceeded\nTERMINATING------- ");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}