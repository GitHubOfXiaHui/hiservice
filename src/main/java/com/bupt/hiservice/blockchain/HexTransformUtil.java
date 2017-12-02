package com.bupt.hiservice.blockchain;

import java.io.ByteArrayOutputStream;

public class HexTransformUtil {

	private static String hexString = "0123456789abcdef";

	public static String encode(String str) {
		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
		}
		return "0x" + sb.toString();
	}

	public static String decode(String bytes) {
		if(bytes == null) return null;
		bytes = bytes.substring(2);
		ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length() / 2);
		// 将每2位16进制整数组装成一个字节
		for (int i = 0; i < bytes.length(); i += 2)
			baos.write((hexString.indexOf(bytes.charAt(i)) << 4 | hexString.indexOf(bytes.charAt(i + 1))));
		return new String(baos.toByteArray());
	}

	public static void main(String[] args) {

		String s = "中期包过";

		System.out.println(encode(s));
		
		System.out.println(decode(encode(s)));
	}
}
