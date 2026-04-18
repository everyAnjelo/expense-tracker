public class Main {
    public static void main(String[] args) {

        // 1. Create ExpenseManager with budget = 100
        ExpenseManager manager = new ExpenseManager(100);

        // 2. Add expenses
        manager.addExpense(new Expense("Lunch", 15, Category.FOOD));
        manager.addExpense(new Expense("Bus", 5, Category.TRANSPORT));
        manager.addExpense(new Expense("Movie", 25, Category.ENTERTAINMENT));
        manager.addExpense(new Expense("Electricity Bill", 70, Category.BILLS)); // should trigger warning

        // Bonus test
        manager.addExpense(new Expense("Candy", 1, Category.FOOD));

        // 3. Print all expenses
        manager.printAllExpenses();

        // 4. Print total spending
        System.out.println("\nTotal Spending: $" + manager.calculateTotal());

        // 5. Print total for one category
        System.out.println("FOOD Total: $" + manager.getTotalByCategory(Category.FOOD));
    }
}