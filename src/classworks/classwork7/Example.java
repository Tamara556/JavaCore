package classworks.classwork7;

import classworks.classwork6.Box;

public class Example {
    public static void main(String[] args) {
       // int a = 10;
       // System.out.println(a);
       // changeA(a);
       // System.out.println(a);
        Box myBox = new Box(10,2,3);
        System.out.println(myBox.width);
        changeBox(myBox);
        System.out.println(myBox.width);
    }

    public static void changeBox(Box box){
        box.width = 90;
    }

    public static void changeA(int a){
        a = a * 2;
    }

}
