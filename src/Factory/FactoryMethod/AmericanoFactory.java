package Factory.FactoryMethod;

/**
 * @Author: Tianze An
 * @Date: 2024/06/16/ 22:56
 * @description:
 */
public class AmericanoFactory implements CoffeeFactory{
    @Override
    public Coffee createCoffee() {
        return new Americano();
    }
}
