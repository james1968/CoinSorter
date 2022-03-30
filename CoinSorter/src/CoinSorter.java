import java.util.Arrays;
import java.util.*;

public class CoinSorter {

    //Class variables
    private String currency;
    private int minCoinIn;
    private int maxCoinIn;
    private int totalCoinValue;
    private int excludedCoin;
    private int exchangeCoin;
    private int [] coinList;

    // Class Constructor
    public CoinSorter(String currency, int minCoinIn, int maxCoinIn, int [] coinListIn) {
        this.currency = currency;
        this.minCoinIn = minCoinIn;
        this.maxCoinIn = maxCoinIn;
        this.coinList = coinListIn;
    }

    // Default constructor as class constructor defined above
    public CoinSorter() {

    }

    // Set methods for class
    public String setCurrency(String currencyIn) {
        currency = currencyIn;
        System.out.println("Currency has been set to: " + currency + ".");
        return "Currency has been set to: " + currency + ".";
    }

    public String setMinCoinIn(int minCoin) {
        minCoinIn = minCoin;
        System.out.println("Minimum coin denomination has been set to: " + minCoin + ".");
        return "Minimum coin denomination has been set to: " + minCoin + ".";
    }

    public String setMaxCoinIn(int maxCoin) {
        maxCoinIn = maxCoin;
        System.out.println("Maximum coin denomination has been set to: " + maxCoin + ".");
        return "Maximum coin denomination has been set to: " + maxCoin + ".";
    }

    // Get methods for class
    public String getCurrency() {
        return currency;
    }

    public int getminCoinIn() {
        return minCoinIn;
    }

    public int getmaxCoinIn() {
        return maxCoinIn;
    }

    // Method to print coin list array,  Note solution to remove [] from array found at "https://stackoverflow.com/questions/10904911/how-to-convert-an-int-array-to-string-with-tostring-method-in-java"
    public String printCoinList(int [] coinListIn) {
        String printString = "The current coin denominations are in circulation: " + Arrays.toString(coinList).replaceAll("\\[|\\]||\\s", "") + ".";
        System.out.println(printString);
        return printString;

    }

    // Method to validate the total amount to be exchanged for either coin calculator or multi-coin calculator
    public int validateTotalCoinValue() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a positive number between " + minCoinIn + " and " + maxCoinIn + " inclusive.");

        int number = sc.nextInt();
        while (number < minCoinIn || number > maxCoinIn) {
            System.out.println("Please enter a positive number between " + minCoinIn + " and " + maxCoinIn + " inclusive.");
            number = sc.nextInt();
        }

        totalCoinValue = number;
        return totalCoinValue;
    }
    // Method to validate denomination for the exchange for the single coin calculator
    public int validateCoin() {
        Scanner sc = new Scanner(System.in);
        while(exchangeCoin == 0) {
            System.out.println("Please enter a valid coin denomination to exchange");

            int number = sc.nextInt();

            for (int i = 0; i < coinList.length; i++) {
                if (number == coinList[i]) {
                    exchangeCoin = number;
                }
            }

        }
        return exchangeCoin;
    }

    // Method to validate the denomination to be excluded for the multi coin calculator
    public int validateExcludedCoin() {
        Scanner sc = new Scanner(System.in);
        while(excludedCoin == 0) {
            System.out.println("Please enter a valid coin to exclude");

            int number = sc.nextInt();

            for (int i = 0; i < coinList.length; i++) {
                if (number == coinList[i]) {
                    excludedCoin = number;
                }
            }
        }
        return excludedCoin;
    }

    /* Method to return the number of coins that will be returned for a total amount along with the remainder.
    E.g. If total amount is 515 and denomination is 50 the method should return "A total of 10 x 50p coins can be exchange, with a remainder of 15p.
    */
    public String coinCalculator(int totalCoinValue, int denomination) {
        int numberCoins = totalCoinValue / denomination;
        int remainder = totalCoinValue % denomination;
        String onePound = "£1s";
        String twoPound = "£2";
        if (denomination == 100) {
            System.out.println("A total of " + numberCoins + " x " + onePound + " can be exchange, with a remainder of " + remainder + "p.");
            return "A total of " + numberCoins + " x " + onePound + " can be exchange, with a remainder of " + remainder + "p.";
        }
        else if (denomination == 200) {
            System.out.println("A total of " + numberCoins + " x " + twoPound + " can be exchange, with a remainder of " + remainder + "p.");
            return "A total of " + numberCoins + " x " + twoPound + " can be exchange, with a remainder of " + remainder + "p.";
        } else {
            System.out.println("A total of " + numberCoins + " x " + denomination + "p coins can be exchange, with a remainder of " + remainder + "p.");
            return "A total of " + numberCoins + " x " + denomination + "p coins can be exchange, with a remainder of " + remainder + "p.";
        }
    }

    /* Method to return number of various denominations to be returned for a total amount and excluding the denomination entered.
    E.g. if total is 55 and excluded denominations is 20 it should return The coins exchanged are: 0 * 200p, 0 * 100p, 1 * 50p, 0 * 10p, with a remainder of 5p.
    */
    public String multiCoinCalculator(int totalCoinValue, int excludedCoin) {
        // The loop is used to create a new revised coinList that excludes the denomination entered by the user.
        int [] revisedCoinList = new int[5];
        for (int i = 0; i < coinList.length; i++) {
            if (coinList[i] != excludedCoin) {
                revisedCoinList[i] = coinList[i];
            }
        }
        // This loop calculates the number of each denomination that will be returned for a given total.
        int numberCoins;
        String answer = "The coins exchanged are: ";
        for (int j = 0; j < revisedCoinList.length; j++) {
            if (revisedCoinList[j] == 0) {
                continue;
            } else {
                numberCoins = totalCoinValue / revisedCoinList[j];

                answer += numberCoins + " * " + revisedCoinList[j] + "p, ";

                totalCoinValue = totalCoinValue % revisedCoinList[j];
            }
        }
        System.out.println(answer + "with a remainder of " + totalCoinValue + "p.");
        return answer + "with a remainder of " + totalCoinValue + "p.";
    }

    // Displays Currency, Minimum Coin and Maximum Coin values.
    public String displayProgramConfigs() {
        String programCcy = "Currency: " + currency;
        String programMinCoin = "Minimum Coin Value: " + minCoinIn;
        String programMaxCoin = "Maximum Coin Value: " + maxCoinIn;
        System.out.println(programCcy + "\n" + programMinCoin + "\n" + programMaxCoin);
        return programCcy + "\n" + programMinCoin + "\n" + programMaxCoin;
    }
}
