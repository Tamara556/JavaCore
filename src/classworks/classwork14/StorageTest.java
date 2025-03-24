package classworks.classwork14;

public class StorageTest {
    public static void main(String[] args) {
        Storage<String> names = new Storage<>();
        names.add("Poxos");
        names.add("Petros");
        names.add("Martiros");

        System.out.println(names.getByIndex(1));
        names.print();

        Storage<Integer> numbers = new Storage<>();
        numbers.add(7);
        numbers.add(9);
        numbers.print();
    }
}
