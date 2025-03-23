package classworks.classwork6;

public class BoxTest {

    public static void main(String[] args) {
        Box myBox = new Box(232,2,2);
        myBox.setDim(4,4,4);
        System.out.println(myBox.volume());
    }

}
