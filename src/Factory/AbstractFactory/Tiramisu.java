package Factory.AbstractFactory;

/**
 * @Author: Tianze An
 * @Date: 2024/06/16/ 23:26
 * @description:
 */
public class Tiramisu implements Dessert{
    @Override
    public String getName() {
        return "Tiramisu";
    }

    @Override
    public void bake() {
        System.out.println("Tiramisu is baking");
    }

    @Override
    public void box() {
        System.out.println("Tiramisu is boxing");
    }
}
