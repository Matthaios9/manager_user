package base.utils;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

public class SecurityUtils {

	public static String[] generateRSAKey(int keySize) {
		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(keySize);
			KeyPair keyPair = generator.generateKeyPair();
			PrivateKey privateKey = keyPair.getPrivate();
			PublicKey publicKey = keyPair.getPublic();

			return new String[] {Base64Utils.encodeToString(privateKey.getEncoded()), Base64Utils.encodeToString(publicKey.getEncoded())};
		} catch (Exception e) {

		}
		return null;
	}

	public static String AESEncrypt(String strSecretKey, String text, String salt) throws Exception {
		byte[] encodedKey = strSecretKey.getBytes();
		byte[] iv = salt.getBytes();
		SecretKey secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		Cipher aesCipherForEncryption = Cipher.getInstance("AES/CTR/NoPadding");
		aesCipherForEncryption.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(iv));
		byte[] byteDataToEncrypt = text.getBytes("UTF-8");
		byte[] byteCipherText = aesCipherForEncryption
				.doFinal(byteDataToEncrypt);

		return URLEncoder.encode(Base64.toBase64String(byteCipherText), "UTF-8");
	}

	public static String AESDecrypt(String strSecretKey, String encryptedText, String salt) throws Exception {
		byte[] textByte = Base64.decode(URLDecoder.decode(encryptedText, "UTF-8"));
		byte[] iv = salt.getBytes();
		byte[] encodedKey = strSecretKey.getBytes();
		SecretKey secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		Cipher aesCipherForDecryption = Cipher.getInstance("AES/CTR/NoPadding");

		aesCipherForDecryption.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

		byte[] byteCipherText = aesCipherForDecryption
				.doFinal(textByte);
		String strCipherText = new String(byteCipherText, "UTF-8");
		return strCipherText;
	}

	public static void main(String[] args) { generateRSAKey(1024);
	}
}
