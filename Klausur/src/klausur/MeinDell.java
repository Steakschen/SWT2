package klausur;

public class MeinDell extends Dell implements Hauptspeicher{

    public MeinDell() {
        super(null, 0, null, 0);
    }

    public MeinDell(String idString, int ram, typ type, double taktFrequenz) {
        super(idString, ram, type, taktFrequenz);
    }

    @Override
    public void addRam(int ram) {
        super.ram += ram;
    }
    
}
