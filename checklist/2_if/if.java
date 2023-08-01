import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (0<1) {
            System.out.println("0 < 1");
            if (2.0<3.0) {
                System.out.println("2 < 3");
            }
        } else {
            System.out.println("0 > 1");
            if (7!=9) {
                System.out.println("7 != 9");
            } else {
                System.out.println("7 == 9");
            }
        }
        sc.close();
    }
}

