import java.util.Scanner;

public class RunMenu {

    private boolean isRunning = true;
    private Scanner scanner = new Scanner(System.in);
    private ExpenseManager manager;

    public RunMenu() {
        manager = new ExpenseManager(100); // budget
    }

    public void runMenu() {
        while (isRunning) {
            System.out.println("==== Expense Tracker ====");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Total Spending");
            System.out.println("4. View Spending by Category");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();

                    System.out.println("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Select category: FOOD, TRANSPORT, ENTERTAINMENT, BILLS");
                    String categoryInput = scanner.nextLine().toUpperCase();

                    Category category = Category.valueOf(categoryInput);

                    Expense expense = new Expense(name, amount, category);
                    manager.addExpense(expense);

                    System.out.println("Expense added successfully!");
                    break;

                case 2:
                    manager.printAllExpenses();
                    break;

                case 3:
                    System.out.println("Total: $" + manager.calculateTotal());
                    break;

                case 4:
                    System.out.println("Enter category: ");
                    String catInput = scanner.nextLine().toUpperCase();
                    Category cat = Category.valueOf(catInput);

                    System.out.println("Total: $" + manager.getTotalByCategory(cat));
                    break;

                case 5:
                    isRunning = false;
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Please enter a valid number (1-5)");
            }
        }
    }
}