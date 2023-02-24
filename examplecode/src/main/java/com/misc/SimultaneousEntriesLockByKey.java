package com.misc;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

// https://www.baeldung.com/java-acquire-lock-by-key
public class SimultaneousEntriesLockByKey {

    private static final int ALLOWED_THREADS = 2;

    private static ConcurrentHashMap<String, Semaphore> semaphores = new ConcurrentHashMap<String, Semaphore>();

    public void lock(String key) {
        Semaphore semaphore = semaphores.compute(key, (k, v) -> v == null ? new Semaphore(ALLOWED_THREADS) : v);
        semaphore.acquireUninterruptibly();
    }

    public void unlock(String key) {
        Semaphore semaphore = semaphores.get(key);
        semaphore.release();
        if (semaphore.availablePermits() == ALLOWED_THREADS) {
            semaphores.remove(key, semaphore);
        }
    }

    public static void main(String[] args) {
        String key = "key";
        SimultaneousEntriesLockByKey lockByKey = new SimultaneousEntriesLockByKey();
        try {
            lockByKey.lock(key);
            // insert your code here
        } finally { // CRUCIAL
            lockByKey.unlock(key);
        }
    }
}
