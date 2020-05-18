package src;

import java.lang.Math;

public class WeatherProvider {
	static private WeatherProvider weatherProvider = null;
	static private String[] weather = {"SNOW", "RAIN", "FOG", "SUN"};
	static private int max_lo = 1;
	static private int max_la = 1;

	public WeatherProvider(){
		weatherProvider = this;
	}

	static public WeatherProvider getProvider(){
		if(weatherProvider == null)
			weatherProvider = new WeatherProvider();
		return weatherProvider;
    }
    
	public String getCurrentWeather(Coordinates co){
		double h = co.getHeight() / 100.0;
		int lo = co.getLatitude();
		int la = co.getLongitude();
		
		max_lo = Math.max(lo, max_lo);
		max_la = Math.max(la, max_la);

		double delta = Math.sqrt(Math.pow(lo, 2) + Math.pow(la, 2));
		double weatherChance = (lo / max_lo) * (la / max_la) * (delta / (lo + la));

		weatherChance += h;
		
		int index = (int)(weatherChance * (Math.random() * 100)) % 4;

		return weather[index];
	}
}