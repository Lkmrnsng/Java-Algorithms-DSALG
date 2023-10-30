import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SocialNetwork {
    HashMap<Integer, ArrayList<Integer>> adjacencyList;
	ArrayList<Integer> friendList;

    public SocialNetwork() {
        this.adjacencyList = new HashMap<Integer, ArrayList<Integer>>();
		this.friendList = new ArrayList<Integer>();
    }

    public void addFriendship(int a, int b) {
		//Adds friendship a as a key to the hash address b
        this.adjacencyList.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
    }

    public void getFriendList(int id) {
		int numberOfFriends = 0;
		this.friendList.clear();
		this.friendList = this.adjacencyList.get(id);
		
		if(this.friendList != null) {
			numberOfFriends = this.friendList.size();
			String formattedString = this.friendList.toString()
				.replace(",", " ")  //remove the commas
				.replace("[", "")   //remove the right bracket
				.replace("]", "")   //remove the left bracket
				.trim();            //remove trailing spaces from partially initialized arrays
			
			System.out.println("\nPerson " + id + " has " + numberOfFriends + " friends!");
			System.out.println("List of friends: " + formattedString + "\n");
		}
		
		else {
			System.out.println("Error! Person " + id + " not found.\n");
		}
    }

	public void getConnections() {
		//code here + edit function call if needed
		//@jan feel free to add helper functions
	}

    public static void main(String[] args) throws FileNotFoundException {
		//Gets filepath from user
		System.out.print("Input file path: ");
		Scanner userInput = new Scanner(System.in);
		String input = userInput.next();
		String filepath = "data/" + input;
		
		File file = new File(filepath);
        Scanner fileScanner = new Scanner(file);
        SocialNetwork socialNetwork = new SocialNetwork();

		//Loads file into adjacent list and in this case hashmap
        int n = fileScanner.nextInt();
        int e = fileScanner.nextInt();

        for (int i = 0; i < e; i++) {
            int a = fileScanner.nextInt();
            int b = fileScanner.nextInt();
            socialNetwork.addFriendship(a, b);
        }
		
		fileScanner.close();
        System.out.println("Graph loaded!");
		
		int menu = 0;
		int firstid = 0;
		int secondid = 0;
		
		//Recursively calls menu options
		do {
			System.out.println("MAIN MENU\n[1] Get friend list\n[2] Get connection\n[3] Exit");
			System.out.print("\nEnter your choice: ");
			menu = userInput.nextInt();
			
			if(menu == 1) {
				System.out.print("Enter ID of person: ");
				firstid = userInput.nextInt();
				socialNetwork.getFriendList(firstid);
			}
			
			if(menu == 2) {
				//@jan code goes here
			}
			
		} while (menu != 3);
		userInput.close();
    }
}
