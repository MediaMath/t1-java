package com.mediamath.terminalone;

public class Temp {
	public static void main(String[] args) {
		String s =  "hello world this is ? and this is a &";
		
		System.out.println(s.indexOf("?"));
		System.out.println(s.contains("?"));
		System.out.println(s.contains("RR"));
	}
}
