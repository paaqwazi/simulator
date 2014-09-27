public class ClientState {
	
	private Client client;
	private int meal;

	public ClientState(Client c, int m) {
		client = c;
		meal = m;
	}

	public int getMeal() {
		return meal;
	}

	public Client getClient(){ 
		return client;
	}
}