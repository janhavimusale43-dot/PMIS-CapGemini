package Day06;

class Animal{
    void eat(){
        System.out.println("Animal is eating: ");
    }
}

class Dog extends Animal{
    void est(){
        System.out.println("Dog is eating: ");
        super.eat();
    }
}
