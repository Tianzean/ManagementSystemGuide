package Factory.SimpleFactory;

/**
 * @Author: Tianze An
 * @Date: 2024/06/16/ 21:58
 * @description: Simple Factory Pattern
 * 1. Abstract Product: describe main features and functions of products
 * 2. Specific Product: implement subclass of abstract product
 * 3. Specific Factory: provide method that creating product
 */
public class CoffeeStore {

    public static void main(String[] args) {
        Coffee coffee = orderCoffee("Americano");
        System.out.println(coffee.getName());
    }

    public static Coffee orderCoffee(String type) {
        SimpleCoffeeFactory factory = new SimpleCoffeeFactory();
        Coffee coffee = factory.createCoffee(type);
        coffee.addMilk();
        coffee.addSugar();
        return coffee;
    }
}
