package application.model;

public class Priority {

    public String prioritaetsNummer;
    public String prioritaetsText;

    @Override
    public String toString() {
        return prioritaetsNummer + " - " + prioritaetsText;
    }

//    public String newCSVList() {
//        return "\n" + prioritaetsNummer + ";" + prioritaetsText;
//    }
}
