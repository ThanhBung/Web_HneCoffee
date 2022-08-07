package model;

public class Customer {
    private int id;
    private String name;
    private String phone;
    private String pass;
    private String address;

    public Customer() {
    }

    public Customer(String phone, String pass) {
        this.phone = phone;
        this.pass = pass;
    }

    public Customer(int id, String name, String phone, String pass, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.pass = pass;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
    
    
}
