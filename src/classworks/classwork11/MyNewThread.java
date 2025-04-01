package classworks.classwork11;

public class MyNewThread extends Thread {


    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("My New Thread" + i);
                Thread.sleep(500);
            }
            System.out.println("MyNewThread is finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
