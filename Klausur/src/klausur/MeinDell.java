package klausur;

public class MeinDell extends Dell implements Hauptspeicher{

    public MeinDell() {
        super(0, typ.Laptop, 0, null);
    }

    public MeinDell(int ram, double taktFrequenz, String idString) {
        super(ram, typ.Laptop, taktFrequenz, idString);
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    public static void main(String[] args) {
        Computer md= new MeinDell(5000, 1.7, "Mix");
        md.addRam(2000);
        md.toString();      //gibt nichts aus :(
        System.out.println(md);
    }
    
}
