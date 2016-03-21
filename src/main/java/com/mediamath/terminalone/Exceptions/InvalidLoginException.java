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

public class InvalidLoginException extends T1Exception {

	private static final long serialVersionUID = 1L;

	private String credentials;
	
	private String code;
	
	private String message;
		
	public InvalidLoginException(String code, String message, String credentials) {
		super(code, message);
		this.code = code;
		this.message = message;
		this.credentials = credentials;
	}
	
	
	@Override
	public String toString() {
		return "Invalid Login Exception: " + this.code + ": " + this.message + ", " + this.credentials;
	}

}
