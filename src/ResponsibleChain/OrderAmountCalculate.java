package ResponsibleChain;

/**
 * @Author: Tianze An
 * @Date: 2024/06/17/ 00:42
 * @description:
 */
public class OrderAmountCalculate extends Handler{
    @Override
    public void process(OrderInfo order) {
        System.out.println("Step 3: Order Amount Calculate processing.");
        handler.process(order);
    }
}
