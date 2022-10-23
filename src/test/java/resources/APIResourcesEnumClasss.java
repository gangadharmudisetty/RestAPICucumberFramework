package resources;
//enum is special class in JAVA which has collection of constants or methods
public enum APIResourcesEnumClasss {
		
	addplaceAPI("/maps/api/place/add/json"), //Enum treats as method now.to separate each and evry methods we have to mention ,
	getplaceAPI("/maps/api/place/get/json"),
	deleteplaceAPI("/maps/api/place/delete/json");
	private String resource;
	
	
	//we have create constructor for enum class-methods
	APIResourcesEnumClasss(String resource) {
		this.resource=resource;
		//resource:Here resource value  will store based on the value you are passing from placeValidations.feature
	}
	
	//After that we have to return the value to StepDefination class : APIResourcesEnumClasss.valueOf(resource)

	
	public String getResource()
	{
		return resource;
	}
	
	
}
