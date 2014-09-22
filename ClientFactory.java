import java.util.Random;

public class ClientFactory{ 

	private Client clientList[];

	public ClientFactory() {
		clientList = new Client[10];

		for(int i = 0; i < clientList.length; i++)
			clientList[i] = new Client();

		clientList[0].name = "Richard Barnes";
		clientList[1].name = "Panin Tenkorang";
		clientList[2].name = "Benjamin Kissi";
		clientList[3].name = "Casssandra Sarfo";
		clientList[4].name = "Samuel Amoah";
		clientList[5].name = "Samson Kuber";
		clientList[6].name = "Jerry King";
		clientList[7].name = "Philip Nunoo";
		clientList[8].name = "Derek Frimpong";
		clientList[9].name = "Moses Krieger";
	}

	public Client generateClient() {
		Random random = new Random();
		int index = random.nextInt(clientList.length);
		clientList[index].friends = random.nextInt(4);
		return clientList[index];
	}
}