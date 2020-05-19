package src;

public class Helicopter extends Aircraft implements Flyable {
	
	WeatherTower weatherTower;

	public Helicopter(String name, Coordinates c) {
		super(name, c);
	}

	public void updateConditions(){
		int longitude = coordinates.getLongitude();
		int latitude = coordinates.getLatitude();
		int height = coordinates.getHeight();

		switch(weatherTower.getWeather(coordinates)){
			case "SUN":
				latitude += 10;
				height += 2;
				System.out.printf("%s : %s \n", this, "pleasant");
				break;
			case "RAIN":
				latitude += 5;
				System.out.printf("%s : %s \n", this, "rain rain go away");
				break;
			case "FOG":
				longitude += 1;
				System.out.printf("%s : %s \n", this, "where am i");
				break;
			case "SNOW":
				height -= 12;
				System.out.printf("%s : %s \n", this, "not again");
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
		return "Helicopter#" + name + "(" + id + ")";
	}
}