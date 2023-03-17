package demineur.temps;

import demineur.segment.Segment;

public class Temps implements Runnable {
	
	private Thread thread;
	
	private Segment afficherSegment;
	
	private boolean marche = true;
	private boolean threadSuspendu = false;

	public Temps(Segment compteur) {
		afficherSegment = compteur;
	}
	
	public void run() {
		
		while (marche) {
			
			try {
				
				Thread.sleep(1000);
				
				// si thread est suspendu on attend
				if (threadSuspendu) {
					
					synchronized(this) {
						
						while (threadSuspendu) {
							wait();
						}
					}
				}
				
			} catch(java.lang.InterruptedException e) {}
			
			int time = afficherSegment.getValeur();
			
			// si on arrive pas à 999, on incremente
			if (marche && (time < 999)) {
				afficherSegment.setValeur(time+1);
			}

		}
	}
	
	public void start() {
		
		if (thread==null) {
			thread = new Thread(this);
		}
		
		thread.setPriority(Thread.MAX_PRIORITY);
		thread.start();
	}
	
	public void stop() {
		
		if (thread!=null) {
			thread = null;
		}
	}
	
	public void cancel() {
		marche=false;
	}
	
	public void suspend() {
		threadSuspendu=true;
	}
	
	public synchronized void resume() { 
		threadSuspendu=false; 
		notify();
	}
}
