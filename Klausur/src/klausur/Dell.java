package klausur;

public class Dell extends Computer{
    private String idString;

    public Dell(int ram, typ type, double taktFrequenz ,String idString) {
        super(ram, type, taktFrequenz);
        this.idString = idString;
    }
    
    @Override
    public String toString() {
        return super.toString() + idString;
    }
}
