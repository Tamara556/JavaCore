package classworks.classwork11;

public class CurrentThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());
        thread.setName("Esim");
        System.out.println(thread.getPriority());
        System.out.println(thread.getName());

        for (int i = 0; i < 10; i++) {
            Thread.sleep(500);
            System.out.println(i);
        }
    }
}
