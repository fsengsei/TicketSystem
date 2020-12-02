package application.model;

public class User {
    public String usernumber;
    public String titel;
    public String name;
    public String adress;
    public String zip;
    public String city;
    public String abtnumber;

    public String toString() { return usernumber + "-" + name + "-" + abtnumber; }

}
