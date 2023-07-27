import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a;
        int b;
        double c;
        a = "TEXTO";
        b = 0;
        c = 0.1;
        System.out.println("ESCRITA");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println("LEITURA");
        a = sc.nextLine();
        b = sc.nextInt();
        c = sc.nextDouble();
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        sc.close();
    }
}

