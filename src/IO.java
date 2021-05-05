import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class IO
{
    // Attributes
    boolean exit = false;


    // Header for the menu
    private void printHeader()
    {
        // Creating the header for our menu
        System.out.println("------------------------------");
        System.out.println("|          Welcome           |");
        System.out.println("------------------------------");
    }

    // Header for the sub-menu
    private void printSubHeader()
    {
        // Creating the header for our menu
        System.out.println("------------------------------");
        System.out.println("|      Enter information     |");
        System.out.println("------------------------------");
    }

    // Main menu options
    private void printMenu()
    {
        // Creating options for our menu
        System.out.println("\nPlease make a selection");
        System.out.println("1) Search for a book");
        System.out.println("2) Add a book to the database");
        System.out.println("3) Exit the program");
    }

    // Method to run the main menu
    protected void runMenu()
    {
        // Show the header
        printHeader();

        // Show the menu unless in exit state
        while (!exit)
        {
            printMenu();

            // Get the menu choice from the user
            int choice = getInput();

            performAction(choice);
        }
    }

    // Method to handle the input menu choice of the user
    private int getInput()
    {
        Scanner scan = new Scanner(System.in);

        // Set variable to a number that won't conflict with the numbers of our menu options
        int choice = -1;

        while(choice <= 0 || choice > 3)
        {
            try
            {
                System.out.print("\nEnter selection here:");

                // Int isn't a string so have to wrap it in a parse
                choice = Integer.parseInt(scan.nextLine().trim());
            }
            catch(NumberFormatException e)
            {
                // If the menu selection number isn't valid then print out an error and ask for another number
                System.out.println("Invalid Selection.  Please try again");
            }

        }


        return choice;

    }
    // Method to do something with the menu choice
    private void performAction(int choice)
    {
        switch (choice)
        {
            case 1:
                //Send search string to the server
                printSubHeader();
                Scanner sc= new Scanner(System.in);
                System.out.print("Enter the name of the book you are searching for: ");
                String bookSearch= sc.nextLine();
                try{
                    Socket s=new Socket("127.0.0.1",3000);
                    DataOutputStream dout=new DataOutputStream(s.getOutputStream());
                    dout.writeUTF(bookSearch);
                    dout.flush();
                    dout.close();
                    s.close();
                    //DataInputStream returnMessage = new DataInputStream(s.getInputStream());
                }catch(Exception e){System.out.println(e);}
                break;
            case 2:
                //Send string to be added to the database
                printSubHeader();
                Scanner sc1= new Scanner(System.in);
                System.out.print("Enter the details you would like to add to the database: ");
                String bookAdd= sc1.nextLine();
                try{
                    Socket s=new Socket("127.0.0.1",3000);
                    DataOutputStream dout=new DataOutputStream(s.getOutputStream());
                    dout.writeUTF(bookAdd);
                    dout.flush();
                    dout.close();
                    s.close();
                    //DataInputStream returnMessage = new DataInputStream(s.getInputStream());
                }catch(Exception e){System.out.println(e);}
                break;
            case 3:
                exit = true;
                System.out.print("Goodbye \n");
                break;
            default:
                System.out.print("Error has occurred");
        }
    }
}