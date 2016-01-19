package com.mediamath.jterminalone.utils;

//Filters for use with JT1.find
public final class Filters {
	
public static String IN = "()";
public static String NULL = ":";
public static String NOT_NULL = ":!";
// Equals operator is *different* between M&E (==) and Picard (=)
public static String EQUALS = "==";
public static String NOT_EQUALS = "!=";
public static String GREATER = "%3E";
public static String GREATER_OR_EQUAL = "%3E=";
public static String LESS = "%3C";
public static String LESS_OR_EQUAL = "%3C=";
public static String CASE_INS_STRING = "=:";


}
