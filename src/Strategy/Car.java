package Strategy;

/**
 * @Author: Tianze An
 * @Date: 2024/06/17/ 00:28
 * @description:
 */
public class Car implements TravelStrategy{
    @Override
    public void travel() {
        System.out.println("Travel by Car");
    }
}
