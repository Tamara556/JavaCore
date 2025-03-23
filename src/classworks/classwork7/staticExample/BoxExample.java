package classworks.classwork7.staticExample;

public class BoxExample {

    public static void main(String[] args) {
        Box myBox = new Box(2,3,4);
        System.out.println(myBox.volume());
        System.out.println(Box.madeIn);
    }

}
