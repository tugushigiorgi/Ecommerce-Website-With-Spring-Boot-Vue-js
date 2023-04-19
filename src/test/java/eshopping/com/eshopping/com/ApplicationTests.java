package eshopping.com.eshopping.com;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Transactional
@SpringBootTest
class ApplicationTests {



	@Test
	void contextLoads() {
		String secretKey = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";
		byte[] keyBytes = Base64.getDecoder().decode(secretKey);
		SecretKey key = new SecretKeySpec(keyBytes, "HmacSHA256");
		System.out.println("Is key valid? " + (key != null));
	}



}
