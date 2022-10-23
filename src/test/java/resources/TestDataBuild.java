package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String address)
	{
		AddPlace ap= new AddPlace();
		
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		ap.setPhone_number("(+91) 983 893 3937");
		
		ap.setWebsite("http://google.com");
		ap.setName(name);
		//we just cannot pass string inside it because types is the return type of list
		List<String>myList=new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		ap.setTypes(myList);
		
		
		Location l= new Location();
		l.setLng(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		return ap;
	}
	public String deletePlacePayload(String place_id)
	{
		 
		return "{\r\n"
				+ "    \"place_id\": \""+place_id+"\"\r\n"
				+ "}";
	}

}
