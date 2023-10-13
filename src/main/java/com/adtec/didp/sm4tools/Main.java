package com.adtec.didp.sm4tools;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Usage: java Main <encrypt|decrypt> <key> <iv> <inputFile> <outputFile>");
            return;
        }

        String mode = args[0];
        String key = args[1];
        String iv = args[2];
        File inputFile = new File(args[3]);
        File outputFile = new File(args[4]);

        long startTime = System.currentTimeMillis();

        try {
            if ("encrypt".equalsIgnoreCase(mode)) {
                SM4Cipher.encrypt(key, iv, inputFile, outputFile);
                System.out.println("File encrypted successfully");
            } else if ("decrypt".equalsIgnoreCase(mode)) {
                SM4Cipher.decrypt(key, iv, inputFile, outputFile);
                System.out.println("File decrypted successfully");
            } else {
                System.out.println("Invalid mode. Use 'encrypt' or 'decrypt'.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error processing file");
        }

        long endTime = System.currentTimeMillis();
        double elapsedTimeInSeconds = (endTime - startTime) / 1000.0;
        System.out.println("Total execution time: " + elapsedTimeInSeconds + " seconds");
    }
}
