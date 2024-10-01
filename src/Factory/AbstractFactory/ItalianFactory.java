package Factory.AbstractFactory;

/**
 * @Author: Tianze An
 * @Date: 2024/06/16/ 23:25
 * @description:
 */
public class ItalianFactory implements Factory{

    @Override
    public Coffee createCoffee() {
        return new Latte();
    }

    @Override
    public Dessert createDessert() {
        return new Tiramisu();
    }
}
