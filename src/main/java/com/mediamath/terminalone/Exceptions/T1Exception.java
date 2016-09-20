/*******************************************************************************
 * Copyright 2016 MediaMath
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.mediamath.terminalone.Exceptions;

public class T1Exception extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String code; 
	
	private String message;
	
	public T1Exception() {
		 super();
	}

	public T1Exception(String message) {
		 super(message);
	}
	
	public T1Exception(String code, String message) {
		super(message);
		this.message = message;
		this.code = code;
	}
	
	public T1Exception(String message, Throwable pCause) {
		 super(message, pCause);
		 this.message = message;
	}
	
	public T1Exception(Throwable pCause) {
		 super(pCause);
	}
	
	@Override
	public String toString() {
		return this.code + ": " + this.message;
	}
}
