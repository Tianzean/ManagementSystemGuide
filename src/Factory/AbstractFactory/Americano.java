package Factory.AbstractFactory;

/**
 * @Author: Tianze An
 * @Date: 2024/06/16/ 23:24
 * @description:
 */
public class Americano implements Coffee{
    @Override
    public String getName() {
        return "Americano";
    }

    @Override
    public void addMilk() {
        System.out.println("Americano add milk");
    }

    @Override
    public void addSugar() {
        System.out.println("Americano add sugar");
    }
}
