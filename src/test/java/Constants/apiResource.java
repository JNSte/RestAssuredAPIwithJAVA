package Constants;

public enum apiResource 

{

	AddPlace("/maps/api/place/add/json"),
	GetPlace("/maps/api/place/get/json"),
	UpdatePlace("/maps/api/place/update/json"),
	DeletePlace("/maps/api/place/delete/json"),;

	private String resource;

	apiResource(String resource) 
	{
		this.resource  = resource;
	}
	
	public String getResource()
	{
		return resource;
	
	}
}
