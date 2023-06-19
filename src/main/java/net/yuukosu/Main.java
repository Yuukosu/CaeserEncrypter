package net.yuukosu;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;

import java.io.*;
import java.nio.file.AccessDeniedException;

public class Main {

    public static void main(String[] args) {
        ArgumentParser parser = ArgumentParsers.newFor("CaesarEncrypter").addHelp(false).build().description("シンプルな暗号化プログラム");
        parser.addArgument("-m", "--method").required(true).choices("ENCRYPT", "DECRYPT", "encrypt", "decrypt").help("ENCRYPT / DECRYPT");
        parser.addArgument("-o", "--output").help("出力先パス");
        parser.addArgument("file").type(String.class).required(true).help("暗号化するファイルパス");
        parser.addArgument("number").type(Integer.class).required(true).help("暗号化番号");
        Namespace ns;

        try {
            ns = parser.parseArgs(args);
        } catch (ArgumentParserException e) {
            parser.printHelp();
            return;
        }

        String method = ns.getString("method");
        String output = ns.getString("output");
        String file = ns.getString("file");
        int number = ns.getInt("number");

        StringBuilder builder = new StringBuilder();
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader reader = new BufferedReader(fileReader);

            while (reader.ready()) {
                String tmp = null;

                if (method.equalsIgnoreCase("encrypt")) {
                    tmp = CaesarLib.instance.encrypt(reader.readLine(), number);
                } else if (method.equalsIgnoreCase("decrypt")) {
                    tmp = CaesarLib.instance.decrypt(reader.readLine(), number);
                }

                if (tmp != null) {
                    builder.append(tmp);
                }
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("[-] ファイルが見つかりません。");
        } catch (AccessDeniedException e) {
            System.out.println("[-] ファイルへのアクセスが拒否されました。");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (output != null) {
            CaesarLib.instance.output(output, builder.toString());
            return;
        }

        System.out.println(builder);
    }
}
