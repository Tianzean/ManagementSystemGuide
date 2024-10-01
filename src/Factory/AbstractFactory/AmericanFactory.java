package Factory.AbstractFactory;

/**
 * @Author: Tianze An
 * @Date: 2024/06/16/ 23:24
 * @description:
 */
public class AmericanFactory implements Factory{
    @Override
    public Coffee createCoffee() {
        return new Americano();
    }

    @Override
    public Dessert createDessert() {
        return new Brownie();
    }
}
