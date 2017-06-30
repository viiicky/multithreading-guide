package gluecoders;

/**
 * Thread.sleep static method causes the current thread to suspend execution for the specified period.
 * This makes processor time available to the other threads of an application
 * or other applications that might be running on a computer system.
 *
 * It can be used for pacing or to wait for another thread that needs time.
 * We cannot assume that invoking sleep will suspend the thread for precisely the time period specified:
 * - because they are limited by the facilities provided by the underlying OS.
 * - because the sleep period can be terminated by interrupts.
 *
 */
public class SleepMessages {
    public static void main(String[] args) throws InterruptedException {
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lamb eats ivy",
                "A kid will eat ivy too"
        };

        for (int i=0; i<importantInfo.length; i++){
            // Pause for 4 seconds
            Thread.sleep(4000); // Pausing the current thread i.e. main thread here

            // Print a message
            System.out.println(importantInfo[i]);
        }
    }
}
