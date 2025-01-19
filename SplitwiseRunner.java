import SplitwaiseUtils.*;
import SplitwaiseUtils.SplitType.EqualSplit;
import SplitwaiseUtils.SplitType.PercentSplit;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SplitwiseRunner {
    public static void main(String[] args) {
        SplitwiseService splitwiseService = SplitwiseService.getInstance();

        // Create users
        User user1 = new User("1", "Vishal", "vishal@example.com");
        User user2 = new User("2", "Tushar", "tushar@example.com");
        User user3 = new User("3", "Om", "om@example.com");

        splitwiseService.addUser(user1);
        splitwiseService.addUser(user2);
        splitwiseService.addUser(user3);

        // Create a group
        Group group = new Group("1", "Apartment");
        group.addMember(user1);
        group.addMember(user2);
        group.addMember(user3);

        splitwiseService.addGroup(group);

        // Add an expense
        Expense expense = new Expense("1", 300.0, "Rent", user1);
        EqualSplit equalSplit1 = new EqualSplit(user1);
        EqualSplit equalSplit2 = new EqualSplit(user2);
        PercentSplit percentSplit = new PercentSplit(user3, 20.0);

        expense.addSplit(equalSplit1);
        expense.addSplit(equalSplit2);
        expense.addSplit(percentSplit);

        splitwiseService.addExpense(group.getId(), expense);

        // Settle balances
        splitwiseService.settleBalance(user1.getId(), user2.getId());
        splitwiseService.settleBalance(user1.getId(), user3.getId());

        // Print user balances
        for (User user : Arrays.asList(user1, user2, user3)) {
            System.out.println("User: " + user.getName());
            for (Map.Entry<String, Double> entry : user.getBalances().entrySet()) {
                System.out.println("  Balance with " + entry.getKey() + ": " + entry.getValue());
            }
        }

        // List the transactions corresponding to a user
        for (User user : Arrays.asList(user1, user2, user3)) {
            List<Transaction> transactionList = splitwiseService.getUserTransactions(user.getId());
            System.out.println("User: " + user.getName());
            System.out.println("Transactions: ");
            for(Transaction transaction : transactionList) {
                System.out.println("Id: " + transaction.getId() + ", Sender: "
                        + transaction.getSender().getName() + ", Receiver: "
                        + transaction.getReceiver().getName() + ", Amount: "
                        + transaction.getAmount());
            }
        }
    }
}
