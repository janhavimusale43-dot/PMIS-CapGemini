package Oops;

class calculator {
    int add (int a , int b){
        return a + b;
    }

    int add (int a , int b , int c) {
        return a + b + c;
    }

    double add (double a , double b) {
        return a + b;
    }
}

class Animal{
    void MakeSound() {
        System.out.println("Aminal makes a sound");
    }
}

class Dog extends Animal{
    @Override
    void MakeSound(){
        System.out.println("Dog barks : Woof Woof!");
    }
}

class Cat extends Animal{
    @Override void MakeSound(){
        System.out.println("Cat meows : Meow Meow!");
    }
}

public class Polymorphism {
    public static void main(String[] args) {
        Animal pet1 = new Dog();
        Animal pet2 = new Cat();
        Animal pet3 = new Animal();

        pet1.MakeSound();
        pet2.MakeSound();
        pet3.MakeSound();
    }
    
}
