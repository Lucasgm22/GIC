#include <stdio.h>
#include <string.h>

int main() {
    char a[100];
    int b;
    double c;
    strcpy(a, "TEXTO");
    b = 0;
    c = 0.1;
    printf("%s\n", "ESCRITA");
    printf("%s\n", a);
    printf("%d\n", b);
    printf("%lf\n", c);
    printf("%s\n", "LEITURA");
    scanf("%s", &a);
    scanf("%d", &b);
    scanf("%lf", &c);
    printf("%s\n", a);
    printf("%d\n", b);
    printf("%lf\n", c);
}

