# t1-java
Java SDK for MediaMath Platform APIs

#Build project
- For developer build
	>mvn clean install -Pdev

- For Production build
	>mvn clean install -Pprod

# Usage

- make sure the build path entries in your project are pointing to the jar file created.

### Authentication

- using constructor

    	TerminalOne one = new TerminalOne(username, password, key);
  
- using authenticate method
	
		TerminalOne one = new TerminalOne();
	 	one.authenticate(username, password, key) 

### How to Post
	 
-	you need to import the Entities from the package `com.mediamath.terminalone.models.*;`

	######Example
		
		TerminalOne one = new TerminalOne();
		one.authenticate("someUser", "somepwd", "somekey");

		Agency agency = new Agency();
		agency.setName("testAgency");
		agency.setOrganization_id(10001);

		agency = one.save(agency);

	The above snippet of code sets the agency entity attributes and saves it with the save method of TerminalOne

	The save method is overloaded, accepting different Entities as parameters. Entities are derived from `T1Entity` which serves as a base `interface` for all the entities.
	
	The save method throws `ClientException` or `ParseException` or `IOException` in case of any error occurs.

	The return type of the save method can be the same Type of Entity as passed into the argument OR it will be a null or in some special cases it will be `JsonResponse<? extends T1Entity>`

	####Video Creatives
	
	uploading a video creative is broken down into two steps.
	- first call creates a video creative and it returns a creative id.
	- second call uploads the file using the creative id received from the first step.

	first call requires a `VideoCreative` entity as an input param.

	#######Example

		TerminalOne t1 = new TerminalOne(user, password,production_key);
	
		VideoCreative videoCreative = new VideoCreative();
		videoCreative.setName("videoCreative2");
		videoCreative.setStartTime(1468486396); //unix timestamp
		videoCreative.setLandingUrl("http://www.somedomain.com");
		videoCreative.setAdvertiser(122631);
		videoCreative.setEndTime(1470009600); //unix timestamp
		videoCreative.setConcept(847527);
		videoCreative.setClickthroughUrl("http://www.somedomain.com");
		
		VideoCreativeResponse videoCreativeResponse = t1.saveVideoCreatives(videoCreative);
  

	the second call requires the creative id received from the step above. this will return a new `VideoCreativeResponse` with appropriate status.
	
		String filePath = "C:\\dir1\\subdir1\\file1234.flv";
		String fileName = "file1234.flv";
		VideoCreativeResponse uploadResponse = t1.uploadVideoCreative(filePath, fileName, videoCreativeResponse.getCreativeId());
	
	
	you can also check the upload status by calling 
		
		VideoCreativeUploadStatus uploadStatus = t1.getVideoCreativeUploadStatus(uploadResponse.getCreativeId());

	which returns a `VideoCreativeUplaodStatus` obj with appropriate response.

	####3PAS Creative Upload
	the 3PAS creative upload is broken down into two steps as shown below.

		TerminalOne t1 = new TerminalOne(user, password,api_key);
		
		// 3pas first call
		ThreePASCreativeUpload response = t1.save3pasCreativeUpload("C:\\dir1\\subdir1\\file1.txt", "file1" ,"file1");
		
		
		// 3pas second call 
		ThreePASCreativeBatchApprove batchApprove = new ThreePASCreativeBatchApprove();

		batchApprove.setBatchId(response.getBatch().getId());
		batchApprove.setAdvertiser_id("123456");
		batchApprove.setBatchIndex("1", null, null);
		batchApprove.setBatchIndex("4", null, null);
		batchApprove.setBatchIndex("3", null, null);
		
		JsonResponse<? extends T1Entity> finalJsonResponse = t1.save3pasCreativeUploadBatch(batchApprove);

	the first step is responsible to upload the creative to 3PAS 
	it takes in filePath, fileName, and a Name parameter as its inputs
	and returns a `ThreePASCreativeUpload` response.

	the second call takes in `ThreePASCreativeBatchApprove` as its input parameter
	the setBatchIndex method takes in 3 parametes: batch index, concept id, click url
	the concept id and click_url are associated to the specific batch index.

		batchApprove.setBatchIndex(pBatchIndex, concept, click_url)
	
	the second call returns `JsonResponse<? extends T1Entity>`.

	####T1AS Creative Upload
	the T1AS Creative upload is done in 2 steps
	as shown below.

	#######Example

		TerminalOne t1 = new TerminalOne(user, password,api_key);
		
		TOneASCreativeAssetsUpload response = t1.saveT1ASCreativeAssetsUpload("C:\\dir1\\subdir1\\JPGs.zip", "JPGs.zip", "t1asfileupload");
		
		assertNotNull(response);
		
		TOneASCreativeAssetsApprove creativeAssetsApprove = new TOneASCreativeAssetsApprove(); 
		creativeAssetsApprove.create(false, 
				"165615", 
				"http://ad.vendor.com/clicktracker/?id=1234", 
				"http://theactuallandingpage.com", 
				"BBVA_CaminoaleÔxito_160x600.swf", 
				"BBVA_CaminoaleÔxito_160x600.swf", 
				"665888");
		
		JsonResponse<? extends T1Entity> secondresponse = t1.saveTOneASCreativeAssetsApprove(creativeAssetsApprove);


	the first call to save T1AS Creative Assets uploads the file and returns the `TOneASCreativeAssetsApprove` obj in response.

	the second call to T1AS requires a `TOneASCreativeAssetsApprove`
	whose create method requires the following parameters
	
		TOneASCreativeAssetsApprove.create(boolean is_https, String advertiserid, String landingPage, String click_url, String primary, String backup, String concept)

	the second call returns the `JsonResponse<? extends T1Entity>` object in response which contans the appropriate status. 