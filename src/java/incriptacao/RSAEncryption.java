/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package incriptacao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

/**
 *
 * @author emanuellima
 */
public class RSAEncryption {

    public static byte[] encrypt(Object object, PublicKey publicKey) throws Exception {
        // Verifica se a chave pública foi definida
        if (publicKey == null) {
            throw new IllegalArgumentException("Chave pública não definida. Use generateKeys() para gerar um par de chaves.");
        }

        // Serializa o objeto em bytes
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(object);
        byte[] plaintextBytes = bos.toByteArray();

        // Criptografa os dados usando a chave pública
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] ciphertextBytes = cipher.doFinal(plaintextBytes);

        return ciphertextBytes;
    }

    public static Object decrypt(byte[] ciphertext, PrivateKey privateKey) throws Exception {
        // Verifica se a chave privada foi definida
        if (privateKey == null) {
            throw new IllegalArgumentException("Chave privada não definida. Use generateKeys() para gerar um par de chaves.");
        }

        // Descriptografa os dados usando a chave privada
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] plaintextBytes = cipher.doFinal(ciphertext);

        // Desserializa os bytes em objeto
        ByteArrayInputStream bis = new ByteArrayInputStream(plaintextBytes);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object objectRead = ois.readObject();

        return objectRead;

    }

}
