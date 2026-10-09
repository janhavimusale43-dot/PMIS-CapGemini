package Day06;

interface Mother{
    void message();
}

interface Father{
    void message();
}

class Child implements Mother, Father{
    @Override 
    public void message(){
        System.out.println("Loving both mom & dad. ");
    }
}

public class Inheritence4 {
    public static void main(String[] args){
        Child c = new Child();
        c.message();

        Mother m = new Child();
        m.message();

        Father f = new Child();
        f.message();
    }
    
}
