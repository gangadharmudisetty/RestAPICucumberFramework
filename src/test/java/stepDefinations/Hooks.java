package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException
	{
		//execute this code only when place_id=null
		//write a code that will give you placeid
		
		StepDefination sdef=new StepDefination();
		//place_id isstatic variable so we can call directly using class name
		if(StepDefination.place_id==null)
		{
			sdef.add_place_payload("Ganga", "Sanskrit", "Himalaya");
			sdef.user_calls_with_http_request("addplaceAPI", "post");
			sdef.verify_place_id_created_maps_to_using("Ganga", "getplaceAPI");
		}
		
	}

}
