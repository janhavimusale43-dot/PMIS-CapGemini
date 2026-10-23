package banking;
public class Customer {
    private int customerID; private String name, address, contact;
    public Customer(int customerID, String name, String address, String contact) {
        this.customerID=customerID; this.name=name; this.address=address; this.contact=contact;
    }
    public int getCustomerID(){return customerID;} public void setCustomerID(int v){customerID=v;}
    public String getName(){return name;} public void setName(String v){name=v;}
    public String getAddress(){return address;} public void setAddress(String v){address=v;}
    public String getContact(){return contact;} public void setContact(String v){contact=v;}
    public String toString(){return "Customer ID: "+customerID+", Name: "+name+", Address: "+address+", Contact: "+contact;}
}
