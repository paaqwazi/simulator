public class Meal {

	private final String food;
	private final double price;
	
	Meal(String m, double p) {
		food = m;
		price = p;
	}

	public String getFood(){
		return food;
	}

	public double getPrice() {
		return price;
	} 
}
