import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ExpenseManager implements Analyzable {

    private ArrayList<Expense> expenses;
    private double budget;

    public ExpenseManager(double budget) {
        this.expenses = new ArrayList<>();
        this.budget = budget;
        loadFromFile();

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
        try (FileWriter fw = new FileWriter("expenses.txt", true)) {
            fw.write(e.getName() + "," + e.getAmount() + "," + e.getCategory() + "\n");
        } catch (IOException ex) {
            System.out.println("Error saving expense.");
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
    private void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("expenses.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                String name = parts[0];
                double amount = Double.parseDouble(parts[1]);
                Category category = Category.valueOf(parts[2]);

                expenses.add(new Expense(name, amount, category));
            }
        } catch (IOException e) {
            System.out.println("No existing file found. Starting fresh.");
        }
    }
}