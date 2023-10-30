import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SocialNetwork {
    HashMap<Integer, ArrayList<Integer>> adjacencyList;

    public SocialNetwork() {
        adjacencyList = new HashMap<>();
    }

    public void addFriendship(int a, int b) {
        adjacencyList.computeIfAbsent(a, k -> new ArrayList<>()).add(b);
        adjacencyList.computeIfAbsent(b, k -> new ArrayList<>()).add(a);
    }

    public List<Integer> getFriends(int id) {
        return adjacencyList.getOrDefault(id, new ArrayList<>());
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("data/Rice31.txt"); // Replace with the actual file path
        Scanner scanner = new Scanner(file);
        SocialNetwork socialNetwork = new SocialNetwork();

        int n = scanner.nextInt();
        int e = scanner.nextInt();

        for (int i = 0; i < e; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            socialNetwork.addFriendship(a, b);
        }

        // Example usage:
        List<Integer> friendsOf123 = socialNetwork.getFriends(123);
        System.out.println("Friends of 123: " + friendsOf123);
    }
}
