package praktikum_9;

public class RestaurantDenganRunnable {
	public static void main(String[] args) {
		Thread t1 = new Thread(new CookingJob("Soup"));
		Thread t2 = new Thread(new CookingJob("Pizza"));
		Thread t3 = new Thread(new CookingJob("Burger"));
		
		t1.start();
		t2.start();
		t3.start();
	}

}

class CookingJob implements Runnable {
	private String task;
	
	CookingJob(String task) {
		this.task = task;
	}
	
	public void run() {
		System.out.println(task + " is being prepared by " + Thread.currentThread().getName());
	}
	
}
