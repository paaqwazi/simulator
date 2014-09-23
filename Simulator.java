import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;

public class Simulator {

	public static void main(String args[]){
		final int hours = Integer.parseInt(args[0]) * 60 * 60 * 1000; //convert input to hours
		final Timer timer = new Timer();			//create new timer object
		final Random random = new Random(); //create a random object

		//Simulator create a new restaurant
		final Restaurant restaurant = new Restaurant();

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
					//generate new client
					Client newClient = cf.generateClient();
					System.out.println("New Client generated: " + newClient.getName());

					//check if restaurant is not full
					if(restaurant.isAvailableTable()) {
						restaurant.serveNewClient(newClient);
					} else {
						System.out.println("No seat available for " + newClient.getName());
					}
				}		
			}
		}, 0, 5000);
	}
}
