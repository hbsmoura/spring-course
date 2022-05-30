package com.hbsmoura.springcourse.service.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class HashUtilTests {

	@Test
	public void getSecureHashTest() {
		String hash = HashUtil.getSecureHash("Teste");
		
		assertThat(hash.length()).isEqualTo(64);
	}
}
