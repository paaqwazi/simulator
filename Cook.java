import java.util.Random;

public class Cook {

	private Meal meal;
	private final static Random r = new Random();

	public void prepareMeal(Meal meal) {
		this.meal = meal;
	}

	public Meal getFood() {
		return meal;
	}
}