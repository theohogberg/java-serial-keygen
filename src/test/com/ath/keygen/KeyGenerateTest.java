package test.com.ath.keygen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.ath.keygen.KeyCore;
import com.ath.keygen.KeyGenerate;
import com.ath.keygen.KeyValidate;

public class KeyGenerateTest {

	KeyGenerate kg = new KeyGenerate();

	@Before
	public void setUp(){
		kg.nextTwelveBytes();		
	}

	@Test
	public void MakeKey_shouldGenerateTheSameKey_lowSeed() {
		final int SEED = 100;
		assertEquals("Same seed generates same key", kg.MakeKey(SEED), kg.MakeKey(SEED));
	}
	
	@Test
	public void MakeKey_shouldGenerateTheSameKey_highSeed() {
		final int SEED = 2140000000;
		assertEquals("Same seed generates same key", kg.MakeKey(SEED), kg.MakeKey(SEED));
	}
	
	@Test
	public void findBytesOnVersion_shouldBeSameAsBytes() {
		assertEquals("Check that the key is correct",  kg.getBytes(), kg.findBytesOnVersion("1"));		
	}
	
		

}
