#include <stdio.h>
#include <string.h>

int main() {
    char a[100];
    int b;
    double c;
    strcpy(a, "TEXTO");
    b = 0;
    c = 0.5;
    printf("%s\n", a);
    printf("%d\n", b);
    printf("%lf\n", c);
}

