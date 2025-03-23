package homeworks;

public class ArraySort {
    public static void main(String[] args) {
        int[] array = {5, 2, 6, 8, 3, 1, 55, 86, 4, 43,};

        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (array[j] < array[j - 1]) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
        }
        for (int x : array) {
            System.out.print(x + " ");
        }
    }
}
