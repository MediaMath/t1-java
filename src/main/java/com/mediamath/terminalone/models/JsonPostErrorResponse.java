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
package com.mediamath.terminalone.models;

public class JsonPostErrorResponse {
	
	String etag;

	T1Error error;
	
	Object errors;
	
	T1Meta meta;
	
	public JsonPostErrorResponse() {}
	
	public JsonPostErrorResponse(Object errors) {
		this.setErrors(errors);
	}


	public String getEtag() {
		return etag;
	}


	public void setEtag(String etag) {
		this.etag = etag;
	}


	public T1Error getError() {
		return error;
	}


	public void setError(T1Error error) {
		this.error = error;
	}


	public Object getErrors() {
		return errors;
	}


	public void setErrors(Object errors) {
		this.errors = errors;
	}

	public T1Meta getMeta() {
		return meta;
	}

	public void setMeta(T1Meta meta) {
		this.meta = meta;
	}
}


