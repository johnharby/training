package com.learn;

import java.lang.management.ManagementFactory;

public class ExampleGarbageCollectorMXBean {
    public static void main(String[] args) {
        // Run 100 GCs
        for (int i = 0; i < 100; i++) {
            System.gc();
        }

        // Print basic information from available beans
        for (final var bean : ManagementFactory.getGarbageCollectorMXBeans()) {
            System.out.println(bean.getName());
            System.out.println("   Count: " + bean.getCollectionCount());
            System.out.println("   Total Time: " + bean.getCollectionTime() + "ms");
            System.out.println("   Average Time: " + (bean.getCollectionTime() / (double)bean.getCollectionCount()) + "ms");
        }
    }
}
