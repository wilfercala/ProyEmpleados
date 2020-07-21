import java.util.Scanner;

public class Util {

    public static Scanner getScanner() {
        Scanner scanner = new Scanner(System.in);
        if (scanner == null) {
            System.out.println("Unable to fetch scanner");
            throw new IllegalStateException();
        }
        return scanner;
    }
}