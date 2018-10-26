package demo;

public class ThreadTest {
	
	public static void main(String[] args) throws InterruptedException {
		PrimeGenerator gen = new PrimeGenerator();
        new Thread(gen).start();
        try{
             Thread.sleep(3000);
        }finally{
             gen.cancel();
        }
	}

}


class PrimeGenerator implements Runnable{

    private boolean cancelled;      

    @Override
    public void run() {
         while(!cancelled){
             System.out.println("Running...");
         }                
    }       

    public void cancel(){cancelled = true;}

}