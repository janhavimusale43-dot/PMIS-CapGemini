//Hierarchical inheritence

package Day06;

class Shape{
    String color = "Purple";
}

class Circle extends Shape{
    void drawCircle(){
        System.out.println("Drawing a "+color+" circle.");
    }
}

class Rectangle extends Shape{
    void drawRectangle(){
        System.out.println("Drawing a "+color+" rectangle.");
    }
}

public class Inheritence3 {
    public static void main(String[] args){
        Circle c = new Circle();
        Rectangle r = new Rectangle();

        c.drawCircle();
        r.drawRectangle();
    }
}
