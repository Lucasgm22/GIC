#include <stdio.h>
#include <string.h>

int main() {
    int b;
    int c;
    c = 0;
    b = 0;
    do {
        b = b+1;
        c = 0;
        do {
            printf("%d\n", c);
            c = c+2;
        } while (c<10);
        printf("%d\n", b);
    } while (b<5);
}

