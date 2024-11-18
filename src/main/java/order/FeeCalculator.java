package order;

public class FeeCalculator {

    public long calcOrderFee(Order order) {
        long orderFee = order.calcOrderFee();
        if (orderFee < 30000) {
            throw new IllegalArgumentException("최소 주문 금액을 만족하지 못했습니다.");
        }
        return orderFee;
    }
}
