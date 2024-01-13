import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner input = new Scanner(System.in);
        int item;
        boolean running = true;

        while(running) {
            System.out.println("Choose any one of the following options. Enter an integer 'i' for selecting the 'i'-th option.");
            System.out.println("1 ... Insert an item");
            System.out.println("2 ... Search for an item");
            System.out.println("3 ... Get in order successor of an item");
            System.out.println("4 ... Get in order predecessor of an item");
            System.out.println("5 ... Delete an item");
            System.out.println("6 ... Get depth of an item");
            System.out.println("7 ... Get the maximum item from the BST");
            System.out.println("8 ... Get the minimum item from the BST");
            System.out.println("9 ... Get height of the BST");
            System.out.println("10 ... Get size of the BST ");
            System.out.println("11 ... Print the BST in-order");
            System.out.println("12 ... Print the BST pre-order");
            System.out.println("13 ... Print the BST post-order");
            System.out.println("14 ... Quit program");

            try {
                int choice = input.nextInt();
                if(choice < 1 || choice > 14)   throw new IndexOutOfBoundsException();
                if(choice <=6) {
                    System.out.print("Enter the item: ");
                    item = input.nextInt();
                    System.out.println();
                    if(choice == 1)  bst.insertItem(item);
                    else if(choice == 2) bst.searchItem(item);
                    else if(choice == 3) System.out.println("In order successor of " + item + " = " + bst.getInOrderSuccessor(item));
                    else if(choice == 4) System.out.println("In order predecessor of " + item + " = " + bst.getInOrderPredecessor(item));
                    else if(choice == 5) bst.deleteItem(item);
                    else System.out.println("Depth of item "+ item + " : "+ bst.getItemDepth(item));
                } else {
                    if(choice == 7) System.out.println("Maximum item : "+bst.getMaxItem());
                    else if(choice == 8) System.out.println("Minimum item : "+bst.getMinItem());
                    else if(choice == 9) System.out.println("Height : "+bst.getHeight());
                    else if(choice == 10) System.out.println("Size: "+bst.getSize());
                    else if(choice == 11) bst.printInOrder();
                    else if(choice == 12) bst.printPreOrder();
                    else if(choice == 13) bst.printPostOrder();
                    else    running = false;
                }
                System.out.println();
            } catch(Exception e) {
                if(e instanceof IndexOutOfBoundsException) {
                    System.out.println("Invalid choice : out of range.");
                }
                else if(e instanceof InputMismatchException) {
                    System.out.println("Invalid choice : not an integer.");
                }
                else e.printStackTrace();
            }
        }

        input.close();
    }
}
