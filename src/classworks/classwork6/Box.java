package classworks.classwork6;

public class Box {

    public double width;
    public double height;
    public double depth;

    public Box(double width, double height, double depth){
        this.width = width;
        this.depth = depth;
        this.height = height;
    }

     void setDim(double w, double h, double d) {
        width = w;
        height = h;
        depth = d;
    }


    double volume() {
        return width * height * depth;
    }
}
