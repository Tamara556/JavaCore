package classworks.classwork11;

public class MainThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new MyNewThread());
        thread.start();

        for (int i = 0; i < 5; i++) {
            System.out.println("Main Thread" + i);
            Thread.sleep(1000);
        }
        System.out.println("Main Thread finished");


    }
}
