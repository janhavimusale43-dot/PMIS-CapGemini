package Day07;

class Address {
    String city;
    String state;
    int pincode;

    Address(String city, String state, int pincode) {
        this.city = city;
        this.state = state;
        this.pincode = pincode;
    }

    void displayAddress() {
        System.out.println(city + ", " + state + ", " + pincode);
    }
}

class Student {
    String name;
    int rollNumber;
    Address address;

    Student(String name, int rollNumber, Address address) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.address = address;
    }

    void displayProfile() {
        System.out.println("Student: " + name);
        System.out.println("Roll No.: " + rollNumber);
        System.out.println("Lives at:");
        address.displayAddress();
    }
}