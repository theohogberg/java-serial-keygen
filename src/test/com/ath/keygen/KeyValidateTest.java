package test.com.ath.keygen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ath.keygen.KeyCore;
import com.ath.keygen.KeyGenerate;
import com.ath.keygen.KeyValidate;

public class KeyValidateTest {

	KeyGenerate kg = new KeyGenerate();
	KeyValidate kv = new KeyValidate();
	
	@Before
	public void setUp(){
		kg.nextTwelveBytes();		
	}

	@Test
	public void isKeyValid_shouldBeValid_lowSeed() {
		assertTrue("Shared bytes object across classes", kv.isKeyValid(kg.MakeKey(0)));
	}
	
	
	@Test
	public void CheckKeyChecksum_shouldBeFalse() {
		assertFalse("Check that checksum is incorrect", kv.CheckKeyChecksum("00-!@#$-ff26-8b5c-faa7-355e-3414-04"));		
	}
	
	@Test
	public void CheckKey_shouldBeInvalid() {
		assertEquals("Check that the key is incorrect",  KeyCore.Status.KEY_INVALID, kv.CheckKey("00-!@#$-ff26-8b5c-faa7-355e-3414-04"));		
	}
	
	@Test
	public void CheckKey_shouldBeGood() {
		kg.getBytes().equals(kv.getBytes());
		assertEquals("Check that the key is correct",  KeyGenerate.Status.KEY_GOOD, kv.CheckKey(kg.MakeKey(20000000)));		
	}
	
	@Test
	public void CheckKey_shouldBeBlacklisted() {
		kv.addBlacklist("000000ff26");
		assertEquals("Check that the key is correct",  KeyGenerate.Status.KEY_BLACKLISTED, kv.CheckKey("00-0000-ff26-8b5c-faa7-355e-3414-04"));		
	}
	
	@Test
	public void CheckKey_shouldBePhony() {
		assertEquals("Check that the key is correct",  KeyGenerate.Status.KEY_PHONY, kv.CheckKey("af-afaf-af26-88ad-28ab-b99f-0ed7-6c"));		
	}
	
		

}
