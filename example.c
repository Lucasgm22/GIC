#include <stdio.h>
#include <string.h>

int main() {
    char a[100];
    char f[100];
    double e;
    int b;
    int c;
    int d;
    printf("%s\n" ,"Hello World!");
    printf("%s\n" ,"Digite o valor C");
    strcpy(a, "Ola mundo aqui e a IsiLanguage");
    e = 3.14;
    b = 100*(2+12)/14;
    d = (b+2)/2;
    strcpy(f, "Ola mundo aqui e a IsiLanguage");
    scanf("%d", &c);
    printf("%s\n" ,a);
    printf("%s\n" ,f);
    printf("%d\n", b);
    printf("%lf\n", e);
    printf("%d\n", d);
}

