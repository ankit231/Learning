package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Avaii {
    public static void main(String a[]) {
        FileOutputStream fos = null;
        try {
            File file = new File("/home/ankit/software/testout.txt");
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);
        System.out.println("This goes to out.txt");
        System.out.println("This is again goes to out.txt");

    }
}
