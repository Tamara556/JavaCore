package classworks.classwork13;

import java.io.File;
import java.io.IOException;

public class FileExample {

    public static final String FILE_PATH = "C:\\Users\\MaGa Mobile\\IdeaProjects\\JavaCore\\src\\classworks\\classwork13\\esim.txt";

    public static void main(String[] args) throws IOException {
        File  file = new File(FILE_PATH);
        if (!file.exists()){
            System.out.println(file.createNewFile());
        }
        System.out.println("is file ? = " + file.isFile());
        System.out.println("is directory ? = " + file.isDirectory());
        System.out.println(file.length());
    }
}
