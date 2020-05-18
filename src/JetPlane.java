package src;

public class JetPlane extends Aircraft implements Flyable{
	
	WeatherTower weatherTower;

	public JetPlane(String name, Coordinates c){
		super(name, c);
	}

	@Override
	public void updateConditions(){
		int longitude = coordinates.getLongitude();
		int latitude = coordinates.getLatitude();
		int height = coordinates.getHeight();

		switch(weatherTower.getWeather(coordinates)){
			case "SUN":
				latitude += 5;
				height += 2;
				System.out.printf("%s : %s\n", this, "NYOOOOOOOM");
				break;
			case "RAIN":
				latitude += 5;
				System.out.printf("%s : %s\n", this, "Whooosh...");
				break;
			case "FOG":
				latitude += 1;
				System.out.printf("%s : %s\n", this, "I am the cloud.");
				break;
			case "SNOW":
				height -= 7;
				System.out.printf("%s : %s\n", this, "Less whoosh...");
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
		return "JetPlane#" + name + "(" + id + ")";
	}
}