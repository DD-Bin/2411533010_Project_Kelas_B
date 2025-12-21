package praktikum_9;

class CookingTask extends Thread {
    private String task;

    CookingTask(String task) {
        this.task = task;
    }

    @Override
    public void run() {
        System.out.println(task + " is being prepared by " + Thread.currentThread().getName());
    }
}

public class RestaurantDenganThread {
    public static void main(String[] args) {
        CookingTask t1 = new CookingTask("Pasta");
        CookingTask t2 = new CookingTask("Salad");
        CookingTask t3 = new CookingTask("Dessert");
        CookingTask t4 = new CookingTask("Rice");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
