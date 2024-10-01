package Singleton;

/**
 * @Author: Tianze An
 * @Date: 2024/06/16/ 21:58
 * @description: Hungry Man Style
 * Static variables create class objects
 */
public class Singleton {
    // private constructor
    private Singleton() {
    }

    // Create an object of this class at the member location
    private static Singleton instance = new Singleton();

    // Provide static methods to obtain the object
    public static Singleton getInstance() {
        return instance;
    }
}
