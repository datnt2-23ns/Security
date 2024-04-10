import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;
import java.util.Scanner;

public class RSACipherExample {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(2048);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		System.out.print("Nhập đoạn văn bản muốn mã hóa: ");
		String originalText = scanner.nextLine();
		byte[] encryptedBytes = encrypt(originalText, publicKey);
		String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
		System.out.println("Văn bản đã mã hóa: " + encryptedText);
		byte[] decryptedBytes = decrypt(encryptedBytes, privateKey);
		String decryptedText = new String(decryptedBytes);
		System.out.println("Văn bản đã giải mã: " + decryptedText);
		scanner.close();
	}

	public static byte[] encrypt(String text, PublicKey key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(text.getBytes());
	}

	public static byte[] decrypt(byte[] encryptedBytes, PrivateKey key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, key);
		return cipher.doFinal(encryptedBytes);
	}
}
