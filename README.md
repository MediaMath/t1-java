# t1-java
Java SDK for MediaMath Platform APIs

# Maven Integration
You can integrate the latest t1-java build easily by just including the following lines in your pom.xml.

   <dependencies>
      <dependency>
           <groupId>com.mediamath</groupId>
           <artifactId>t1-java</artifactId>
           <version>0.0.1</version>
       </dependency>
       ...
   </dependencies>

 You can also download the latest jar from Maven Central or from the following source - https://oss.sonatype.org/content/repositories/releases/com/mediamath/t1-java/0.0.1/t1-java-0.0.1.jar

#Build project from source	
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


###Fetching Entities and Collections
Using **get** method of TerminalOne we can fetch entities and collections.


		TerminalOne one = new TerminalOne();
		one.get(QueryCriteria query)	

Returns: If single entity is specified, returns a single entity object. If multiple entities, generator yielding each entity.

**QueryCriteria** class has parameters as below:

 		
 - **collection**: T1 collection i.e. entity to fetch, like "advertisers","campaigns"
 
 		Example: query.setCollection("campaigns");
 
-  **entity**: Integer ID of entity being retrieved from T1
 		
 		Example: query.setEntity(12312);
 
-  **child**: Child object of a particular entity, e.g. "dma", "acl","margins"
 
 		Example: query.setChild("browser");
 
-  **sortBy:** sort order. Default "id". e.g. "-id", "name"
 
 
 		Example: query.setSortBy("id"); //ascending
 				 query.setSortBy("-id"); //descending
 
-  **parent:** Integer ID of entity. When set, it returns entities with this parent id.
 		
 		Example: query.setParent(Long.valueOf(111555)); //requires long value
 
 
-  **limit:** to query the entity. Its a Map of name-value pair of size 1.

		Example: Map<String, Long> limitMap = new HashMap<String, Long>();
				 limitMap.put("agency", Long.valueOf(111555));
				 query.setLimit(limitMap);
 
 
-  **full:** When retrieving multiple entities, specifies which types to return     the full record. It can accept values as boolean(true/false), String or List of Strings.

		Example: 1. As Boolean
					FullParamValues fullParamvalue = new FullParamValues();
					fullParamvalue.setBoolValue(true);	
				 	query.setFull(fullParamvalue);
			
				 2. As String
				 	FullParamValues fullParamvalue = new FullParamValues();
					fullParamvalue.setStrValue("agency"); 
					query.setFull(new FullParamValues().setStrValue("agency"))

				 3. As List of Strings
					List<String> fullList = new ArrayList<String>();
					fullList.add("campaign");
					fullList.add("advertiser");
					FullParamValues fullParamvalue = new FullParamValues();
					fullParamvalue.setListValue(fullList)
					query.setFull(fullParamvalue)

- **pageLimit** and **pageOffset**:  handles pagination. 
 
 	*pageLimit* specifies how many entities to return at a time, default and max of 100.

	*pageOffset* specifies which entity to start at for that page.


		Example: query.setPageLimit(10);
				 query.setPageLimit(10).setPageOffset(200);

- **getAll:** Boolean(true/false) whether to retrieve all results for a query or just a single page. Mutually exclusive with pageLimit/pageOffset.

		Example:query.setGetAll(true);

-  **include:** String/List of relations to include in query.

		Example: 1. As String
					query.setInclude(new ConditionQuery("agency"));	
				 2. As List of lateral (non-hierarchical) relations
				    query.setInclude(new ConditionQuery("agency","organization"));
				 3.As List of list/strings of hierarchical relations
					query.setInclude(new ConditionQuery("ad_server"))
						 .setInclude(new ConditionQuery("vertical"))
-  **query:** String search parameters. Invoked by **`find`**. 
		
		Example: query.setQuery("agency_id%3E=109308");


We can use different combinations of these parameters to extract expected result. Here are some complete examples.

		    TerminalOne one = new TerminalOne();
    
    		QueryCriteria query = QueryCriteria.builder()
    								.setCollection("advertisers")		//collection
    								.setEntity(154161)		//entity
    								.build();			
    		JsonResponse<?> jsonresponse = one.get(query);
    		------------------------------------------------------------
    		QueryCriteria query = QueryCriteria.builder()
    								.setCollection("advertisers")
    								.setEntity(154161)
    								.setInclude(new ConditionQuery("agency", "organization"))  		
    								.build();
    		JsonResponse<?> jsonresponse = one.get(query);
    		------------------------------------------------------------
    		QueryCriteria query = QueryCriteria.builder()
    								.setCollection("advertisers")
    								.setPageLimit(40)		//pageLimit
    								.setPageOffset(100)		//pageOffset
    								.build();
    		JsonResponse<?> jsonresponse = one.get(query);
    		------------------------------------------------------------
    		Map<String, Long> limitList = new HashMap<String, Long>();
    		limitList.put("agency", Long.valueOf(111555));
    		QueryCriteria query = QueryCriteria.builder()
    								.setCollection("advertisers")		 
    								.setLimit(limitList)		//limit
    								.setGetAll(true)		//getAll
    								.setSortBy("-updated_on")
    								.build();
    		JsonResponse<?> jsonresponse = one.get(query);
    		------------------------------------------------------------
    		QueryCriteria query = QueryCriteria.builder()
    								.setCollection("strategies")
    								.setEntity(1376198)
    								.setChild("browser")
    								.setPageLimit(1)
    								.build();
     
    
### Searching for entities
Limiting entities by relation ID is one way to limit entities, but we can also search with more intricate queries using **`find`**:

		TerminalOne one = new TerminalOne();
		one.find(QueryCriteria query);

Here QueryCriteria has 3 parameters

1. queryParamName: String query parameter name
2. queryOperator: arithmetic operator like < > = !=. 
		
	It handled by Fileters object constants-

	- IN
	- NULL
	- NOT_NULL
	- EQUALS
	- NOT_EQUALS
	- GREATER
	- GREATER_OR_EQUAL
	- LESS
	- LESS_OR_EQUAL
	- CASE_INS_STRING

3. queryParams : values to pass to queryParameter. It can be Boolean, String, 					Number or any List

		Example: 1. queryParam As Number   
				QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setQueryParamName("agency_id")
									.setQueryOperator(Filters.GREATER_OR_EQUAL)
									.setQueryParams(new QueryParamValues(109308))
									.setPageLimit(100)
									.build();
				JsonResponse<?> jsonresponse = one.find(query);

				2. queryParam as String
				QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setQueryParamName("name")
									.setQueryOperator(Filters.EQUALS)
									.setQueryParams(new QueryParamValues("Retirement"))
									.setPageLimit(100)
									.build();
				JsonResponse<?> jsonresponse = one.find(query);

				3. queryParam as List, to be used as IN query
				//form list
				List<Object> inQueryParams = new ArrayList<Object>();
				inQueryParams.add(154121);
				inQueryParams.add(153226);
				inQueryParams.add(150994);
				QueryCriteria query = QueryCriteria.builder()
									.setCollection("advertisers")
									.setQueryParams(new QueryParamValues("name"))
									.setQueryOperator(Filters.IN)
									.setQueryParams(new QueryParamValues(inQueryParams))
									.setPageLimit(100)
									.build();
				JsonResponse<?> jsonresponse = one.find(query);

*TerminalOne.find(QueryCriteria q)* method can be used as a helper method to *TerminalOne.get(QueryCriteria q)* method and all get related QueryCriteria parameters can be used along with find() parameters. 


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

- ####Video Creatives
	
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

- ####3PAS Creative Upload
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

	>batchApprove.setBatchIndex(pBatchIndex, concept, click_url)
	
	the second call returns `JsonResponse<? extends T1Entity>`.


- ####T1AS Creative Upload
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


### Reporting

The Reports API on TerminalOne Platform allows advertisers to access, query and aggregate reporting data
	
There are two types of requests that can be made against Reports API: requests for metadata information, and requests for data retrieval. All data retrieval requests are performed synchronously.

- ####Get the meta data on number of the reports supported by the API

		t1 = new TerminalOne(user, password, api_key);
		JsonResponse<? extends T1Entity> jsonresponse = t1.getMeta();
	
	the above code snippets fetches all the supported reports information
	it returns a Meta object containing all the meta data.

- ####Get Individual Report Definition
	
	you can get meta data for each report as shown below.
	
		t1 = new TerminalOne(user, password, api_key);
		MetaData metaResponse = t1.getReportsMeta(Reports.GEO);

	getReportsMeta takes in Reports enum; select a desired report from the enum

	the method returns a MetaData object which contains meta data like dimension and metrics for the specified report. 

- ####Get Report Data
	retrieve report data as shown below.
	> t1.getReport(Reports.PERFORMANCE, report);

	this method takes in a Reports enum and a ReportCriteria criteria object.

	the Report Criteria is used set the filters, dimensions and metrics parameters to query the reporting api, as shown below.
	
		ReportCriteria report = new ReportCriteria();
		report.setDimension("advertiser_name");
		report.setDimension("campaign_id");
		report.setDimension("campaign_name");
		report.setFilter("organization_id", "=", "100000");
		report.setMetric("impressions");
		report.setMetric("clicks");
		report.setMetric("total_conversions");
		report.setMetric("media_cost");
		report.setMetric("total_spend");
		report.setTime_rollup("by_day");
 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String stateDate = df.format(df.parse(dateInString));
		String endDate = df.format(df.parse(endDateInString));
		report.setStart_date(stateDate);
		report.setEnd_date(endDate);

	the start date and end date can be specified in the following formats.
	- month - YYYY-MM
	- day - YYYY-MM-DD
	- hour - YYYY-MM-DDThh
	- minute - YYYY-MM-DDThh:mi
	- second - YYYY-MM-DDThh:mi:ss

	the response is always a .csv file which is created at
	`reports/performance_2016_09_27_11_12_02.csv`
