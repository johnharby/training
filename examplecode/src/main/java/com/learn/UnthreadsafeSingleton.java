package com.learn;

// This class has a race condition in it.
public class UnthreadsafeSingleton {

    private static UnthreadsafeSingleton simulator = null;
    // invariant: there should never be more than one PinballSimulator
    //            object created

    private UnthreadsafeSingleton() {
        System.out.println("created a PinballSimulator object");
    }

    // factory method that returns the sole PinballSimulator object,
    // creating it if it doesn't exist - this needs to be synchronized
    public static UnthreadsafeSingleton getInstance() {
        if (simulator == null) {
            simulator = new UnthreadsafeSingleton();
        }
        return simulator;
    }

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            UnthreadsafeSingleton.getInstance();});
        t.start();
        Thread t2 = new Thread(() -> {
            UnthreadsafeSingleton.getInstance();});
        t2.start();
    }
}
