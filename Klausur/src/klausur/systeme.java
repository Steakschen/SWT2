package klausur;


import java.util.EnumSet;

public class systeme {

    public enum BS {
        MSDOS, Win311, Win95, Win98, WinNT, WinXP, winVista, Win7,
        SLS, DLD, OpenSuSE, Ubuntu, Debian,
        AppleDOS, LisaOS, MacOS, MacOS9, MacOSX
    }

    public void start() {

        EnumSet<BS> Windows = EnumSet.range(BS.MSDOS, BS.Win7);

        EnumSet<BS> Linux = EnumSet.range(BS.SLS, BS.Debian);

        EnumSet<BS> Apple = EnumSet.range(BS.AppleDOS, BS.MacOSX);

        for (BS w : BS.values()) {
            if (Windows.contains(w)) {
                System.out.println("MS-Windows OS: "+ w);
            } else if (Linux.contains(w)) {
                System.out.println("Linux OS: " + w);
            } else if (Apple.contains(w)) {
                System.out.println("Apple OS: " + w);
            }
        }
    }

    public static void main(String[] args) {
        systeme os = new systeme();
        os.start();
    }
}
