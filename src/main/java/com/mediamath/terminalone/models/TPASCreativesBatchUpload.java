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

public class TPASCreativesBatchUpload {
	
	private TPASCreativeBatch batch;
	
	private String called_on;
	
	private Status status;

	public TPASCreativeBatch getBatch() {
		return batch;
	}

	public void setBatch(TPASCreativeBatch batch) {
		this.batch = batch;
	}

	public String getCalledOn() {
		return called_on;
	}

	public void setCalledOn(String called_on) {
		this.called_on = called_on;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
