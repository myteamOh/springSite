package com.spring.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class OpenCrypt {
	/*
	 * 참고 : 해시함수는 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑하는 함수이다. 해시 함수의의해 얻어지는 값은 해시 값, 해시코드,
	 * 해시 체크섬 또는 간단하게 해시라고 한다. 해시함수중에는 암호학적 해쉬함수 와 비암호화적 해쉬함수로 구분되곤 한다. 암호학적 해쉬함수의
	 * 종류로는 MD5,SHA계열 해쉬함수가 있으며 비암호학적 해쉬함수로는 CRC32등이 있다
	 */
	public static byte[] getSHA256(String source, String salt) {
		byte byteData[] = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(source.getBytes());
			md.update(salt.getBytes());
			byteData = md.digest();
		} catch (NoSuchAlgorithmException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return byteData;
	}

}
