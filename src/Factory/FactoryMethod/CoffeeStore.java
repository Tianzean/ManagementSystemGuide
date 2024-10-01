package Factory.FactoryMethod;

/**
 * @Author: Tianze An
 * @Date: 2024/06/16/ 22:40
 * @description: Factory Method
 * 1. Abstract Factory: interface of creating product.
 * 2. Concrete Factory: implement abstract method of abstract product.
 * 3. Abstract Product: describe main features and functions of products.
 * 4. Concrete Product: implement interface of Abstract Product and create by Concrete Factory.
 */
public class CoffeeStore {

    public static void main(String[] args) {
        CoffeeStore coffeeStore = new CoffeeStore(new LatteFactory());
        Coffee Latte = coffeeStore.orderCoffee();
        System.out.println(Latte);
    }

    private CoffeeFactory coffeeFactory;

    public CoffeeStore(CoffeeFactory coffeeFactory){
        this.coffeeFactory = coffeeFactory;
    }

    public Coffee orderCoffee(){
        Coffee coffee = coffeeFactory.createCoffee();
        coffee.addMilk();
        coffee.addSugar();
        return coffee;
    }
}
