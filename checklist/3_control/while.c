#include <stdio.h>
#include <string.h>

int main() {
    int b;
    int c;
    c = 0;
    b = 0;
    while (b<5) {
        b = b+1;
        c = 0;
        while (c<10) {
            printf("%d\n", c);
            c = c+2;
        }
        printf("%d\n", b);
    }
}

