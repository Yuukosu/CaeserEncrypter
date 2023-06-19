#include "caesar.h"

char* encrypt(const char* target, int c) {
    unsigned long long length = strlen(target);
    char* buffer = (char*) malloc(length + 1);

    for (int i = 0; i < length; i++) {
        buffer[i] = target[i] + c;
    }

    buffer[length] = '\0';

    return buffer;
}

char* decrypt(const char* target, int c) {
    unsigned long long length = strlen(target);
    char* buffer = (char*) malloc(length + 1);

    for (int i = 0; i < length; i++) {
        buffer[i] = target[i] - c;
    }

    buffer[length] = '\0';

    return buffer;
}

void output(const char* fileName, const char* message) {
    FILE* file;
    fopen_s(&file, fileName, "w");
    fprintf_s(file, message);
    fclose(file);
}

void encrypt_output(const char* target, int c, const char* fileName) {
    output(fileName, encrypt(target, c));
}

void decrypt_output(const char* target, int c, const char* fileName) {
    output(fileName, decrypt(target, c));
}
