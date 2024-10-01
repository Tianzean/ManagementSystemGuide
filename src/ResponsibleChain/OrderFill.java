package ResponsibleChain;

/**
 * @Author: Tianze An
 * @Date: 2024/06/17/ 00:43
 * @description:
 */
public class OrderFill extends Handler{
    @Override
    public void process(OrderInfo order) {
        System.out.println("Step 2: Order Fill processing.");
        handler.process(order);
    }
}
