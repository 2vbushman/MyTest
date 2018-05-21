package com.example.dmitry.mytest;

import com.example.dmitry.mytest.util.CredentialsValidator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class CredentialsUnitTest {
	@Test
	public void assert_password_correct() {
		assertTrue(CredentialsValidator.validatePassword("aaaaaA1"));
		assertTrue(CredentialsValidator.validatePassword("AAAAa1"));
		assertTrue(CredentialsValidator.validatePassword("1111aA"));
	}

	@Test
	public void assert_password_incorrect() {
		assertFalse(CredentialsValidator.validatePassword("aaaaaa"));
		assertFalse(CredentialsValidator.validatePassword("AAAAAA"));
		assertFalse(CredentialsValidator.validatePassword("111111"));

		assertFalse(CredentialsValidator.validatePassword("aaaaaA"));
		assertFalse(CredentialsValidator.validatePassword("AAAAAa"));
		assertFalse(CredentialsValidator.validatePassword("111111"));

		assertFalse(CredentialsValidator.validatePassword("11111"));
		assertFalse(CredentialsValidator.validatePassword("aaaaa"));
		assertFalse(CredentialsValidator.validatePassword("AAAAA"));
	}

	@Test
	public void assert_email_correct() {
		assertTrue(CredentialsValidator.validateEmail("aaa@aaa.com"));
	}

	@Test
	public void assert_email_incorrect() {
		assertFalse(CredentialsValidator.validateEmail("aaa@aaa."));
		assertFalse(CredentialsValidator.validateEmail("aaa@@aaa.com"));
		assertFalse(CredentialsValidator.validateEmail("a@aa@aaa.com"));
		assertFalse(CredentialsValidator.validateEmail("aaa@aaa..com"));
		assertFalse(CredentialsValidator.validateEmail("aaa@aaa_com"));
		assertFalse(CredentialsValidator.validateEmail("@aaa.com"));
		assertFalse(CredentialsValidator.validateEmail("aaa.com"));
		assertFalse(CredentialsValidator.validateEmail("aaa.com"));
		assertFalse(CredentialsValidator.validateEmail("."));
		assertFalse(CredentialsValidator.validateEmail(".com"));
		assertFalse(CredentialsValidator.validateEmail("com"));
		assertFalse(CredentialsValidator.validateEmail(""));
	}
}