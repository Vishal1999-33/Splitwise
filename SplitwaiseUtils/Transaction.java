package SplitwaiseUtils;

public class Transaction {
    private final String id;
    private final User sender;
    private final User receiver;
    private final double amount;

    public String getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }

    public Transaction(String id, User sender, User receiver, double amount) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }
}
