package Singleton;

/**
 * @Author: Tianze An
 * @Date: 2024/06/16/ 21:58
 * @description: Hungry Man Style
 * Static variables create class objects
 */
public class Singleton {
    // 1.Prevent to create external new objects to destroying the singleton pattern
    private Singleton() {}
    // 2.Saving singleton objects by private variables
    private static volatile Singleton instance = null;
    // 3.Provides a public method to get a singleton object
    public static Singleton getInstance() {
        if (instance == null) { // First vertify
            synchronized (Singleton.class) {
                if (instance == null) { // Second vertify
                    instance = new Singleton(); 
                }
            }
        }
        return instance;
    }
}
