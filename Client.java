import java.util.Random;

public class Client {

	private String name;
	private int friends = 0;
	private int tableNumber;


	public int chooseMenu(Meal[] menu) {
		Random r = new Random();
		return r.nextInt(menu.length);
	}

	public void setTableNumber(int number) {
		tableNumber = number;
	}

	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return name;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setFriends(int value) {
		friends = value;
	}

	public int getFriends() {
		return friends;
	}

	public void eat(Meal meal) {

	}

	public void payBill(double bill) {
		System.out.println("BILL FOR: " + ((friends == 0) ? getName() : ((friends == 1) ? getName() + " and 1 friend" : getName() + " and " + friends + " friends")));
		System.out.println("---------------------");

		if(friends == 0) System.out.println("Price: " + bill + "USD\nTotal: " + bill + "USD");
		else System.out.printf("Price: %d x %.2fUSD\nTotal: %.2fUSD", (friends + 1), bill, ((friends + 1) * bill));
		System.out.println();
		System.out.println();
	}
}
