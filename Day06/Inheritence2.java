//multi-level inheritence

package Day06;

//parent class
class Device{
    void poweron(){
        System.out.println("Device powered on.");
    }
}

//child class 
class dabbaPhone extends Device{
    void makeCall(){
        System.out.println("Calling the number.....");
    }
}

class SmartPhone extends dabbaPhone{
    void browseInternet(){
        System.out.println("Opening Browser...");
    }
}

public class Inheritence2 {
    public static void main(String[] args){
        SmartPhone samsung = new SmartPhone();
        samsung.browseInternet();
        samsung.makeCall();
        samsung.poweron();
    }
}
