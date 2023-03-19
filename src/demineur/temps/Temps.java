package demineur.temps;

import demineur.segment.Segment;

public class Temps implements Runnable {
	
	private Thread thread;
	
	private Segment afficherSegment;
	
	private boolean marche = true;
	private boolean threadSuspendu = false;

	public Temps(Segment compteur) {
		this.afficherSegment = compteur;
	}
	
	public void run() {
		
		while (this.marche) {
			
			try {
				
				Thread.sleep(1000);
				
				// si thread est suspendu on attend
				if (this.threadSuspendu) {
					
					synchronized(this) {
						
						while (this.threadSuspendu) {
							wait();
						}
					}
				}
				
			} catch(java.lang.InterruptedException e) {}
			
			int time = this.afficherSegment.getValeur();
			
			// si on arrive pas à 999, on incremente
			if (marche && (time < 999)) {
				this.afficherSegment.setValeur(time+1);
			}

		}
	}
	
	public void start() {
		
		if (this.thread == null) {
			this.thread = new Thread(this);
		}
		
		this.thread.setPriority(Thread.MAX_PRIORITY);
		this.thread.start();
	}
	
	public void stop() {
		
		if (this.thread != null) {
			this.thread = null;
		}
	}
	
	public void cancel() {
		this.marche = false;
	}
	
	public void suspend() {
		this.threadSuspendu = true;
	}
	
	public synchronized void resume() { 
		this.threadSuspendu = false; 
		notify();
	}
}
