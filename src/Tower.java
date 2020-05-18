package src;

import java.util.ArrayList;

public class Tower{

	private ArrayList<Flyable> observers;

	Tower(){
		observers = new ArrayList<Flyable>();
	}

	public void register(Flyable flyable){
		System.out.printf("Tower says: %s registered to weather tower.\n", flyable.toString());
		observers.add(flyable);
	}
	public void unregister(Flyable flyable){
		System.out.printf("Tower says: %s unregistered to weather tower.\n", flyable.toString());
		observers.remove(flyable);
	}

	protected void conditionsChanged(){
		Object cl = observers.clone();
		if (cl instanceof ArrayList<?>){
			ArrayList<?> bf = (ArrayList<?>) cl;
			for(int x = 0; x < bf.size(); x++){
				Object i = bf.get(x);
				if(i instanceof Flyable){
					((Flyable)i).updateConditions();
				}
			}
		}
	}

	public String toString(){
		String str = "";
		for(Flyable i : observers){
			str += i.toString() + "\n";
		}
		return str;
	}
}