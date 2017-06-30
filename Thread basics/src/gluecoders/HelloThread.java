package gluecoders;

/**
 * An application that creates an instance of Thread must provide the code that will run in that thread.
 * There are two ways to do this: One of which is to subclass a Thread.
 *
 * The Thread class itself implements Runnable, though its run method does nothing.
 * An application can subclass Thread, providing its own implementation of run.
 */
public class HelloThread extends Thread{
    public void run(){
        System.out.println("Hello from a thread!");
    }

    public static void main(String[] args) {
        (new HelloThread()).start();
    }
}
