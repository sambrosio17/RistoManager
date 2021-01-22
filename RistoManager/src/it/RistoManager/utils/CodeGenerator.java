package it.RistoManager.utils;

import java.util.Random;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;

public class CodeGenerator {
	
	private Random r;
	private char[] alphabet;
	private int digits;
	
	public CodeGenerator() {
		r=new Random();
		String alpha="abcdefghilmnopqrstuvzjkywx0123456789";
		alphabet=alpha.toCharArray();
		digits=5;
	}
	
	/* Restituisce una stringa casuale di 5 caratteri utilizzando le impostazioni di base
	 * 
	 * 
	 * @return code stringa casuale di 5 caratteri
	 */
	
	public String generate() {
		String code=NanoIdUtils.randomNanoId(r, alphabet, digits);
		return code;
	}
	
	public String generate(int digits) {
		this.digits=digits;
		return generate();
	}
	
	public String generate(String alpha) {
		
		alphabet=alpha.toCharArray();
		
		return generate();
	}

}
