import java.util.Scanner;

public class Utility {
    public static String scanString(){
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        return name;
    }
    public static int scanInteger(){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        return number;
    }
}