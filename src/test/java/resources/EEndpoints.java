package resources;

public enum EEndpoints {
	AddPlaceAPI("/add/json"),
	deletePlaceAPI("/delete/json"),
	getPlaceAPI("/get/json");
	
	private String resource;
	//Constructor of the enum
	EEndpoints(String resource) {
		
		this.resource = resource;
	}
	
	public String getResource() {
		
		return this.resource;
	}
	
	

}
