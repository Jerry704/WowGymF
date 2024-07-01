package tool;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Clock implements Runnable {

	@Override
	public void run() {}
	
	/*
	 	addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				super.windowActivated(e);
				new Thread(new Clock() {
					@Override
					public void run() {
						while (true) {
							lblTime.setText(Cal.getTime());
							try {	Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}).start();
			}
		});
	 */
}