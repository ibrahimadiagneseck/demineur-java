package demineur.temps;

import demineur.segment.Segment;

public class Temps implements Runnable {
  Thread thread;
  Segment aff;
  boolean marche=true;
  boolean threadSuspended=false;

  public Temps(Segment compteur) {
    aff=compteur;
  }
  public void run() {
    while (marche) {
      try {
        Thread.sleep(1000);//On attend 1 seconde
        if (threadSuspended) {
          synchronized(this) {
            while (threadSuspended) //On attend que le thread soit suspendu
              wait();
          }
        }
      }
      catch(java.lang.InterruptedException e) {}
      int time = aff.getValeur();
      if (marche && time<999) {//si le compteur n'est pas arrêté au bout de 999 secondes
        aff.setValeur(time+1);
      }

    }
  }
  public void start() {
    if (thread==null) thread = new Thread(this);
    thread.setPriority(Thread.MAX_PRIORITY);
    thread.start();
  }
  public void stop() {
    if (thread!=null) thread = null;
  }
  public void cancel() {
    marche=false;
  }
  public void suspend() {
    threadSuspended=true;
  }
  public synchronized void resume() { 
    threadSuspended=false; 
    notify();
  }
}
