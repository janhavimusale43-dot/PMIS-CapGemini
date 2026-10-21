package Day11;

@FunctionalInterface 
interface Greeter{
    void greet(String name);
}
public class lambda1 {
    public static void main(String[] args){
        Greeter g = name -> System.out.println("Hello, " + name);
        g.greet("Janhavi");
    }
}
