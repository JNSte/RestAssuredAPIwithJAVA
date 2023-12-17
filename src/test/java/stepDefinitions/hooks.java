package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;
import stepDefinitions.stepDefinitions;

public class hooks 
{

	@Before("@DeletePlace")
	public void BeforeScenario() throws IOException
	{
		stepDefinitions bef = new stepDefinitions();
		
		if(stepDefinitions.place_id == null)
		{
		bef.add_place_payload_with("Denmark", "77", "Danish");
		bef.user_calls_with_http_request("AddPlace", "POST");
		bef.verify_entered_for_the_new_place_id_same_as_received_from("Danish","GetPlace");
		}	
}
}