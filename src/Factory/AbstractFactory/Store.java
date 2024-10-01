package Factory.AbstractFactory;

/**
 * @Author: Tianze An
 * @Date: 2024/06/16/ 23:22
 * @description: Abstract Factory
 * Product Class:
 * 1. Coffee: Americano, Latte
 * 2. Dessert: Brownie, Tiramisu
 * Brand Class:
 * 1. Italian: Latte, Tiramisu
 * 2. American: Americano, Brownie
 */
public class Store {
    public static void main(String[] args) {
        Factory americanFactory = new AmericanFactory();
        Coffee americano = americanFactory.createCoffee();
        Dessert brownie = americanFactory.createDessert();

        americano.addMilk();
        americano.addSugar();
        System.out.println(americano.getName());
        brownie.bake();
        brownie.box();
        System.out.println(brownie.getName());
    }
}
