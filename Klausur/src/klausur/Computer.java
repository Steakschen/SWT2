package klausur;

public class Computer {
    
    public enum typ {
        Server, Workstation, Laptop
    }
    
    public int ram;
    private typ type;
    private double taktFrequenz;
    
    public Computer() {
        }
    
    public Computer(int ram, typ type, double taktFrequenz) {
        this.ram=ram;
        this.type=type;
        this.taktFrequenz=taktFrequenz;
        }
    
    public String toString() {
        return type + " " + ram + " gb Ram " + taktFrequenz + " Mhz ";
    }
    
    public static void main(String[] args) {
        Computer meinComputer = new Computer(2, typ.Server, 2500);
        System.out.println(meinComputer);
    }
    
}
