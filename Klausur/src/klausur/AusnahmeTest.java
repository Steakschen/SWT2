package klausur;

   
class Ausnahme1 extends Exception {
    public Ausnahme1() {super();}}
    
class Ausnahme11 extends Exception {
    public Ausnahme11() {super();}}  
    
class Ausnahme12 extends Exception {        
    public Ausnahme12() {super();}}   

public class AusnahmeTest {
    
    protected void s1(int selektor) {
        int ret=73;
        try { ret=s11(selektor); }
        //catch (Ausnahme11 a11) { System.out.println("s1(a11):"+a11); }
        //catch (Ausnahme12 a12) { System.out.println("s1(a12):"+a12); }
        catch (Exception e) {System.err.println("s1(e):"+e); }
        finally { System.out.println("Ende s1");}
        System.out.println("ret:"+ret);
    }

    protected int s11(int selektor) throws Ausnahme1{
        try { s111(selektor); }
        //catch (Ausnahme11 a11) { System.out.println("s1(a11):"+a11); }
        //catch (Ausnahme12 a12) { System.out.println("s1(a12):"+a12); }
        finally { System.out.println("Ende s11");}
        return 42;
    }
    
    protected void s111(int selektor) throws Ausnahme1{
        switch (selektor) {
            //case 0: throw  new Exception();
            case 1: throw  new Ausnahme1();
            //case 2: throw  new Ausnahme11();
            //case 3: throw  new Ausnahme12();
            case 4: throw  new ArrayIndexOutOfBoundsException();
            default: System.out.println("s111");
        }
    }
    public static void main(String[] args) {
        
        AusnahmeTest at = new AusnahmeTest();
        for (int i=0;i<5;i++) {
            System.out.println("--------->(i="+i+")");
            at.s1(i);
        }                
    }
}
    

