package Strategy;

/**
 * @Author: Tianze An
 * @Date: 2024/06/17/ 00:28
 * @description: Strategy Pattern
 * 1. Abstract Strategy: interface of concrete strategy
 * 2. Concrete Strategy: implement specific algorithm
 * 3. Context: quote strategy
 */
public class TravelContext {

    private TravelStrategy travelStrategy;

    public TravelContext(TravelStrategy travelStrategy){
        this.travelStrategy = travelStrategy;
    }

    public void selectTravel(){
        this.travelStrategy.travel();
    }

    public static void main(String[] args) {
        TravelContext travelContext = new TravelContext(new Aircraft());

        travelContext.selectTravel();
    }
}
