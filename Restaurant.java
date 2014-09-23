public class Restaurant {
	private int tables[] = {0, 0, 0, 0, 0};
	public static int numberOfClients = 0;
	private Cook cook;
	private Server servers[] = new Server[2];
	private Meal menu[];
	public Restaurant() {
		System.out.println("Welcome to MEST Restaurant");

		//initalize menu
		menu = new Meal[10];

		//Populating menu
		menu[0] = new Meal("Banku and Okro", 10.99);
		menu[1] = new Meal("Red Red", 25.00);
		menu[2] = new Meal("Rice and gravy with fish fillet",30.00);
		menu[3] = new Meal("Banku and Tilapia", 35.00);
		menu[4] = new Meal("Yam and palava sauce", 15.00);
		menu[5] = new Meal("Fufu and goundnut soup", 35.00);
		menu[6] = new Meal("Fufu with palmnut soup", 33.00);
		menu[7] = new Meal("French fries", 30.00);
		menu[8] = new Meal("Noodles with sausage", 25.00);
		menu[9] = new Meal("Waakye with fish", 27.00);


		//initialize cook
		cook = new Cook();

		//initialize servers
		servers[0] = new Server();
		servers[1] = new Server();
	}	

	public Meal[] getMenu() {
		return menu;
	}

	public void serveNewClient(Client client) {
		Restaurant.numberOfClients += (1 + client.getFriends());
		client.setTableNumber(getTableNumber());
		seatClient(client.getTableNumber(), (1 + client.getFriends()));

		availableServer(client.chooseMenu(getMenu()), client);
	}

	public boolean isAvailableTable() { 
		if(tables[0] != 0 && tables[1] != 0 && tables[2] != 0 && tables[3] != 0 && tables[4] != 0)
			return false;
		else return true;
	}

	public void seatClient(int index, int number) {
		tables[index] = number;
	}

	public void availableServer(int meal, Client client) {
		if(servers[0].isFree()) {
			servers[0].toggleFree();
			System.out.println("Served by: server1");
			servers[0].takeMenu(menu[meal]); //waiter takes client's preferred dish
			cook.prepareMeal(servers[0].getClientDish()); //cook prepares dish based on what client chose
			servers[0].sendMeal(cook.getFood(), client); //server takes prepared dish to client
			numberOfClients -= (1 + client.getFriends());
			tables[client.getTableNumber()] = 0;
			servers[0].toggleFree();
		} else if(servers[1].isFree()){ 
			servers[0].toggleFree();
			System.out.println("Served by: server2");
			servers[0].takeMenu(menu[meal]); //waiter takes client's preferred dish
			cook.prepareMeal(servers[0].getClientDish()); //cook prepares dish based on what client chose
			servers[0].sendMeal(cook.getFood(), client); //server takes prepared dish to client
			numberOfClients -= (1 + client.getFriends());
			tables[client.getTableNumber()] = 0;
			servers[0].toggleFree();
		}
	}

	public int getTableNumber() {
		int index = 0;
		for( int i = 0; i < tables.length; i++)
			if(tables[i] == 0) {
				index = i;
				break;
			}

		return index;
	}
}
