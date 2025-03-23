package homeworks.dynamicArray;

public class DynamicArray {

    private int[] array;

    DynamicArray(){
        array = new int[10];
    }

    DynamicArray(int lenght){
        array = new int[lenght];
    }


    private int size = 0;

    public void add(int value) {
        if (array.length == size) {
            extend();
        }
        array[size++] = value;
    }

    public void extend() {
        int[] newArray = new int[array.length + 10];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    private int getByIndex(int index) {
        if (index < 0 || index > size){
            return -1;
        }
        return array[index];
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
    }


}
