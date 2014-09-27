import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

public class Simulator {
	public static void main(String args[]) {
		final Timer timer = new Timer();
		final Scanner input = new Scanner(System.in);
		final Random random = new Random();
		final Restaurant restaurant = new Restaurant();
		final ClientFactory cf = new ClientFactory();
		final long time = Integer.parseInt(args[0]) * 100 * 60;
		int t = Integer.parseInt(args[0]);
		while(t < 1 || t > 7) {
			System.out.print("Time > 7 hours. Enter new time: ");
			t = input.nextInt();
		}

		timer.scheduleAtFixedRate(new TimerTask() {
			long prev = System.currentTimeMillis();
			public void run() {

				if((System.currentTimeMillis() - prev) > time) {
					this.cancel();
					timer.cancel();
					restaurant.cleanUp();
					restaurant.printSummary();
					return;
				}

				int options = random.nextInt(5) + 1;

				switch(options) {
					case 1: //creates a client based on a random figure
						if(random.nextInt(2) == 1) {
							Client client = cf.generateClient();
							if(!restaurant.isFull())
								restaurant.addClient(client);
							else System.out.printf("%s was rejected. Restaurant full.\n", client.getName());
						}
						break;
					case 2:
						restaurant.chooseMenu();
						break;
					case 3:
						restaurant.takeMenu();
						break;
					case 4:
						restaurant.prepareMeal();
						break;
					case 5:
						restaurant.clientLeaves();
						break;
				}
			}
		}, 0, 100);
	}
}