import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class Simulator {

	public static void main(String args[]){
		final int hours = Integer.parseInt(args[0]) * 60 * 60 * 1000; //convert input to hours
		final Timer timer = new Timer();			//create new timer object
		final Random random = new Random(); //create a random object

		//Simulator create a new restaurant
		Restaurant restaurant = new Restaurant();

		//Restaurant in session
		timer.scheduleAtFixedRate(new TimerTask() {
			long prev = System.currentTimeMillis(); //store start time
			ClientFactory cf = new ClientFactory();
			public void run() {

				//check if restaurant is still open: if program time is 7 hours.
				if((System.currentTimeMillis() - prev) == hours) {
					this.cancel();
					timer.cancel();
					return;
				}

				//generate client based on probability
				if(random.nextInt(2) == 1) {
					Client newClient = cf.generateClient();

					if((Restaurant.numberOfClients <= 20) && (Restaurant.numberOfClients + newClient.friends + 1) <= 20) {
						Restaurant.numberOfClients += (1 + newClient.friends);

						System.out.println(Restaurant.numberOfClients);
					}
				}		
			}
		}, 0, 5000);
	}
}
