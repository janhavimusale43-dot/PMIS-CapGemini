package Oops;
class Car{
    String color;
    String brand;
    int speed;

    Car(String color, String brand, int speed){
        this.color = color;
        this.brand = brand;
        this.speed = speed;
    }

    void displayInfo(){
        System.out.println(color+"\n"+brand+"\n"+speed);
    }

    void accelerate(int incr){
        int or_speed = speed;
        speed += incr;
            System.out.println("Original speed:"+ or_speed);
            System.out.println(brand + "accelreated by " +speed +" km/hr");
    }
}
public class Code1 {
    public static void main(String[] args){
        Car c1 = new Car( "Purple" , "BMW" , 360);
        c1.displayInfo();
        c1.accelerate( 50);
    }
}
