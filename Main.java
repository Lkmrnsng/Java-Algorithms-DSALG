public class Main {
    public static void main(String[] args) {
        String filePath = "data/random100000.txt";
        FileReader fileReader = new FileReader();
        SortingAlgorithms algos = new SortingAlgorithms();
        Record[] records = fileReader.readFile(filePath);
        
        //algos.insertionSort(records, records.length);
        //algos.selectionSort(records, records.length);


        for (Record record : records) {
            int id = record.getIdNumber();
            String name = record.getName();
            System.out.println(id + " " + name);
        }
    }
}
