package ResponsibleChain;

/**
 * @Author: Tianze An
 * @Date: 2024/06/17/ 00:42
 * @description:
 */
public abstract class Handler {

    protected Handler handler;

    public void setNext(Handler handler){
        this.handler = handler;
    }

    public abstract void process(OrderInfo order);
}
