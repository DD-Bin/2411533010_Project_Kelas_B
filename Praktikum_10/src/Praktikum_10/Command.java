package Praktikum_10;

import java.util.Calendar;

class Command implements Runnable {
	String taskName;
	
	public Command(String taskName) {
		this.taskName = taskName;
	}
	public void run() {
		try {
			System.out.println("Task Name : "
					+ this.taskName 
					+ "Current Time : "
					+ Calendar.getInstance().get(Calendar.SECOND));
		} catch ( Exception e) {
			e.printStackTrace();
		}
	}
}