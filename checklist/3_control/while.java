import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int b;
        int c;
        c = 0;
        b = 0;
        while (b<5) {
            b = b+1;
            c = 0;
            while (c<10) {
                System.out.println(c);
                c = c+2;
            }
            System.out.println(b);
        }
        sc.close();
    }
}

