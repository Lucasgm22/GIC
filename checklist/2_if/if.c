#include <stdio.h>
#include <string.h>

int main() {
    if (0<1) {
        printf("%s\n", "0 < 1");
        if (2<3) {
            printf("%s\n", "2 < 3");
        }
    } else {
        printf("%s\n", "0 > 1");
        if (7!=9) {
            printf("%s\n", "7 != 9");
        } else {
            printf("%s\n", "7 == 9");
        }
    }
}

