package Strategy;

/**
 * @Author: Tianze An
 * @Date: 2024/06/17/ 00:29
 * @description:
 */
public class Bicycle implements TravelStrategy{
    @Override
    public void travel() {
        System.out.println("Travel by Bicycle");
    }
}
