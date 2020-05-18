package src;

public class WeatherTower extends Tower{

	public void register(Flyable f){
		super.register(f);
		f.registerTower(this);
	}
	public String getWeather(Coordinates c){
		return WeatherProvider.getProvider().getCurrentWeather(c);
	}
	public void changeWeather(){
		super.conditionsChanged();
	}

}