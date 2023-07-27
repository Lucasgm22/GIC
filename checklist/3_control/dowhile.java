import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int b;
        int c;
        c = 0;
        b = 0;
        do {
            b = b+1;
            c = 0;
            do {
                System.out.println(c);
                c = c+2;
            } while (c<10);
            System.out.println(b);
        } while (b<5);
        sc.close();
    }
}

