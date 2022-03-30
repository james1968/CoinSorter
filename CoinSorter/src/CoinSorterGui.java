import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class CoinSorterGui extends Application {

    @Override
    public void start(Stage primaryStage) {
        int [] coinList = {200, 100, 50, 20, 10};
        // Main menu options
        CoinSorter c=new CoinSorter("GBP", 1, 10000, coinList);

        // create and configure text fields for input
        TextField optionField = new TextField();
        optionField.setMaxWidth(50);

        // create and configure a non-editable text area to display the results
        Label menuLabel = new Label("Menu Options");
        menuLabel.setTextFill(Color.BLACK);
        menuLabel.setFont(Font.font("Arial", 18));
        Label menuLabel1 = new Label("1. Coin Calculator");
        menuLabel1.setTextFill(Color.BLACK);
        menuLabel1.setFont(Font.font("Arial", 14));
        Label menuLabel2 = new Label("2. Multi Coin Calculator");
        menuLabel2.setTextFill(Color.BLACK);
        menuLabel2.setFont(Font.font("Arial", 14));
        Label menuLabel3 = new Label("3. Print Coin List");
        menuLabel3.setTextFill(Color.BLACK);
        menuLabel3.setFont(Font.font("Arial", 14));
        Label menuLabel4 = new Label("4. Set Details");
        menuLabel4.setTextFill(Color.BLACK);
        menuLabel4.setFont(Font.font("Arial", 14));
        Label menuLabel5 = new Label("5. Display program configurations");
        menuLabel5.setTextFill(Color.BLACK);
        menuLabel5.setFont(Font.font("Arial", 14));
        Label menuLabel6 = new Label("6. Exit");
        menuLabel6.setTextFill(Color.BLACK);
        menuLabel6.setFont(Font.font("Arial", 14));

        // create and configure a non-editable text area to display the results
        TextArea display = new TextArea();
        display.setEditable(false);
        display.setWrapText(true);
        display.setMinSize(250,50);
        display.setMaxSize(250,100);

        // create and configure Labels for the text fields
        Label optionLabel = new Label("Option");
        optionLabel.setTextFill(Color.BLACK);
        optionLabel.setFont(Font.font("Arial", 16));

        // create and configure a button to perform the calculations
        Button calculateButton = new Button();
        calculateButton.setText("Enter");
        calculateButton.setOnAction(e ->  {
            if (optionField.getText().isEmpty()) {
                display.setText("Please enter a valid option");
            }

            if (optionField.getText().equals(Integer.toString(1))) {
                createCoinCalculatorStage().show();
            }

            else if (optionField.getText().equals(Integer.toString(2))) {
                multiCoinCalculatorStage().show();
            }

            else if (optionField.getText().equals(Integer.toString(3))) {

                display.setText(c.printCoinList(coinList));
            }

            else if (optionField.getText().equals(Integer.toString(4))) {

                setDetailsMenuStage().show();
            }

            else if (optionField.getText().equals(Integer.toString(5))) {

                display.setText(c.displayProgramConfigs());
            }

            else if (optionField.getText().equals(Integer.toString(6))) {

                primaryStage.close();
            }

            else {
                display.setText("Please enter a value from 1 to 6.");
            }
        });

        // create and configure an HBox for the labels and text inputs
        HBox inputComponents = new HBox(10);
        inputComponents.setAlignment(Pos.CENTER);
        inputComponents.getChildren().addAll(optionLabel, optionField);

        // create and configure a vertical container to hold all the components
        VBox root = new VBox(25);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(menuLabel, menuLabel1, menuLabel2, menuLabel3, menuLabel4, menuLabel5, menuLabel6, inputComponents, calculateButton, display);

        // create a new scene and add it to the stage
        Scene scene = new Scene(root, 450, 650);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Menu");
        primaryStage.show();

    }


    private static Stage createCoinCalculatorStage() {
        int [] coinList = {200, 100, 50, 20, 10};
        CoinSorter c=new CoinSorter("GBP", 1, 10000, coinList);

        Stage coinCalcStage = new Stage();

        Label menuLabel = new Label("Coin Calculator");
        menuLabel.setTextFill(Color.BLACK);
        menuLabel.setFont(Font.font("Arial", 18));

        Label subMenuLabel = new Label("Please enter the Total value of coins and the denomination");
        menuLabel.setTextFill(Color.BLACK);
        menuLabel.setFont(Font.font("Arial", 18));

        // create and configure text fields for input
        TextField totalField = new TextField();
        totalField.setMaxWidth(70);

        TextField denomField = new TextField();
        denomField.setMaxWidth(70);

        // create and configure Labels for the text fields
        Label totalLabel = new Label("Total");
        totalLabel.setTextFill(Color.BLACK);
        totalLabel.setFont(Font.font("Arial", 16));

        // create and configure Labels for the text fields
        Label denomLabel = new Label("Denomination");
        denomLabel.setTextFill(Color.BLACK);
        denomLabel.setFont(Font.font("Arial", 16));

        // create and configure a non-editable text area to display the results
        TextArea display = new TextArea();
        display.setEditable(false);
        display.setWrapText(true);
        display.setMinSize(250,150);
        display.setMaxSize(250,150);

        // create and configure a button to perform the the coin calculator method including validation of the total ammount.
        Button calculateButton = new Button();
        calculateButton.setText("Enter");
        calculateButton.setOnAction(e ->  {
            if (totalField.getText().isEmpty() || denomField.getText().isEmpty()) {
                display.setText("Please enter a valid option");
            }

            int minCoin = c.getminCoinIn();
            int maxCoin = c.getmaxCoinIn();

            try {
                Integer.parseInt(totalField.getText());
                Integer.parseInt(denomField.getText());

            } catch (NumberFormatException ex) {

                display.setText("Please enter a number.");
            }

            int total = Integer.parseInt(totalField.getText());
            int denomIn = Integer.parseInt(denomField.getText());

            if (total < minCoin) {
                display.setText("Please enter a Total value greater than " + minCoin + ".");
            }

            else if (total > maxCoin) {
                display.setText("Please enter a Total value less than " + maxCoin + ".");
            }

            else {
                display.setText(c.coinCalculator(total, denomIn));
            }
        });

        // create and configure an HBox for the labels and text inputs
        HBox inputComponents = new HBox(10);
        inputComponents.setAlignment(Pos.CENTER);
        inputComponents.getChildren().addAll(totalLabel, totalField, denomLabel, denomField);

        // create and configure an VBox for the menu labels, HBox components, buttons and display text
        VBox root = new VBox(25);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(menuLabel, subMenuLabel, inputComponents, calculateButton, display);

        Scene sceneCoinCalculator = new Scene(root, 500, 400);
        coinCalcStage.setScene(sceneCoinCalculator);
        coinCalcStage.setTitle("Coin Calculator");
        coinCalcStage.show();


        return coinCalcStage;
    }

    private static Stage multiCoinCalculatorStage() {
        int [] coinList = {200, 100, 50, 20, 10};
        CoinSorter c=new CoinSorter("GBP", 1, 10000, coinList);

        Stage multiCalcStage = new Stage();

        Label menuLabel = new Label("Multi Coin Calculator");
        menuLabel.setTextFill(Color.BLACK);
        menuLabel.setFont(Font.font("Arial", 18));

        Label subMenuLabel = new Label("Please enter the Total value of coins and the denomination");
        menuLabel.setTextFill(Color.BLACK);
        menuLabel.setFont(Font.font("Arial", 18));

        // create and configure text fields for input
        TextField totalField = new TextField();
        totalField.setMaxWidth(70);

        TextField denomField = new TextField();
        denomField.setMaxWidth(70);

        // create and configure Labels for the text fields
        Label totalLabel = new Label("Total");
        totalLabel.setTextFill(Color.BLACK);
        totalLabel.setFont(Font.font("Arial", 16));

        // create and configure Labels for the text fields
        Label denomLabel = new Label("Denomination");
        denomLabel.setTextFill(Color.BLACK);
        denomLabel.setFont(Font.font("Arial", 16));

        // create and configure a non-editable text area to display the results
        TextArea display = new TextArea();
        display.setEditable(false);
        display.setWrapText(true);
        display.setMinSize(250,150);
        display.setMaxSize(250,150);

        // create and configure a button to perform the multi coin calculator method
        Button calculateButton = new Button();
        calculateButton.setText("Enter");
        calculateButton.setOnAction(e ->  {
            if (totalField.getText().isEmpty() || denomField.getText().isEmpty()) {
                display.setText("Please enter a valid option");
            }

            int minCoin = c.getminCoinIn();
            int maxCoin = c.getmaxCoinIn();
            try {
                Integer.parseInt(totalField.getText());
                Integer.parseInt(denomField.getText());

            } catch (NumberFormatException ex) {

                display.setText("Please enter a number.");
            }


            int total = Integer.parseInt(totalField.getText());
            int denomIn = Integer.parseInt(denomField.getText());

            if (total < minCoin) {
                display.setText("Please enter a Total value greater than " + minCoin + ".");
            }

            else if (total > maxCoin) {
                display.setText("Please enter a Total value less than " + maxCoin + ".");
            }

            else {
                display.setText(c.multiCoinCalculator(total, denomIn));
            }

        });

        // create and configure an HBox for the labels and text inputs
        HBox inputComponents = new HBox(10);
        inputComponents.setAlignment(Pos.CENTER);
        inputComponents.getChildren().addAll(totalLabel, totalField, denomLabel, denomField);

        // create and configure an VBox for the menu labels, HBox components, buttons and display text
        VBox root = new VBox(25);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(menuLabel, subMenuLabel, inputComponents, calculateButton, display);

        Scene sceneCoinCalculator = new Scene(root, 500, 400);
        multiCalcStage.setScene(sceneCoinCalculator);
        multiCalcStage.setTitle("Coin Calculator");
        multiCalcStage.show();


        return multiCalcStage;
    }

    private static Stage setDetailsMenuStage() {
        int [] coinList = {200, 100, 50, 20, 10};
        CoinSorter c=new CoinSorter("GBP", 1, 10000, coinList);

        Stage setDetailsStage = new Stage();

        Label menuLabel = new Label("Set Details Menu");
        menuLabel.setTextFill(Color.BLACK);
        menuLabel.setFont(Font.font("Arial", 18));
        Label menuLabel1 = new Label("1. Set Currency");
        menuLabel1.setTextFill(Color.BLACK);
        menuLabel1.setFont(Font.font("Arial", 14));
        Label menuLabel2 = new Label("2. Set Minimum Coin Value");
        menuLabel2.setTextFill(Color.BLACK);
        menuLabel2.setFont(Font.font("Arial", 14));
        Label menuLabel3 = new Label("3. Set Maximum Coin Value");
        menuLabel3.setTextFill(Color.BLACK);
        menuLabel3.setFont(Font.font("Arial", 14));
        Label menuLabel4 = new Label("4. Return to Main Menu");
        menuLabel4.setTextFill(Color.BLACK);
        menuLabel4.setFont(Font.font("Arial", 14));

        // create and configure a non-editable text area to display the results
        TextArea display = new TextArea();
        display.setEditable(false);
        display.setWrapText(true);
        display.setMinSize(250,50);
        display.setMaxSize(250,50);

        // create and configure text fields for input
        TextField optionsField = new TextField();
        optionsField.setMaxWidth(70);

        // create and configure Labels for the text fields
        Label optionsLabel = new Label("Option");
        optionsLabel.setTextFill(Color.BLACK);
        optionsLabel.setFont(Font.font("Arial", 14));

        // create and configure a button to allow options to be selected
        Button calculateButton = new Button();
        calculateButton.setText("Enter");
        calculateButton.setOnAction(e ->  {
            if (optionsField.getText().isEmpty()) {
                display.setText("Please enter a valid option");
            }

            if (optionsField.getText().equals(Integer.toString(1))) {
                setCurrencyStage().show();
            }

            else if (optionsField.getText().equals(Integer.toString(2))) {
                setMinCoinStage().show();
            }

            else if (optionsField.getText().equals(Integer.toString(3))) {
                setMaxCoinStage().show();
            }

            else if (optionsField.getText().equals(Integer.toString(4))) {
                setDetailsStage.close();
            }

            else {
                display.setText("Please enter a value from 1 to 4.");
            }
        });

        // create and configure an HBox for the labels and text inputs
        HBox inputComponents = new HBox(10);
        inputComponents.setAlignment(Pos.CENTER);
        inputComponents.getChildren().addAll(optionsLabel, optionsField);

        // create and configure an VBox for the menu labels, HBox components, buttons and display text
        VBox root = new VBox(25);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(menuLabel, menuLabel1, menuLabel2, menuLabel3, menuLabel4, inputComponents, calculateButton, display);

        Scene sceneCoinCalculator = new Scene(root, 400, 550);
        setDetailsStage.setScene(sceneCoinCalculator);
        setDetailsStage.setTitle("Coin Calculator");
        setDetailsStage.show();


        return setDetailsStage;
    }

    private static Stage setCurrencyStage() {
        int [] coinList = {200, 100, 50, 20, 10};
        CoinSorter c=new CoinSorter("GBP", 1, 10000, coinList);

        Stage setCurrencyStage = new Stage();

        Label menuLabel = new Label("Set Currency");
        menuLabel.setTextFill(Color.BLACK);
        menuLabel.setFont(Font.font("Arial", 18));

        // create and configure a non-editable text area to display the results
        TextArea display = new TextArea();
        display.setEditable(false);
        display.setWrapText(true);
        display.setMinSize(250,50);
        display.setMaxSize(250,50);

        // create and configure text fields for input
        TextField optionsField = new TextField();
        optionsField.setMaxWidth(70);

        // create and configure Labels for the text fields
        Label optionsLabel = new Label("Currency");
        optionsLabel.setTextFill(Color.BLACK);
        optionsLabel.setFont(Font.font("Arial", 14));

        // create and configure a button to set currency
        Button calculateButton = new Button();
        calculateButton.setText("Enter");
        calculateButton.setOnAction(e ->  {
            if (optionsField.getText().isEmpty()) {
                display.setText("Please enter a valid option");
            }

            String currencyIn = optionsField.getText();

            display.setText(c.setCurrency(currencyIn));
        });

        // create and configure a button to exit the screen
        Button exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setOnAction(e -> {
            setCurrencyStage.close();
        });

        // create and configure an HBox for the labels and text inputs
        HBox inputComponents = new HBox(10);
        inputComponents.setAlignment(Pos.CENTER);
        inputComponents.getChildren().addAll(optionsLabel, optionsField);

        VBox root = new VBox(25);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(menuLabel, inputComponents, calculateButton, display, exitButton);

        Scene sceneCoinCalculator = new Scene(root, 300, 350);
        setCurrencyStage.setScene(sceneCoinCalculator);
        setCurrencyStage.setTitle("Set Currency");
        setCurrencyStage.show();


        return setCurrencyStage;
    }

    private static Stage setMinCoinStage() {
        int [] coinList = {200, 100, 50, 20, 10};
        CoinSorter c=new CoinSorter("GBP", 1, 10000, coinList);

        Stage setMinCoinStage = new Stage();

        Label menuLabel = new Label("Minimum Coin Setting");
        menuLabel.setTextFill(Color.BLACK);
        menuLabel.setFont(Font.font("Arial", 18));

        // create and configure a non-editable text area to display the results
        TextArea display = new TextArea();
        display.setEditable(false);
        display.setWrapText(true);
        display.setMinSize(250,50);
        display.setMaxSize(250,50);

        // create and configure text fields for input
        TextField optionsField = new TextField();
        optionsField.setMaxWidth(70);

        // create and configure Labels for the text fields
        Label optionsLabel = new Label("Denomination");
        optionsLabel.setTextFill(Color.BLACK);
        optionsLabel.setFont(Font.font("Arial", 14));

        // create and configure a button to set minimum coin value
        Button calculateButton = new Button();
        calculateButton.setText("Enter");
        calculateButton.setOnAction(e ->  {
            if (optionsField.getText().isEmpty()) {
                display.setText("Please enter a valid option");
            }

            String minCoinIn = optionsField.getText();
            try {
                Integer.parseInt(minCoinIn);

            } catch (NumberFormatException ex) {
                display.setText("Please enter a number");
            }

            display.setText(c.setMinCoinIn(Integer.parseInt(minCoinIn)));

        });

        // create and configure a button to exit the screen
        Button exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setOnAction(e -> {
            setMinCoinStage.close();
        });

        // create and configure an HBox for the labels and text inputs
        HBox inputComponents = new HBox(10);
        inputComponents.setAlignment(Pos.CENTER);
        inputComponents.getChildren().addAll(optionsLabel, optionsField);

        // create and configure an VBox for the menu labels, HBox components, buttons and display text
        VBox root = new VBox(25);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(menuLabel, inputComponents, calculateButton, display, exitButton);

        Scene sceneCoinCalculator = new Scene(root, 300, 350);
        setMinCoinStage.setScene(sceneCoinCalculator);
        setMinCoinStage.setTitle("Set Currency");
        setMinCoinStage.show();


        return setMinCoinStage;
    }

    private static Stage setMaxCoinStage() {
        int [] coinList = {200, 100, 50, 20, 10};
        CoinSorter c=new CoinSorter("GBP", 1, 10000, coinList);

        Stage setMaxCoinStage = new Stage();

        Label menuLabel = new Label("Maximum Coin Setting");
        menuLabel.setTextFill(Color.BLACK);
        menuLabel.setFont(Font.font("Arial", 18));

        // create and configure a non-editable text area to display the results
        TextArea display = new TextArea();
        display.setEditable(false);
        display.setWrapText(true);
        display.setMinSize(250,50);
        display.setMaxSize(250,50);

        // create and configure text fields for input
        TextField optionsField = new TextField();
        optionsField.setMaxWidth(70);

        // create and configure Labels for the text fields
        Label optionsLabel = new Label("Denomination");
        optionsLabel.setTextFill(Color.BLACK);
        optionsLabel.setFont(Font.font("Arial", 14));

        // create and configure a button to set max coin value
        Button calculateButton = new Button();
        calculateButton.setText("Enter");
        calculateButton.setOnAction(e ->  {
            if (optionsField.getText().isEmpty()) {
                display.setText("Please enter a valid option");
            }
            String maxCoinIn = optionsField.getText();

            try {
                Integer.parseInt(maxCoinIn);

            } catch (NumberFormatException ex) {
                display.setText("Please enter a number");
            }

            display.setText(c.setMaxCoinIn(Integer.parseInt(maxCoinIn)));
        });

        // create and configure a button to exit the screen
        Button exitButton = new Button();
        exitButton.setText("Exit");
        exitButton.setOnAction(e -> {
            setMaxCoinStage.close();
        });

        // create and configure an HBox for the labels and text inputs
        HBox inputComponents = new HBox(10);
        inputComponents.setAlignment(Pos.CENTER);
        inputComponents.getChildren().addAll(optionsLabel, optionsField);

        // create and configure an VBox for the menu labels, HBox components, buttons and display text
        VBox root = new VBox(25);
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(menuLabel, inputComponents, calculateButton, display, exitButton);

        Scene sceneCoinCalculator = new Scene(root, 300, 350);
        setMaxCoinStage.setScene(sceneCoinCalculator);
        setMaxCoinStage.setTitle("Set Currency");
        setMaxCoinStage.show();


        return setMaxCoinStage;
    }

    public static void main (String args[]) {

        launch(args);
    }
}



