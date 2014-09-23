public class Menu {

	private Meal meal[];

	public Menu() {
		meal = new Meal[10];

		//Populating meal
		meal[0] = new Meal("Banku and Okro", 10.99);
		meal[1] = new Meal("Red Red", 25.00);
		meal[2] = new Meal("Rice and gravy with fish fillet",30.00);
		meal[3] = new Meal("Banku and Tilapia", 35.00);
		meal[4] = new Meal("Yam and palava sauce", 15.00);
		meal[5] = new Meal("Fufu and goundnut soup", 35.00);
		meal[6] = new Meal("Fufu with palmnut soup", 33.00);
		meal[7] = new Meal("French fries", 30.00);
		meal[8] = new Meal("Noodles with sausage", 25.00);
		meal[9] = new Meal("Waakye with fish", 27.00);
	}

	public Meal[] getMenu() {
		return meal;
	}
}