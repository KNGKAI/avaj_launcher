
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.lang.RuntimeException;

import src.AircraftFactory;
import src.WeatherTower;

public class avaj_launcher {
	public static void main(String[] args){
		if (args.length > 0) {
			String file = args[0]; 
			String type;
			String name;
			int longitude;
			int latitude;
			int height;
			int i = 0;

			System.out.println(file);

			try{
				FileReader	fr = new FileReader(file);
				Scanner fs = new Scanner(new BufferedReader(fr));
					
				if(!fs.hasNextInt()) {
					fs.close();
					throw new RuntimeException("Error Line " + 0 + ": No itterator found");
				}

				int n = fs.nextInt();

				if (n < 0) {
					fs.close();
					throw new RuntimeException("Error: itterator is negative");
				}
				
				AircraftFactory f = new AircraftFactory();
				WeatherTower tower = new WeatherTower();

				fs.nextLine();

				while(fs.hasNext()){
					Scanner ls = new Scanner(fs.nextLine());

					i++;
					
					if(!ls.hasNext()) {
						fs.close();
						ls.close();
						throw new RuntimeException("Error Line " + i + ": No type found");
					}

					type = ls.next();
					
					if (!type.equals("Baloon") && !type.equals("Helicopter") && !type.equals("JetPlane")) {
						fs.close();
						ls.close();
						throw new RuntimeException("Error Line " + i + ": Invalid type, " + type);
					}

					if(!ls.hasNext()) {
						ls.close();
						fs.close();
						throw new RuntimeException("Error Line " + i + ": No name found");
					}

					name = ls.next();
					
					if(!ls.hasNextInt()){
						ls.close();
						fs.close();
						throw new RuntimeException("Error Line " + i + ": No longitude found");
					}
					
					longitude = ls.nextInt();
					
					if(longitude < 0){
						ls.close();
						fs.close();
						throw new RuntimeException("Error Line " + i + ": longitude is negative");
					}
					
					if(!ls.hasNextInt()){
						ls.close();
						fs.close();
						throw new RuntimeException("Error Line " + i + ": No latitude found");
					}

					latitude = ls.nextInt();
					
					if(latitude < 0){
						ls.close();
						fs.close();
						throw new RuntimeException("Error Line " + i + ": latitude is negative");
					}

					if(!ls.hasNextInt()){
						ls.close();
						fs.close();
						throw new RuntimeException("Error Line " + i + ": No height found");
					}

					height = ls.nextInt();
					
					if(height < 0){
						ls.close();
						fs.close();
						throw new RuntimeException("Error Line " + i + ": height is negative");
					}
					
					tower.register(f.newAircraft(type, name, longitude, latitude, height));

					ls.close();
				}

				for(int a = 0; a < n; a++)
					tower.changeWeather();
			} catch (FileNotFoundException ex){
				System.err.println("Could not read file");
			} catch (RuntimeException ex){
				System.err.printf("An exception has occured: %s\n", ex.getMessage());
				ex.printStackTrace();
			}
		} else {
			System.err.println("No scenario provided");
		}
	};
}