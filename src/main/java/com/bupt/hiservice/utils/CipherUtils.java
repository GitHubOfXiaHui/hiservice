package com.bupt.hiservice.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class CipherUtils implements CipherConstants {

	private Key mainPrivateKey;

	private Properties tableSecretKeys;

	private Cipher mainCipher;
	private Cipher tableCipher;

	public CipherUtils() throws IOException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException {
		try (ObjectInputStream ois = new ObjectInputStream(new ClassPathResource(MAIN_PRIVATE_KEY).getInputStream())) {
			this.mainPrivateKey = (Key) ois.readObject();
		}
		this.tableSecretKeys = new Properties();
		try (InputStream is = new ClassPathResource(TABLE_SECRET_KEYS).getInputStream()) {
			this.tableSecretKeys.load(is);
		}
		this.mainCipher = Cipher.getInstance(MAIN_ALGORITH_NAME);
		this.tableCipher = Cipher.getInstance(TABLE_ALGORITH_NAME);
	}

	public String encryptPostId(Long postId, long salt)
			throws InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
		return encrypt(String.valueOf(postId ^ salt), ENCRYPTED_POST_KEYWORD_KEY);
	}

	public Long decryptPostId(String str, long salt)
			throws InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
		return Long.parseLong(decrypt(str, ENCRYPTED_POST_KEYWORD_KEY)) ^ salt;
	}

	private String encrypt(String str, String key)
			throws InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
		Key tableKey = getTableKey(key);
		tableCipher.init(Cipher.ENCRYPT_MODE, tableKey);
		return Base64.encodeBase64String(tableCipher.doFinal(str.getBytes()));
	}

	private String decrypt(String str, String key)
			throws InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException {
		Key tableKey = getTableKey(key);
		tableCipher.init(Cipher.DECRYPT_MODE, tableKey);
		return new String(tableCipher.doFinal(Base64.decodeBase64(str)));
	}

	/**
	 * 解密表密钥
	 * 
	 * @param key
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	private Key getTableKey(String key) throws InvalidKeyException, NoSuchAlgorithmException {
		mainCipher.init(Cipher.UNWRAP_MODE, mainPrivateKey);
		return mainCipher.unwrap(Base64.decodeBase64(tableSecretKeys.getProperty(key)), TABLE_ALGORITH_NAME,
				Cipher.SECRET_KEY);
	}

}
