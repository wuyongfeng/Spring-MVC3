package com.demo.test.development;

import org.junit.Test;

public class Md5Test {
	@Test
	public void testMd5(){
		String psw = "wiugbviuwebviuwebiuvbweubvouewbvoeqbvoibqew9";
		System.out.println(org.springframework.util.DigestUtils.md5DigestAsHex(psw.getBytes())+"_9fd3efb7770584fb16fefa44e5d36a5f");
	}
}
