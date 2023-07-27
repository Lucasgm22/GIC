import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a;
        String f;
        String g;
        double e;
        int b;
        int c;
        int d;
        System.out.println("Hello World!");
        System.out.println("Digite o valor C");
        c = 0;
        if (0<1) {
            c = 0;
        } else {
            System.out.println(c);
        }
        a = "Ola mundo aqui e a IsiLanguage";
        e = 2.1;
        c = 0;
        b = 100*(2+12)/14;
        d = (b+2)/2;
        f = "Ola mundo aqui e a IsiLanguage";
        System.out.println("SE");
        if (0<1) {
            c = 0;
            System.out.println("0 < 1");
            if (2<3) {
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
        b = 2;
        System.out.println("ENQUANTO");
        while (b<5) {
            c = 0;
            b = b+1;
            while (b<10) {
                b = b+2;
            }
            System.out.println(b);
        }
        System.out.println("EXECUTE");
        do {
            do {
                c = c+2;
                System.out.println(c);
            } while (c<10);
            System.out.println(b);
            b = b+1;
        } while (b<10);
        System.out.println(a);
        System.out.println(f);
        System.out.println(b);
        System.out.println(d);
    }
}

