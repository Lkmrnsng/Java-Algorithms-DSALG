public class Main {
    public static void main(String[] args) {
		//String filePath = "data/random100.txt";
		//String filePath = "data/random25000.txt";
		//String filePath = "data/random50000.txt";
		//String filePath = "data/random75000.txt";
		//String filePath = "data/random100000.txt";
		//String filePath = "data/almostsorted.txt";
		String filePath = "data/totallyreversed.txt";
        FileReader fileReader = new FileReader();
        SortingAlgorithms algos = new SortingAlgorithms();
        Record[] records = fileReader.readFile(filePath);
		long startTime = System.currentTimeMillis();

        //algos.insertionSort(records, records.length);
        //algos.selectionSort(records, records.length);
		//algos.mergeSort(records, 0, records.length - 1);
		algos.quickSort(records, 0, records.length - 1);

		long endTime = System.currentTimeMillis(); 
        long executionTime = endTime - startTime;
		
        for (Record record : records) {
            int id = record.getIdNumber();
            String name = record.getName();
            System.out.println(id + " " + name);
        }
		
		System.out.println("\nExecution Time = " + executionTime);
    }
}
