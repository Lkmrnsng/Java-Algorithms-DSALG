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
		//Adds friendship a as a key to the hash address b, if the friendship doesn't exist yet
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
        if (!this.adjacencyList.containsKey(personA) || !this.adjacencyList.containsKey(personB)) {
            System.out.println("Error! One or both persons not found.");
        } 
		
		else {
            ArrayList<Integer> path = findConnectionPath(personA, personB);
            if (path != null) {
                System.out.println("There is a connection from " + personA + " to " + personB + "!");
                for (int i = 0; i < path.size() - 1; i++) {
                    int current = path.get(i);
                    int next = path.get(i + 1);
                    System.out.println(current + " is friends with " + next);
                }
				
				System.out.print("\n");
            } 
			
			else {
                System.out.println("No connection found between " + personA + " and " + personB);
				System.out.print("\n");
            }
        }
	}

	//  breadth-first search (BFS)
	private ArrayList<Integer> findConnectionPath(int personA, int personB) {
		HashSet<Integer> visited = new HashSet<Integer>();                  // used to monitor the people that have visited a node so that it wont repeat
		ArrayList<Integer> path = new ArrayList<Integer>();                 // stores the path when a connection is determined between person a and b
		LinkedList<Integer> queue = new LinkedList<>();                     // used to implement BFS exploration, starts with person a
		HashMap<Integer, Integer> parentMap = new HashMap<>();              // imaginary map that is used of the parent that is encountered during the BFS
																			// used to trace the path from person b to person a

		queue.add(personA);                                                 // adds person a to the queue
		visited.add(personA);                                               // marks person a as visited
		parentMap.put(personA, -1);                                         // sets the parent of person a to -1,which means persona is the startpoint

		while (!queue.isEmpty()) {                                          // repeats till there are no more people to explore
			int currentPerson = queue.poll();                               // removes the first person in the queue 
			
			if (currentPerson == personB) {                                 // checks if person b is found, if yes, a connection is found
				ArrayList<Integer> connectionPath = new ArrayList<Integer>();  // stores the path from person b to person a
				int current = personB;                                      // person b is the current
				
				while (current != -1) {                                     // traces the path from person b to person a
					connectionPath.add(current);                            // adds the current person to the path
					current = parentMap.get(current);                       // sets the current person to the parent of the current person
				}
				
				Collections.reverse(connectionPath);                        // reverses the path
				return connectionPath;                                      // returns the path
			}

			for (int friend : adjacencyList.getOrDefault(currentPerson, new ArrayList<Integer>())) { // explore the friends of the current person
				if (!visited.contains(friend)) {                                                // checks if the friend has been visited
					queue.add(friend);                                                          // adds the friend to the queue
					visited.add(friend);                                                        // marks the friend as visited
					parentMap.put(friend, currentPerson);                                       // sets the parent of the friend to the current person
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

		System.out.print("Input file path without .txt: ");
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
