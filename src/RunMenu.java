import java.util.Scanner;

public class RunMenu {

    private boolean isRunning = true;
    private Scanner scanner = new Scanner(System.in);
    private ExpenseManager manager;

    public RunMenu() {
        manager = new ExpenseManager(100);
    }

    public void runMenu() {
        while (isRunning) {
            System.out.println("\n==== Expense Tracker ====");
            System.out.println("1. Add Expense");
            System.out.println("2. View All Expenses");
            System.out.println("3. View Total Spending");
            System.out.println("4. View Spending by Category");
            System.out.println("5. Exit");

            int choice = readInt();

            switch (choice) {

                case 1:
                    System.out.println("Enter name: ");
                    String name = scanner.nextLine();

                    System.out.println("Enter amount: ");
                    double amount = readDouble();

                    Category category = readCategory();

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
                    Category cat = readCategory();
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

    // 🔹 Safe integer input
    private int readInt() {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number:");
            }
        }
    }

    // 🔹 Safe double input
    private double readDouble() {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine());
                return value;
            } catch (Exception e) {
                System.out.println("Invalid amount. Please enter a valid number:");
            }
        }
    }

    // 🔹 Safe category input
    private Category readCategory() {
        while (true) {
            System.out.println("Select category: FOOD, TRANSPORT, ENTERTAINMENT, BILLS");
            String input = scanner.nextLine().toUpperCase();

            try {
                return Category.valueOf(input);
            } catch (Exception e) {
                System.out.println("Invalid category. Try again.");
            }
        }
    }
}