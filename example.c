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
    b = 100*(2+12)/14;
    d = (b+2)/2;
    b = 2;
    strcpy(f, "Ola mundo aqui e a IsiLanguage");
    scanf("%d", &c);
    if (b<1) {
        printf("%s\n" ,"0 < 1");
        if (2<3) {
            printf("%s\n" ,"2 < 3");
        }
    } else {
        printf("%s\n" ,"0 > 1");
        if (7>9) {
            printf("%s\n" ,"7 > 9");
        } else {
            printf("%s\n" ,"7 < 9");
         }
     }
    printf("%s\n" ,a);
    printf("%s\n" ,f);
    printf("%d\n", b);
    printf("%d\n", d);
}

