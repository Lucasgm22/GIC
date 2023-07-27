#include <stdio.h>
#include <string.h>

int main() {
    char a[100];
    char f[100];
    char g[100];
    double e;
    int b;
    int c;
    int d;
    printf("%s\n" ,"Hello World!");
    printf("%s\n" ,"Digite o valor C");
    strcpy(a, "Ola mundo aqui e a IsiLanguage");
    e = 2.1;
    c = 0;
    b = 100*(2+12)/14;
    d = (b+2)/2;
    strcpy(f, "Ola mundo aqui e a IsiLanguage");
    printf("%s\n" ,"SE");
    if (0<1) {
        c = 0;
        printf("%s\n" ,"0 < 1");
        if (2<3) {
            printf("%s\n" ,"2 < 3");
        }
    } else {
        printf("%s\n" ,"0 > 1");
        if (7!=9) {
            printf("%s\n" ,"7 != 9");
        } else {
            printf("%s\n" ,"7 == 9");
        }
    }
    b = 2;
    printf("%s\n" ,"ENQUANTO");
    while (b<5) {
        c = 0;
        b = b+1;
        while (b<10) {
            b = b+2;
        }
        printf("%d\n", b);
    }
    printf("%s\n" ,"EXECUTE");
    do {
        do {
            c = c+2;
            printf("%d\n", c);
        } while (c<10);
        printf("%d\n", b);
        b = b+1;
    } while (b<10);
    printf("%s\n" ,a);
    printf("%s\n" ,f);
    printf("%d\n", b);
    printf("%d\n", d);
}

