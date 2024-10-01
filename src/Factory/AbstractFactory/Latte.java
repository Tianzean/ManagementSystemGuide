package Factory.AbstractFactory;

/**
 * @Author: Tianze An
 * @Date: 2024/06/16/ 23:24
 * @description:
 */
public class Latte implements Coffee{
    @Override
    public String getName() {
        return "Latte";
    }

    @Override
    public void addMilk() {
        System.out.println("Latte add milk");
    }

    @Override
    public void addSugar() {
        System.out.println("Latte add sugar");
    }
}
