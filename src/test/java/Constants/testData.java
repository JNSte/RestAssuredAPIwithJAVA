package Constants;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlaceRequest;
import pojo.locationInput;

public class testData
{
	public  AddPlaceRequest AddPlacePayLoad(String address, String accuracy, String language)
	{
	AddPlaceRequest requestBody = new AddPlaceRequest();
	requestBody.setAccuracy(accuracy);
	requestBody.setAddress(address);
	requestBody.setLanguage(language);
	requestBody.setName("Frontlite house");
	requestBody.setPhone_number("(+91) 943 893 3937");
	requestBody.setWebsite("http://google.com");
	
	locationInput l= new locationInput();
	l.setLat(-38.383494);
	l.setLng(33.427362);
	requestBody.setLocation(l);
	
	List <String> setTypesList = new ArrayList<String>();
	setTypesList.add("shoe park");
	setTypesList.add("parlour");
	requestBody.setTypes(setTypesList);
	
	return requestBody;
}
	
	public static String deletePayLoad(String place_id)
	{
		return "{\r\n\"place_id\":\""+place_id+"\"\r\n}";
	}
}
