package ResponsibleChain;

/**
 * @Author: Tianze An
 * @Date: 2024/06/17/ 00:42
 * @description: Responsible Chain is applied in various interceptors.
 * 1. Handler
 * 2. Concrete Handler
 * 3. Client
 */
public class Application {
    public static void main(String[] args) {

        Handler orderValidation = new OrderValidation();
        Handler orderFill = new OrderFill();
        Handler orderAmountCalculate = new OrderAmountCalculate();
        Handler orderCreate = new OrderCreate();

        orderValidation.setNext(orderFill);
        orderFill.setNext(orderAmountCalculate);
        orderAmountCalculate.setNext(orderCreate);

        orderValidation.process(new OrderInfo());
    }
}
