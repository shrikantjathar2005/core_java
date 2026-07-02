public class MyThread extends Thread {

    private int data;

    @Override
    public void run() {

        Thread ct = 
        String tname = Thread.currentThread().getName();

        for (int c = 1; c <= 50; c++)
            System.out.println(tname + " : " + ++data);
    }

    public static void main(String[] args) {
    	
    	MyRunnable mr = new MyRunnable();		
        MyThread t1 = new MyThread(mr, "First");
        MyThread t2 = new MyThread(mr, "Second");
        MyThread t3 = new MyThread(mr, "Third");

        t1.start();
        t2.start();
        t3.start();
        
        
    }
}