package com.ath.keygen;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class KeyCore {

	// Same value across the application, this is the salt of the application
	protected static byte[] bytes;
		
    protected static String intToHex(final Number n, final int chars) {
        return String.format("%0" + chars + "x", n);
    }
	
    public enum Status {
        KEY_GOOD, KEY_INVALID, KEY_BLACKLISTED, KEY_PHONY
    }
    
    public byte[] getBytes(){
    	return bytes;
    }
    
    public void setBytes(byte[] b){
    	bytes = b;
    }
	
    protected short GetKeyShort(final int Seed, byte a, byte b, byte c){
		short result = 0x0000;
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(a); 
			md.update(b);
			md.update(c);
			md.update(BigInteger.valueOf(Seed).toByteArray());
			byte[] digest = md.digest();
			result =  ByteBuffer.wrap(digest).order(ByteOrder.LITTLE_ENDIAN).getShort();
		} catch (NoSuchAlgorithmException e) {System.err.println(e.getMessage());}
		return result;
	}
	
    public String GetChecksum(final String s){
		int left = 0x0056;
		int right = 0x00AF;
		for (byte i : s.getBytes()) {
			right += i;
			if ( right > 0x00FF ){
				right -= 0x00FF;
			}
			left += right;
			if ( left > 0x00FF ){
				left -= 0x00FF;
			}
		}
		int sum = (left << 8) + right;
		return intToHex(sum, 4);
	}
	
}
