import java.util.Random;

public class Client {

	private String name;
	private int friends = 0;
	private	int id;
	private int tableNumber;


	public int chooseMenu(Meal[] menu) {
		Random r = new Random();
		return r.nextInt(menu.length);
	}

	public void setTableNumber(int number) {
		tableNumber = number;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public void setName(String n) {
		name = n;
	}

	public String getName() {
		return ((friends == 0) ? name : ((friends == 1) ? name + " and 1 friend" : name + " and " + friends + " friends"));
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
		Random r = new Random();
		for(int i = 0; i < (r.nextInt(5) + 1) * 5000; i++);
	}

	public void payBill(double bill) {
		System.out.println("BILL FOR: " + getName());
		System.out.println("---------------------");

		if(friends == 0) System.out.println("Price: " + bill + "USD\nTotal: " + bill + "USD");
		else System.out.printf("Price: %d x %.2fUSD\nTotal: %.2fUSD\n", (friends + 1), bill, ((friends + 1) * bill));
	}
}
