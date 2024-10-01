package Strategy;

/**
 * @Author: Tianze An
 * @Date: 2024/06/17/ 00:28
 * @description:
 */
public class Train implements TravelStrategy{
    @Override
    public void travel() {
        System.out.println("Travel by Train");
    }
}
