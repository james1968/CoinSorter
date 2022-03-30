import java.util.*;
public class  TestCoinSorter {

    public static void main (String args[]) {
        // Initialisation of object with valid coinList
        int [] coinList = {200, 100, 50, 20, 10};
        // Main menu options
        CoinSorter c=new CoinSorter("GBP", 1, 10000, coinList);
        System.out.println("***Coin-Sorter - Main Menu***");
        System.out.println();
        System.out.println("1. Coin Calculator");
        System.out.println("2. Multiple Coin Calculator");
        System.out.println("3. Print Coin List");
        System.out.println("4. Set Details");
        System.out.println("5. Display program configurations");
        System.out.println("6. Quit the program");
        Scanner sc=new Scanner(System.in);
        System.out.println("Please select 1 - 6");
        // Loop for main menu options to loop until option 6, exit is selected
        while (true) {
            int selection = sc.nextInt();
            switch (selection) {
                case 1:
                    int totalCoinValue = c.validateTotalCoinValue();
                    int excludedCoin = c.validateCoin();
                    c.coinCalculator(totalCoinValue, excludedCoin);
                    break;
                case 2:
                    int mTotalCoinValue = c.validateTotalCoinValue();
                    int mExcludedCoin = c.validateExcludedCoin();
                    c.multiCoinCalculator(mTotalCoinValue, mExcludedCoin);
                    break;
                case 3:
                    c.printCoinList(coinList);
                    break;
                // Sub Menu items to loop through until option 4 is selected.
                case 4:
                    System.out.println("***Set Details Sub-Menu***");
                    System.out.println();
                    System.out.println("1 - Set currency");
                    System.out.println("2 - Set minimum coin value");
                    System.out.println("3 - Set maximum coin value");
                    System.out.println("4 - Return to main menu");
                    System.out.println("Please enter an option from 1 - 4");
                    while (true) {
                        int subMenuSelection = sc.nextInt();
                        switch (subMenuSelection) {
                            case 1:
                                System.out.println("Please select a currency");
                                String currency = sc.next();
                                c.setCurrency(currency);
                                break;
                            case 2:
                                System.out.println("Please select minimum coin amount");
                                int minCoin = sc.nextInt();
                                c.setMinCoinIn(minCoin);
                                break;
                            case 3:
                                System.out.println("Please select a maximum coin amount");
                                int maxCoin = sc.nextInt();
                                c.setMaxCoinIn(maxCoin);
                                break;
                            case 4:
                                return;
                            default:
                                System.out.println("Please enter a valid option");
                                break;
                        }
                    }
                case 5:
                    c.displayProgramConfigs();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Please enter a valid option");
            }
        }

    }
}


