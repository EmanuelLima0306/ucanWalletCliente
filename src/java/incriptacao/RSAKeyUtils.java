/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package incriptacao;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 *
 * @author emanuellima
 */
public class RSAKeyUtils {
    public static String publicKeyToBase64(byte[] publicKeyBytes) {
        return Base64.getEncoder().encodeToString(publicKeyBytes);
    }

    public static String privateKeyToBase64(byte[] privateKeyBytes) {
        return Base64.getEncoder().encodeToString(privateKeyBytes);
    }

    public static byte[] publicKeyFromBase64(String publicKeyBase64) {
        return Base64.getDecoder().decode(publicKeyBase64);
    }

    public static byte[] privateKeyFromBase64(String privateKeyBase64) {
        return Base64.getDecoder().decode(privateKeyBase64);
    }
    
    public static PublicKey publicKeyFromBytes(byte[] publicKeyBytes) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        return keyFactory.generatePublic(keySpec);
    }

    public static PrivateKey privateKeyFromBytes(byte[] privateKeyBytes) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        return keyFactory.generatePrivate(keySpec);
    }
}
