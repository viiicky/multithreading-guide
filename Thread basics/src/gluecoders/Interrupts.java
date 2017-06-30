package gluecoders;

/**
 * Interrupt is an indication to a thread that it should stop what it is doing and do something else.
 * We decide, how the thread responds to interruptions, but commonly we terminate the interrupted thread.
 *
 * To interrupt a thread, we call interrupt() on it.
 * Apart from the interruption of a thread from an outside thread,
 * it is a perfectly valid Java code if we are interrupting a thread from within it.
 * However, to actually do this we might need to create a thread by subclassing the {@link Thread},
 * so that we have access to the interrupt() from within the thread.
 *
 * Also calling the interrupt() will not guarantee the interruption of the thread on which it is called upon.
 * The interrupted thread should have relevant code to actually catch the interruption signal, and then proceed as wanted:
 *
 * - so if the thread to be interrupted invokes a method that throws {@link InterruptedException}, the call to that method
 * can be made to handle the exception and thus control the behavior of the thread on interruption.
 * The example below does the same thing. The thread implemented frequently calls sleep() that throws {@link InterruptedException}
 * if interrupt() is being called upon the thread while it is sleeping. It catches the exception and then define the behavior.
 *
 * - TODO: discuss here the isInterrupted()
 *
 * TODO: try calling the interrupt() below sleep from within a thread,
 * it should not do anything, because the interrupt() will be called once the sleep has been done. Let's check later
 *
 */
public class Interrupts implements Runnable {
    @Override
    public void run() {
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lamb eats ivy",
                "A kid will eat ivy too"
        };

        for (int i = 0; i < importantInfo.length; i++) {
            // Pause for 4 seconds
            try {
                Thread.sleep(4000); // Pausing the current thread i.e. main thread here
            } catch (InterruptedException e) {
                // here we can define how this thread will respond to interruptions

                // we can be rigid and do not care about interruption like below :P
                /*System.out.println("Seems like I have been interrupted.");
                System.out.println("OK! This is my way of handling interruptions.");
                System.out.println("I do not return from here.");
                System.out.println("Even if someone has interrupted me, I will just print this stuff.");
                System.out.println("And continue doing what I was doing. I will not use return statement.");
                System.out.println("**** OFF!");*/

                // or we can just respect the interruption and terminate ourselves :-|
                System.out.println("Seems like I have been interrupted.");
                System.out.println("No more messages. Terminating myself.");
                return;
            }

            // Print a message
            System.out.println(importantInfo[i] + " Current Thread: " + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Start the above thread
        Thread interruptsThread = new Thread(new Interrupts());
        interruptsThread.start();

        // Interrupt the above thread from this(main) thread
        System.out.println("Interrupting the above thread after waiting for 8 seconds. Current Thread: " +
                Thread.currentThread().getName());
        Thread.sleep(8000);
        // this is how we send interrupt. interruptsThread is the thread here we want to interrupt
        interruptsThread.interrupt();
    }
}
