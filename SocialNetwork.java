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

	public void getConnections(int personA, int personB) {
		//code here + edit function call if needed
		//@jan feel free to add helper functions
        if (!adjacencyList.containsKey(personA) || !adjacencyList.containsKey(personB)) {
            System.out.println("Error! One or both persons not found.");
        } else {
            List<Integer> path = findConnectionPath(personA, personB);
            if (path != null) {
                System.out.println("There is a connection from " + personA + " to " + personB + "!");
                for (int i = 0; i < path.size() - 1; i++) {
                    int current = path.get(i);
                    int next = path.get(i + 1);
                    System.out.println(current + " is friends with " + next);
                }
            } else {
                System.out.println("No connection found between " + personA + " and " + personB);
            }
        }
}

private List<Integer> findConnectionPath(int personA, int personB) {
    HashSet<Integer> visited = new HashSet<>();
    List<Integer> path = new ArrayList<>();
    LinkedList<Integer> queue = new LinkedList<>();
    HashMap<Integer, Integer> parentMap = new HashMap<>();

    queue.add(personA);
    visited.add(personA);
    parentMap.put(personA, -1);

    while (!queue.isEmpty()) {
        int currentPerson = queue.poll();
        if (currentPerson == personB) {
            List<Integer> connectionPath = new ArrayList<>();
            int current = personB;
            while (current != -1) {
                connectionPath.add(current);
                current = parentMap.get(current);
            }
            Collections.reverse(connectionPath);
            return connectionPath;
        }

        for (int friend : adjacencyList.getOrDefault(currentPerson, new ArrayList<>())) {
            if (!visited.contains(friend)) {
                queue.add(friend);
                visited.add(friend);
                parentMap.put(friend, currentPerson);
            }
        }
    }

    return null;
}

    public static void main(String[] args) throws FileNotFoundException {
		//Gets filepath from user

        int menu = 0;
		int firstid = 0;
		int secondid = 0;

		System.out.print("Input file path (without '.txt'): ");
		Scanner userInput = new Scanner(System.in);


		String input = userInput.next();
		String filepath = "data/" + input + ".txt"; // Add ".txt" to the file name
		
		File file = new File(filepath);

        if (file.exists()) { // Check if the file exists
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
			System.out.print("Enter the ID of the first person: ");
            firstid = userInput.nextInt();
            System.out.print("Enter the ID of the second person: ");
            secondid = userInput.nextInt();
            socialNetwork.getConnections(firstid, secondid);
			}
			
		} while (menu != 3);
		} 
		else{
    		System.out.println("File not found: " + filepath);
			}

		
		userInput.close();
    }
}
