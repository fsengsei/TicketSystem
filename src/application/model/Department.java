package application.model;

public class Department {
    public String abteilungsNummer;
    public String abteilungsName;

    @Override
    public String toString() {
        return abteilungsNummer + " - " + abteilungsName;
    }
}
