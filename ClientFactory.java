import java.util.Random;

public class ClientFactory{ 

	private Client clientList[];

	public ClientFactory() {
		clientList = new Client[10];

		for(int i = 0; i < clientList.length; i++)
			clientList[i] = new Client();

		clientList[0].setName("Richard Barnes");
		clientList[1].setName("Panin Tenkorang");
		clientList[2].setName("Benjamin Kissi");
		clientList[3].setName("Casssandra Sarfo");
		clientList[4].setName("Samuel Amoah");
		clientList[5].setName("Samson Kuber");
		clientList[6].setName("Jerry King");
		clientList[7].setName("Philip Nunoo");
		clientList[8].setName("Derek Frimpong");
		clientList[9].setName("Moses Krieger");
	}

	public Client generateClient() {
		Random random = new Random();
		int index = random.nextInt(clientList.length);
		clientList[index].setFriends(random.nextInt(4));
		return clientList[index];
	}
}