public class Passenger {

    static int id = 1; // Our Purpose Initialization
    String name;
    int age;
    String gender;
    String bp;
    String cname;
    int cage;
    int passengerID = id++;
    int number;     // 2
    String allotted; // L

    public Passenger (String name, int age, String gender, String bp, String cname, int cage) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.bp = bp;
        this.cname = cname;
        this.cage = cage;
        number = -1;
        allotted = "";
    }
}
