package kg.kstu.lb.component;

import kg.kstu.lb.exception.BaseException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CipherComponent {

    @Value("${encryption.secret-key}")
    String secretKey;

    @Value("${encryption.algorithm}")
    String encryptionAlgorithm;

    public String encrypt(String inputString) {
        byte[] validSecretKeyBytes = new byte[32];
        System.arraycopy(this.secretKey.getBytes(), 0, validSecretKeyBytes, 0, Math.min(this.secretKey.getBytes().length, validSecretKeyBytes.length));
        SecretKey secretKeySpec = new SecretKeySpec(validSecretKeyBytes, this.encryptionAlgorithm);
        KeyGenerator keyGenerator;
        Cipher cipher;
        byte[] encryptedObject;

        try {

            keyGenerator = KeyGenerator.getInstance(this.encryptionAlgorithm);
            cipher = Cipher.getInstance(this.encryptionAlgorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            keyGenerator.init(256);
            byte[] objectBytes = inputString.getBytes();
            encryptedObject = cipher.doFinal(objectBytes);

        } catch (Exception exception) {
            throw new BaseException(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return Base64.getUrlEncoder().encodeToString(encryptedObject);
    }

    public String decrypt(String base64EncryptedText) {
        byte[] validSecretKeyBytes = new byte[32];
        System.arraycopy(this.secretKey.getBytes(), 0, validSecretKeyBytes, 0, Math.min(this.secretKey.getBytes().length, validSecretKeyBytes.length));

        SecretKey secretKeySpec = new SecretKeySpec(validSecretKeyBytes, this.encryptionAlgorithm);
        Cipher cipher;
        byte[] decryptedObjectBytes;

        try {

            cipher = Cipher.getInstance(this.encryptionAlgorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] encryptedObject = Base64.getUrlDecoder().decode(base64EncryptedText);
            decryptedObjectBytes = cipher.doFinal(encryptedObject);

        } catch (Exception exception) {
            throw new BaseException(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new String(decryptedObjectBytes, StandardCharsets.UTF_8);
    }
}
