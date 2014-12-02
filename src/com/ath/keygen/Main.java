package com.ath.keygen;

public class Main {

	public static void main(String[] args) {
		int seed = 20000000;
		KeyGenerate kg = new KeyGenerate();
		KeyValidate kv = new KeyValidate();
		kg.nextTwelveBytes();
		String k = kg.MakeKey(seed);
		System.out.println(k);
	}

}
