package com.ath.keygen;

import java.util.ArrayList;

public class KeyValidate extends KeyCore {

	private ArrayList<String> black_list = new ArrayList<String>();
	
	public boolean isKeyValid(String str) {
		return CheckKey(str) == Status.KEY_GOOD;
	}
	
    public void addBlacklist(String val){
    	black_list.add(val);
    }
	
	public Status CheckKey(final String S){
		Status result = Status.KEY_INVALID;
		if (!CheckKeyChecksum(S)){
			return result;
		}
		
		String key = S.toLowerCase().replaceAll("-", "");
		if (black_list.size() > 0 ){
			for (String bl : black_list){
				if (key.startsWith(bl)){
					return Status.KEY_BLACKLISTED;
				}
			}
		}
		
		final int seed;
		try {
			seed = Integer.valueOf(key.substring(0, 8), 16);
		} catch (NumberFormatException e) {
			return Status.KEY_PHONY;
		}
		
        // test key 0
        final String kb0 = key.substring(8, 12);
        final short b0 = GetKeyShort(seed, bytes[0], bytes[1], bytes[2]);
        if (!kb0.equals(intToHex(b0, 4))) {
            return Status.KEY_PHONY;
        }
        
        // test key1
        final String kb1 = key.substring(12, 16);
        final short b1 = GetKeyShort(seed, bytes[3], bytes[4], bytes[5]);
        if (!kb1.equals(intToHex(b1, 4))) {
            return Status.KEY_PHONY;
        }

        // test key2
        final String kb2 = key.substring(16, 20);
        final short b2 = GetKeyShort(seed, bytes[6], bytes[7], bytes[8]);
        if (!kb2.equals(intToHex(b2, 4))) {
            return Status.KEY_PHONY;
        }

        // test key3
        final String kb3 = key.substring(20, 24);
        final short b3 = GetKeyShort(seed, bytes[9], bytes[10], bytes[11]);
        if (!kb3.equals(intToHex(b3, 4))) {
            return Status.KEY_PHONY;
        }
		return Status.KEY_GOOD;
	}
	
	
	public boolean CheckKeyChecksum(final String key){
		boolean result = false;
		
		String s = key.replaceAll("-", "").toLowerCase();
		if (s.length() != 28){
			return result;
		}
		
		final String checksum = s.substring(24);
		final boolean isEqual = checksum.equals(GetChecksum(s.substring(0, 24)));
		return isEqual;
	}
	
}
