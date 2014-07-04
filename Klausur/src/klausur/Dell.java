package klausur;

public class Dell extends Computer{
    private String idString;

    public Dell(String idString, int ram, typ type, double taktFrequenz) {
        super(ram, type, taktFrequenz);
        this.idString = idString;
    }
    
    public String toString() {
        return super.toString() + idString;
    }
}
