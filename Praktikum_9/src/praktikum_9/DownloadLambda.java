package praktikum_9;

public class DownloadLambda {
    public static void main(String[] args) throws InterruptedException {

        Thread f1 = new Thread(() -> {
            for (int i = 10; i <= 100; i += 10) {
                System.out.println("File-1 Progress: " + i + "%");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("File-1 selesai diunduh!");
        });

        Thread f2 = new Thread(() -> {
            for (int i = 10; i <= 100; i += 10) {
                System.out.println("File-2 Progress: " + i + "%");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("File-2 selesai diunduh!");
        });

        Thread f3 = new Thread(() -> {
            for (int i = 10; i <= 100; i += 10) {
                System.out.println("File-3 Progress: " + i + "%");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("File-3 selesai diunduh!");
        });

        f1.start();
        f2.start();
        f3.start();

        System.out.println("\nDownloading...");
        f1.join();
        f2.join();
        f3.join();

        System.out.println("Semua file selesai diunduh!\n");

        System.out.println("Status akhir:");
        System.out.println(f1.getName() + ": " + f1.getState());
        System.out.println(f2.getName() + ": " + f2.getState());
        System.out.println(f3.getName() + ": " + f3.getState());
    }
}
