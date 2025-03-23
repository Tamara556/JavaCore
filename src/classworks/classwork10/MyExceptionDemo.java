package classworks.classwork10;

public class MyExceptionDemo {

    public static void main(String[] args) {
        try {
            meth();
        } catch (MyException e){
            System.out.println(e.getMessage());
        }
    }

    public static void meth() throws MyException {
        throw new MyException("This is message");
    }

}
