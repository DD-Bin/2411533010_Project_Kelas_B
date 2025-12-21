package praktikum_9;

class Cooking {
    private String task;

    Cooking(String task) {
        this.task = task;
    }

    public void run() {
        System.out.println(task + " is being prepared");
    }
}

public class RestaurantTanpaThread {
    public static void main(String[] args) {
        Cooking t1 = new Cooking("Pasta");
        Cooking t2 = new Cooking("Salad");
        Cooking t3 = new Cooking("Dessert");
        Cooking t4 = new Cooking("Rice");

        t1.run();
        t2.run();
        t3.run();
        t4.run();
    }
}
