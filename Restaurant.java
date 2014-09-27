import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Date;
import java.util.ArrayList;

public class Restaurant {
	private int tables[] = {0, 0, 0, 0, 0};
	public static int numberOfClients = 0;
	private Cook cook;
	private Server servers[] = new Server[2];
	private Meal menu[];
	private final static Random random = new Random();

	//States at any point in time
	private Queue<Client> clientEntryState;
	private Queue<ClientState> clientChooseMenuState;
	private Queue<ClientState> clientMealTakenState;
	private Queue<ClientState> clientMealServedState;

	private int id = 0;

	private ArrayList<ArrayList<String>> summary = new ArrayList<ArrayList<String>>();

	public Restaurant() {
		System.out.println("================================");
		System.out.println("Welcome to MEST Restaurant");
		System.out.println("================================");
		System.out.println();

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

		clientEntryState = new LinkedList<Client>();
		clientChooseMenuState = new LinkedList<ClientState>();
		clientMealTakenState = new LinkedList<ClientState>();
		clientMealServedState = new LinkedList<ClientState>();
	}	

	public void cleanUp() {
		while(tables[0] != 0 || tables[1] != 0 || tables[2] != 0 || tables[3] != 0 || tables[4] != 0) {
			chooseMenu();
			takeMenu();
			prepareMeal();
			clientLeaves();
		}	
	}

	public Meal[] getMenu() {
		return menu;
	}

	public void addClient(Client client) {
		Restaurant.numberOfClients += (1 + client.getFriends());
		client.setTableNumber(getTableNumber());
		tables[client.getTableNumber()] = (1 + client.getFriends());
		clientEntryState.add(client);
		System.out.printf("%s: %s came in.\n", new Date(), client.getName());
		client.setId(id);
		summary.add(new ArrayList<String>());
		summary.get(id).add(String.valueOf(id));
		summary.get(id).add(client.getName());
		id++;
	}

	public void chooseMenu() {
		if(clientEntryState.size() != 0) {
			Client c = clientEntryState.poll();
			ClientState client = new ClientState(c, c.chooseMenu(menu));
			clientChooseMenuState.add(client);
			System.out.printf("%s: Client %s chose menu.\n", new Date(), client.getClient().getName());
			summary.get(client.getClient().getId()).add(menu[client.getMeal()].getFood());
		}
	}

	public void takeMenu() {
		if(clientChooseMenuState.size() != 0) {
			ClientState client = clientChooseMenuState.poll();
			if(servers[0].isFree()) {
				System.out.printf("%s: %s was served by server 1\n", new Date(), client.getClient().getName());
				servers[0].toggleFree();
				servers[0].takeMenu(menu[client.getMeal()]);
				clientMealTakenState.add(client);
			} else if(servers[1].isFree()){
				servers[1].toggleFree();
				System.out.printf("%s: %s was served by server 2\n", new Date(), client.getClient().getName());
				servers[1].takeMenu(menu[client.getMeal()]);
				clientMealTakenState.add(client);
			}
			System.out.printf("%s: Menu taken from %s.\n", new Date(), client.getClient().getName());
		}
	}

	public void prepareMeal() {
		if(clientMealTakenState.size() != 0) {
			ClientState client = clientMealTakenState.poll();
			if(!servers[0].isFree()) {
				cook.prepareMeal(servers[0].getClientDish());
				servers[0].sendMeal(cook.getFood(), client.getClient());
				summary.get(client.getClient().getId()).add("Server 1");
				clientMealServedState.add(client);
				servers[0].toggleFree();
			} else if (!servers[1].isFree()) {
				cook.prepareMeal(servers[1].getClientDish());
				servers[1].sendMeal(cook.getFood(), client.getClient());
				clientMealServedState.add(client);
				summary.get(client.getClient().getId()).add("Server 2");
				servers[1].toggleFree();
			}
			System.out.printf("%s: Meal prepared for %s\n", new Date(), client.getClient().getName());
		}
	}


	public void clientLeaves() {
		if(clientMealServedState.size() != 0 ) {
			ClientState client = clientMealServedState.poll();
			tables[client.getClient().getTableNumber()] = 0;
			numberOfClients -= (1 + client.getClient().getFriends());
			client.getClient().payBill(menu[client.getMeal()].getPrice());
			summary.get(client.getClient().getId()).add(String.valueOf(menu[client.getMeal()].getPrice()));
			System.out.printf("%s: %s left\n\n\n", new Date(), client.getClient().getName());
		}
	}

	public boolean isFull() { 
		if(tables[0] != 0 && tables[1] != 0 && tables[2] != 0 && tables[3] != 0 && tables[4] != 0)
			return true;
		else return false;
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

	public void printSummary() {
		System.out.println("\n\n");
		for(int i = 0; i < summary.size(); i++ ) {
			System.out.printf("%s\t%s\t%s\t%s\t%s\n", summary.get(i).get(0), summary.get(i).get(1), summary.get(i).get(2), summary.get(i).get(3), summary.get(i).get(4));
		}
	}
}
