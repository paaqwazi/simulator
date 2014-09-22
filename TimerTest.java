import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	public static void main(String args[]) {
		Timer timer = new Timer();

		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				System.out.println("Hello World!!");
			}
		}, 0, 5000);
	}
}
