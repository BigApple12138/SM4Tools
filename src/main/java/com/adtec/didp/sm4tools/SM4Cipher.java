package com.adtec.didp.sm4tools;

import org.bouncycastle.crypto.engines.SM4Engine;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

import java.io.*;

public class SM4Cipher {

    public static void encrypt(String key, String iv, File inputFile, File outputFile) throws Exception {
        byte[] keyBytes = key.getBytes();
        byte[] ivBytes = iv.getBytes();
        // 确保keyBytes和ivBytes的长度是16字节
        if (keyBytes.length != 16 || ivBytes.length != 16) {
            throw new IllegalArgumentException("Key and IV must be 16 bytes long");
        }
        CBCBlockCipher cbcBlockCipher = new CBCBlockCipher(new SM4Engine());
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(cbcBlockCipher);
        cipher.init(true, new ParametersWithIV(new KeyParameter(keyBytes), ivBytes));

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {
            processFile(cipher, fis, fos);
        }
    }

    public static void decrypt(String key, String iv, File inputFile, File outputFile) throws Exception {
        byte[] keyBytes = key.getBytes();
        byte[] ivBytes = iv.getBytes();
        CBCBlockCipher cbcBlockCipher = new CBCBlockCipher(new SM4Engine());
        PaddedBufferedBlockCipher cipher = new PaddedBufferedBlockCipher(cbcBlockCipher);
        cipher.init(false, new ParametersWithIV(new KeyParameter(keyBytes), ivBytes));

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {
            processFile(cipher, fis, fos);
        }
    }

    private static void processFile(PaddedBufferedBlockCipher cipher,
                                    InputStream in,
                                    OutputStream out) throws Exception {
        byte[] inBuffer = new byte[cipher.getBlockSize()];
        byte[] outBuffer = new byte[cipher.getOutputSize(inBuffer.length)];
        int bytesRead;
        while ((bytesRead = in.read(inBuffer, 0, inBuffer.length)) != -1) {
            int length = cipher.processBytes(inBuffer, 0, bytesRead, outBuffer, 0);
            if (length > 0) {
                out.write(outBuffer, 0, length);
            }
        }
        int length = cipher.doFinal(outBuffer, 0);
        if (length > 0) {
            out.write(outBuffer, 0, length);
        }
    }
}
