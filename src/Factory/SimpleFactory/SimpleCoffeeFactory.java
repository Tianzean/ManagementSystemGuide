package Factory.SimpleFactory;

/**
 * @Author: Tianze An
 * @Date: 2024/06/16/ 21:58
 * @description: Specific Factory
 */
public class SimpleCoffeeFactory {

    public static Coffee createCoffee(String type) {
        Coffee coffee = null;
        if ("Americano".equals(type)) {
            coffee = new Americano();
        } else if ("Latte".equals(type)) {
            coffee = new Latte();
        }
        return coffee;
    }
}
