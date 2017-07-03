package gluecoders;

/**
 * The join method allows the current thread to wait for the completion of another.
 *
 * We can also specify a waiting period which will be equal to the maximum time current thread is going to wait for other:
 * - If the other thread gets terminated before the mentioned waiting period, current thread will resume instantly.
 * - If the other thread is not done and the waiting period has expired,
 * current thread will resume resulting both the threads running in parallel.
 *
 * Time need not be precise as it depends upon the underlying OS.
 * Like sleep() join() also responds with {@link InterruptedException} if interrupted clearing the interrupted status
 */
public class Joins implements Runnable {
    @Override
    public void run(){
        for (int i = 0; i < 4; i++) {
            try {
                System.out.println("We are in thread: " + Thread.currentThread().getName());
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " has been interrupted");
            }
        }
        System.out.println("Thread getting terminated: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException{
        System.out.println("This is main thread: " + Thread.currentThread().getName());

        // Create a new thread and start it
        Thread joinThread = new Thread(new Joins());
        joinThread.start();

        for (int i = 0; i < 4; i++) {
            System.out.println("Do something in main thread: " + Thread.currentThread().getName());
        }

        // Wait!!, let's wait until the above thread terminates
        System.out.println("I am going to join with other thread now: " + Thread.currentThread().getName());
        System.out.println("So we need to wait now until that thread terminates: " + Thread.currentThread().getName());
        joinThread.join();
//        joinThread.join(0); // 0 means to wait forever i.e. till the joinThread terminates thus same as .join()

        // We can also define a maximum time to wait for
        // This will wait either till the joinThread gets terminated or 8 second, whichever happens first.
        // Post that this thread will also start and if the other thread didn't terminate, now both of them will run in parallel
//        joinThread.join(8000);

        // Similarly, this will wait either till the joinThread gets terminated or 32 second, whichever happens first
        // Post that this thread will also start and if the other thread didn't terminate, now both of them will run in parallel
//        joinThread.join(32000);

        for (int i = 0; i < 4; i++) {
            System.out.println("Now resume doing that something in main thread: " + Thread.currentThread().getName());
        }
    }
}
