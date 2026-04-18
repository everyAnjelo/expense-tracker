import java.util.ArrayList;

public class ExpenseManager implements Analyzable {

    private ArrayList<Expense> expenses;
    private double budget;

    public ExpenseManager(double budget) {
        this.expenses = new ArrayList<>();
        this.budget = budget;
    }

    @Override
    public double calculateTotal() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.getAmount();
        }
        return total;
    }

    public void addExpense(Expense e) {
        expenses.add(e);

        double total = calculateTotal();

        if (total > budget) {
            System.out.println("⚠️ Warning: Budget exceeded!");
        }
    }

    public double getTotalByCategory(Category c) {
        double total = 0;
        for (Expense e : expenses) {
            if (e.getCategory() == c) {
                total += e.getAmount();
            }
        }
        return total;
    }

    public void printAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        System.out.println("---- Expense List ----");
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }
}