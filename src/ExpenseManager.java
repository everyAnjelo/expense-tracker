import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    public void printCategorySummary() {
        System.out.println("==== Category Summary ====");

        for (Category c : Category.values()) {
            double total = 0;

            for (Expense e : expenses) {
                if (e.getCategory() == c) {
                    total += e.getAmount();
                }
            }

            System.out.println(c + ": $" + total);
        }
    }
    public void printCategoryBreakdown() {
        double total = calculateTotal();

        if (total == 0) {
            System.out.println("No expenses to analyze.");
            return;
        }

        System.out.println("==== Spending Breakdown ====");

        for (Category c : Category.values()) {
            double categoryTotal = 0;

            for (Expense e : expenses) {
                if (e.getCategory() == c) {
                    categoryTotal += e.getAmount();
                }
            }

            double percentage = (categoryTotal / total) * 100;
            System.out.printf("%s: %.2f%%\n", c, percentage);
        }
    }
    public void printSortedExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }

        ArrayList<Expense> sorted = new ArrayList<>(expenses);

        Collections.sort(sorted, new Comparator<Expense>() {
            @Override
            public int compare(Expense e1, Expense e2) {
                return Double.compare(e2.getAmount(), e1.getAmount());
            }
        });

        System.out.println("==== Expenses (Highest to Lowest) ====");
        for (Expense e : sorted) {
            System.out.println(e);
        }
    }

}