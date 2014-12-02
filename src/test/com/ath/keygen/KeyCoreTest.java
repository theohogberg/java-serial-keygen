package test.com.ath.keygen;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ath.keygen.KeyCore;
import com.ath.keygen.KeyGenerate;
import com.ath.keygen.KeyValidate;

public class KeyCoreTest {
	
	KeyCore kc = new KeyCore();
	
	@Test
	public void GetChecksum_checksumShouldBeCorrect() {		
		assertEquals("Test checksum",  "cf2d", kc.GetChecksum("3b9ac9ff95866516c2f8"));
	}
	
}
