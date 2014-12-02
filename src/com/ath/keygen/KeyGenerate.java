package com.ath.keygen;

import java.security.SecureRandom;
import java.util.HashMap;

public class KeyGenerate extends KeyCore {

	private HashMap<String, byte[]> lastBytes = new HashMap<String, byte[]>();
	
    private void nextBytes(int total){
    	bytes = new byte[total];
    	new SecureRandom().nextBytes(bytes);
    }
    
    // Increase the version of the serial
    public void nextTwelveBytes(){
    	nextBytes(12);
    	lastBytes.put(lastBytes.size()+1+"", bytes);
    }
    
    public byte[] findBytesOnVersion(String key){
    	return lastBytes.get(key);
    }
    
	public String MakeKey(final int Seed){
		short[] keyBytes = new short[4];
		int i;
		try {
			keyBytes[0] = GetKeyShort(Seed, bytes[0], bytes[1], bytes[2]);
			keyBytes[1] = GetKeyShort(Seed, bytes[3], bytes[4], bytes[5]);
			keyBytes[2] = GetKeyShort(Seed, bytes[6], bytes[7], bytes[8]);
			keyBytes[3] = GetKeyShort(Seed, bytes[9], bytes[10], bytes[11]);
		} catch(NullPointerException e){
			System.err.println("Bytes not set!\n");
		}
		
		String result = intToHex(Seed, 8);
		
		for(i = 0 ; i<=3 ; i++){
			result += intToHex(keyBytes[i], 4);
		}
		
		result += GetChecksum(result);
		
		i = result.length() - 2;
		while (i > 1) {
			result = new StringBuilder(result).insert(i, '-').toString();
			i -= 4;
		}
		return result; 
	}	
	



	
}
