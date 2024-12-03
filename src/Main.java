interface Payment {
    void pay(int amount);
}

class Cash implements Payment {
    @Override
    public void pay(int amount) {
        System.out.println("Thanh toán " + amount + " bằng tiền mặt.");
    }
}

class CreditCard implements Payment {
    private Cash cash;
    private String cardNumber;
    private int balance;

    public CreditCard(String cardNumber, int balance) {
        this.cardNumber = cardNumber;
        this.balance = balance;
    }

    @Override
    public void pay(int amount) {
        if (checkCardValidity()) {
            if (hasSufficientBalance(amount)) {
                if (cash == null) {
                    cash = new Cash();  // Tạo đối tượng thực hiện giao dịch nếu chưa có
                }
                cash.pay(amount);
            } else {
                System.out.println("Thẻ tín dụng: Số dư không đủ.");
            }
        } else {
            System.out.println("Thẻ tín dụng: Thẻ không hợp lệ.");
        }
    }

    private boolean checkCardValidity() {
        return cardNumber != null && cardNumber.length() == 6;
    }

    private boolean hasSufficientBalance(int amount) {
        return balance >= amount;
    }
}

public class Main {
    public static void main(String[] args) {

        Payment paymentWithCard = new CreditCard("123456", 1000);
        paymentWithCard.pay(500);
        System.out.println();

        Payment paymentWithCard2 = new CreditCard("1234567", 200);
        paymentWithCard2.pay(500);
        System.out.println();

        Payment paymentWithCash = new Cash();
        paymentWithCash.pay(100);
    }
}
