package Factory.AbstractFactory;

/**
 * @Author: Tianze An
 * @Date: 2024/06/16/ 23:26
 * @description:
 */
public class Brownie implements Dessert{
    @Override
    public String getName() {
        return "Brownie";
    }

    @Override
    public void bake() {
        System.out.println("Brownie is baking");
    }

    @Override
    public void box() {
        System.out.println("Brownie is boxing");
    }
}
