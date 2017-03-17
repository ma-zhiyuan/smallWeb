package me.ifma.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CheckUtil {
	private static final String token = "MaTalk";
	
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		String[] arr = new String[]{token,timestamp,nonce};
		Arrays.sort(arr);
		StringBuffer content = new StringBuffer();
		for (String string : arr) {
			content.append(string);
		}
		String temp = getSha1(content.toString());
		return temp.equals(signature);
	} 
	
	/**
	 * sha1
	 * @param str
	 * @return
	 */
	public static String getSha1(String str) {
		if(str == null || str.length() == 0){
			return null;
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("sha1");
			byte[] digest = md.digest(str.getBytes());
			char[] c = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			StringBuffer buffer = new StringBuffer();
			for (byte b : digest) {
				buffer.append(c[(b >> 4) & 15]);
				buffer.append(c[b & 15]);
			}
			String string = buffer.toString();
			return string;
		} catch (NoSuchAlgorithmException e) {
			System.out.println("获取sha1出错");
		}
		return null;
	}
}
