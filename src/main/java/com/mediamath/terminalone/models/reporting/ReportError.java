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
package com.mediamath.terminalone.models.reporting;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "Result")
public class ReportError {
	
	@JacksonXmlProperty(localName = "status")
	@JacksonXmlElementWrapper(useWrapping = false)
	private ReportStatus[] status;
	
	@JacksonXmlProperty(localName="entity")
	@JacksonXmlElementWrapper(useWrapping = false)
	private ReportErrorEntityInfo[] entity;

	public ReportStatus[] getStatus() {
		return status;
	}

	public void setStatus(ReportStatus[] status) {
		this.status = status;
	}

	public ReportErrorEntityInfo[] getEntity() {
		return entity;
	}

	public void setEntity(ReportErrorEntityInfo[] entity) {
		this.entity = entity;
	}


}
