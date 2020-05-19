package src;

public class Baloon extends Aircraft implements Flyable {
	WeatherTower weatherTower;
	
	public Baloon(String name, Coordinates c){
		super(name, c);
	}

	@Override
	public void updateConditions(){
		int longitude = coordinates.getLongitude();
		int latitude = coordinates.getLatitude();
		int height = coordinates.getHeight();

		switch(weatherTower.getWeather(coordinates)){
			case "SUN":
				longitude += 2;
				height += 4;
				System.out.printf("%s : %s\n", this, "shinny");
				break;
			case "RAIN":
				height -= 5;
				System.out.printf("%s : %s\n", this, "ah its wet");
				break;
			case "FOG":
				System.out.printf("%s : %s\n", this, "what.");
				height -= 3;
				break;
			case "SNOW":
				height -= 15;
				System.out.printf("%s : %s\n", this, "going Down.");
		};

		coordinates = new Coordinates(longitude, latitude, height);
		
		if(coordinates.getHeight() <= 0)
			weatherTower.unregister(this);
	}
	
	@Override
	public void registerTower(WeatherTower weatherTower){
		this.weatherTower = weatherTower;
	}

	@Override
	public String toString() {
		return "Baloon#" + name + "(" + id + ")";
	}
}