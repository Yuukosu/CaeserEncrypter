package net.yuukosu;

import com.sun.jna.Library;
import com.sun.jna.Native;

import java.io.File;

public interface CaesarLib extends Library {

    CaesarLib instance = (CaesarLib) Native.loadLibrary(new File("libcaesar.dll").getAbsolutePath(), CaesarLib.class);

    String encrypt(String target, int c);
    String decrypt(String target, int c);
    void output(String fileName, String message);
    void encrypt_output(String target, int c, String fileName);
    void decrypt_output(String target, int c, String fileName);
}
