public class Server {
	private Meal meal;
	private Client client;
	private boolean free = true;

	public void takeMenu(Meal m) {
		meal = m;
	}

	public Meal getClientDish() {
		return meal;
	}

	public void toggleFree() {
		free = !free;
	}

	public boolean isFree() {
		return free;
	}

	public void sendMeal(Meal meal, Client client) {
		client.eat(meal);
	}
}
