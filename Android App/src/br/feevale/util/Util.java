package br.feevale.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
<<<<<<< HEAD
=======
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
>>>>>>> Web Services Sync Tasks

public class Util {

	public static String md5(String str) throws NoSuchAlgorithmException{

		MessageDigest md = null;

		try {

			md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new NoSuchAlgorithmException(e);
		}

		return hash(md);
	}

	private static String hash(MessageDigest md) {

		byte[] hash = md.digest();

		StringBuilder hexString = new StringBuilder(hash.length);

		for (int i = 0; i < hash.length; i++) {
			if ((0xff & hash[i]) < 0x10) {
				hexString.append("0");
			}
			hexString.append(Integer.toHexString(0xFF & hash[i]));
		}

		return hexString.toString();
	}
}
