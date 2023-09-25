import java.util.Scanner;
public class ATM {
    private BankAccount account;
    public ATM(BankAccount account) {
        this.account = account;
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check balance");
            System.out.println("4. Quit");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter the amount you want to withdraw:");
                    double amount = scanner.nextDouble();
                    account.withdraw(amount);
                    break;
                case 2:
                    System.out.println("Enter the amount you want to deposit:");
                    amount = scanner.nextDouble();
                    account.deposit(amount);
                    break;
                case 3:
                    account.checkBalance();
                    break;
                case 4:
                    System.out.println("Thank you for using our ATM.");
                    return;
                default:
                    System.out.println("Invalid option. Please choose a number between 1 and 4.");
            }
        }
    }
    public static void main(String[] args) {
        BankAccount account = new BankAccount(123456, 1111);
        ATM atm = new ATM(account);
        atm.run();
    }
}