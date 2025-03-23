package classworks.classwork10;

public class ExceptionExample {
    public static void main(String[] args) {
        int d = 0;
        try{
            int a = 42 / d;
            System.out.println(a);
        } catch (ArithmeticException e){
            System.out.println("wrong num");
        }
        System.out.println("Finished");
    }
}
