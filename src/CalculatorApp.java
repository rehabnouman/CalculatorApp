import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    // UI Colors
    private static final String BG_DARK       = "#1c1c1e";
    private static final String DISPLAY_BG    = "#1c1c1e";
    private static final String BTN_FUNC      = "#3a3a3c";
    private static final String BTN_OP        = "#ff9f0a";
    private static final String BTN_OP_ACTIVE = "#ffcc02";
    private static final String BTN_NUM       = "#2c2c2e";
    private static final String BTN_EQ        = "#ff9f0a";
    private static final String TXT_DARK      = "#1c1c1e";
    private static final String TXT_WHITE     = "#ffffff";

    // Application State
    private final CalculatorModel model = new CalculatorModel();
    private String currentDisplay = "0";
    private String expressionText = "";
    private boolean startNewNumber = true;
    private boolean inErrorState = false;
    private boolean justComputedResult = false;
    private Button activeOperatorButton = null;

    // UI Components
    private Label expressionLabel;
    private Label displayLabel;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setTop(buildDisplay());
        root.setCenter(buildButtonGrid());
        root.setStyle("-fx-background-color: " + BG_DARK + ";");
        root.setPadding(new Insets(12));

        primaryStage.setTitle("Scientific Calculator");
        primaryStage.setScene(new Scene(root, 340, 580));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private VBox buildDisplay() {
        expressionLabel = new Label("");
        expressionLabel.setMaxWidth(Double.MAX_VALUE);
        expressionLabel.setAlignment(Pos.CENTER_RIGHT);
        expressionLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 15));
        expressionLabel.setStyle("-fx-text-fill: #8e8e93; -fx-padding: 0 4 0 0;");

        displayLabel = new Label("0");
        displayLabel.setMaxWidth(Double.MAX_VALUE);
        displayLabel.setAlignment(Pos.CENTER_RIGHT);
        displayLabel.setFont(Font.font("Arial", FontWeight.LIGHT, 54));
        displayLabel.setStyle("-fx-text-fill: " + TXT_WHITE + "; -fx-padding: 0 4 0 0;");

        VBox display = new VBox(4, expressionLabel, displayLabel);
        display.setAlignment(Pos.BOTTOM_RIGHT);
        display.setStyle("-fx-background-color: " + DISPLAY_BG + "; -fx-padding: 8 8 16 8; -fx-background-radius: 12;");
        BorderPane.setMargin(display, new Insets(0, 0, 10, 0));
        return display;
    }

    private GridPane buildButtonGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        for (int c = 0; c < 4; c++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setPercentWidth(25);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }
        for (int r = 0; r < 6; r++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(16.66);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }

        // Row 0: Function Keys
        grid.add(makeButton("C", BTN_FUNC, TXT_WHITE, new ClearAllHandler()), 0, 0);
        grid.add(makeButton("⌫", BTN_FUNC, TXT_WHITE, new BackspaceHandler()), 1, 0);
        grid.add(makeButton("√", BTN_FUNC, TXT_WHITE, new SquareRootHandler()), 2, 0);
        grid.add(makeButton("%", BTN_FUNC, TXT_WHITE, new PercentHandler()), 3, 0);

        // Row 1: 7, 8, 9, Division
        grid.add(makeButton("7", BTN_NUM, TXT_WHITE, new DigitHandler("7")), 0, 1);
        grid.add(makeButton("8", BTN_NUM, TXT_WHITE, new DigitHandler("8")), 1, 1);
        grid.add(makeButton("9", BTN_NUM, TXT_WHITE, new DigitHandler("9")), 2, 1);
        grid.add(makeOpButton("÷", model.getOperations()[3]), 3, 1);

        // Row 2: 4, 5, 6, Multiplication
        grid.add(makeButton("4", BTN_NUM, TXT_WHITE, new DigitHandler("4")), 0, 2);
        grid.add(makeButton("5", BTN_NUM, TXT_WHITE, new DigitHandler("5")), 1, 2);
        grid.add(makeButton("6", BTN_NUM, TXT_WHITE, new DigitHandler("6")), 2, 2);
        grid.add(makeOpButton("×", model.getOperations()[2]), 3, 2);

        // Row 3: 1, 2, 3, Subtraction
        grid.add(makeButton("1", BTN_NUM, TXT_WHITE, new DigitHandler("1")), 0, 3);
        grid.add(makeButton("2", BTN_NUM, TXT_WHITE, new DigitHandler("2")), 1, 3);
        grid.add(makeButton("3", BTN_NUM, TXT_WHITE, new DigitHandler("3")), 2, 3);
        grid.add(makeOpButton("−", model.getOperations()[1]), 3, 3);

        // Row 4: Negate, 0, Decimal, Addition
        grid.add(makeButton("±", BTN_FUNC, TXT_WHITE, new NegateHandler()), 0, 4);
        grid.add(makeButton("0", BTN_NUM, TXT_WHITE, new DigitHandler("0")), 1, 4);
        grid.add(makeButton(".", BTN_NUM, TXT_WHITE, new DecimalHandler()), 2, 4);
        grid.add(makeOpButton("+", model.getOperations()[0]), 3, 4);

        // Row 5: Equals Button (Spans full width)
        Button eqBtn = makeButton("=", BTN_EQ, TXT_DARK, new EqualsHandler());
        grid.add(eqBtn, 0, 5, 4, 1);

        return grid;
    }

    private Button makeButton(String label, String bg, String fg, EventHandler<ActionEvent> handler) {
        Button btn = new Button(label);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setMaxHeight(Double.MAX_VALUE);
        btn.setFont(Font.font("Arial", FontWeight.NORMAL, 22));
        btn.setStyle(btnStyle(bg, fg, "6"));
        btn.setOnAction(handler);
        btn.setOnMouseEntered(e -> btn.setStyle(btnStyle(lighten(bg), fg, "6")));
        btn.setOnMouseExited(e -> btn.setStyle(btnStyle(bg, fg, "6")));
        return btn;
    }

    private Button makeOpButton(String symbol, MathOperation op) {
        Button btn = new Button(symbol);
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setMaxHeight(Double.MAX_VALUE);
        btn.setFont(Font.font("Arial", FontWeight.NORMAL, 26));
        btn.setStyle(btnStyle(BTN_OP, TXT_DARK, "50"));
        btn.setOnAction(new OperatorHandler(op, btn));
        btn.setOnMouseEntered(e -> {
            if (btn != activeOperatorButton) btn.setStyle(btnStyle(BTN_OP_ACTIVE, TXT_DARK, "50"));
        });
        btn.setOnMouseExited(e -> {
            if (btn != activeOperatorButton) btn.setStyle(btnStyle(BTN_OP, TXT_DARK, "50"));
        });
        return btn;
    }

    private String btnStyle(String bg, String fg, String radius) {
        return "-fx-background-color: " + bg + "; -fx-text-fill: " + fg
                + "; -fx-background-radius: " + radius + "; -fx-cursor: hand;";
    }

    private String lighten(String hex) {
        if (hex.startsWith("#") && hex.length() == 7) {
            try {
                int r = Math.min(255, Integer.parseInt(hex.substring(1, 3), 16) + 30);
                int g = Math.min(255, Integer.parseInt(hex.substring(3, 5), 16) + 30);
                int b = Math.min(255, Integer.parseInt(hex.substring(5, 7), 16) + 30);
                return String.format("#%02x%02x%02x", r, g, b);
            } catch (NumberFormatException ignored) {}
        }
        return hex;
    }

    private void updateDisplay() {
        int len = currentDisplay.length();
        double fs = len > 14 ? 26 : len > 10 ? 36 : 54;
        displayLabel.setFont(Font.font("Arial", FontWeight.LIGHT, fs));
        displayLabel.setStyle("-fx-text-fill: " + TXT_WHITE + "; -fx-padding: 0 4 0 0;");
        displayLabel.setText(currentDisplay);
    }

    private void showError(String msg) {
        inErrorState = true;
        startNewNumber = true;
        expressionLabel.setText("");
        displayLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        displayLabel.setStyle("-fx-text-fill: #ff453a; -fx-padding: 0 4 0 0;");
        displayLabel.setText(msg);
        clearOperatorHighlight();
    }

    private void updateExpression(String text) {
        expressionText = text;
        expressionLabel.setText(text);
    }

    private String fmt(double v) {
        if (Double.isInfinite(v)) return v > 0 ? "∞" : "−∞";
        if (Double.isNaN(v)) return "Undefined";
        if (v == Math.floor(v) && Math.abs(v) < 1e15) return String.valueOf((long) v);
        return String.valueOf(v);
    }

    private void clearOperatorHighlight() {
        if (activeOperatorButton != null) {
            activeOperatorButton.setStyle(btnStyle(BTN_OP, TXT_DARK, "50"));
            activeOperatorButton = null;
        }
    }

    private class DigitHandler implements EventHandler<ActionEvent> {
        private final String digit;

        DigitHandler(String digit) {
            this.digit = digit;
        }

        @Override
        public void handle(ActionEvent e) {
            if (inErrorState) {
                inErrorState = false;
                model.reset();
                currentDisplay = digit;
                startNewNumber = false;
                updateExpression("");
                updateDisplay();
                return;
            }
            if (startNewNumber) {
                currentDisplay = digit.equals("0") ? "0" : digit;
                startNewNumber = false;
            } else {
                if (currentDisplay.equals("0") && !digit.equals(".")) {
                    currentDisplay = digit;
                } else if (currentDisplay.length() < 15) {
                    currentDisplay += digit;
                }
            }
            justComputedResult = false;
            updateDisplay();
        }
    }

    private class DecimalHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (inErrorState) {
                inErrorState = false;
                currentDisplay = "0.";
                startNewNumber = false;
                updateDisplay();
                return;
            }
            if (startNewNumber) {
                currentDisplay = "0.";
                startNewNumber = false;
            } else if (!currentDisplay.contains(".")) {
                currentDisplay += ".";
            }
            justComputedResult = false;
            updateDisplay();
        }
    }

    private class OperatorHandler implements EventHandler<ActionEvent> {
        private final MathOperation operation;
        private final Button button;

        OperatorHandler(MathOperation op, Button btn) {
            this.operation = op;
            this.button = btn;
        }

        @Override
        public void handle(ActionEvent e) {
            if (inErrorState) {
                inErrorState = false;
                model.reset();
                currentDisplay = "0";
                updateExpression("");
            }
            try {
                double v = model.parseDisplayValue(currentDisplay);
                if (model.getPendingOperation() != null && !startNewNumber) {
                    currentDisplay = fmt(model.compute(v));
                }
                model.setFirstOperand(model.parseDisplayValue(currentDisplay));
                model.setPendingOperation(operation);
                updateExpression(fmt(model.getFirstOperand()) + " " + operation.getSymbol());
                startNewNumber = true;
                justComputedResult = false;
                clearOperatorHighlight();
                button.setStyle(btnStyle(BTN_OP_ACTIVE, TXT_DARK, "50"));
                activeOperatorButton = button;
            } catch (CalculatorException ex) {
                showError(ex.getMessage());
            }
        }
    }

    private class EqualsHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (inErrorState) {
                inErrorState = false;
                model.reset();
                currentDisplay = "0";
                updateExpression("");
                updateDisplay();
                return;
            }
            try {
                double v = model.parseDisplayValue(currentDisplay);
                double result;
                if (model.getPendingOperation() != null) {
                    updateExpression(fmt(model.getFirstOperand()) + " "
                            + model.getPendingOperation().getSymbol()
                            + " " + fmt(v) + " =");
                    result = model.compute(v);
                    model.setPendingOperation(null);
                } else if (justComputedResult) {
                    result = model.repeatLastComputation(model.parseDisplayValue(currentDisplay));
                } else {
                    result = v;
                }
                currentDisplay = fmt(result);
                justComputedResult = true;
                startNewNumber = true;
                clearOperatorHighlight();
                updateDisplay();
            } catch (CalculatorException ex) {
                showError(ex.getMessage());
            }
        }
    }

    private class ClearAllHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            model.reset();
            currentDisplay = "0";
            inErrorState = false;
            startNewNumber = true;
            justComputedResult = false;
            clearOperatorHighlight();
            updateExpression("");
            updateDisplay();
        }
    }

    private class BackspaceHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (inErrorState || startNewNumber || justComputedResult) {
                currentDisplay = "0";
                inErrorState = false;
                startNewNumber = true;
                justComputedResult = false;
                updateDisplay();
                return;
            }
            if (currentDisplay.length() > 1) {
                currentDisplay = currentDisplay.substring(0, currentDisplay.length() - 1);
                if (currentDisplay.equals("-")) {
                    currentDisplay = "0";
                }
            } else {
                currentDisplay = "0";
                startNewNumber = true;
            }
            updateDisplay();
        }
    }

    private class NegateHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (inErrorState) return;
            try {
                currentDisplay = fmt(model.negate(model.parseDisplayValue(currentDisplay)));
                updateDisplay();
            } catch (CalculatorException ex) {
                showError(ex.getMessage());
            }
        }
    }

    private class PercentHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (inErrorState) return;
            try {
                double v = model.parseDisplayValue(currentDisplay);
                double pct = model.getPendingOperation() != null
                        ? model.getFirstOperand() * model.toPercentage(v)
                        : model.toPercentage(v);
                currentDisplay = fmt(pct);
                updateDisplay();
            } catch (CalculatorException ex) {
                showError(ex.getMessage());
            }
        }
    }

    private class SquareRootHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (inErrorState) return;
            try {
                double v = model.parseDisplayValue(currentDisplay);
                double res = model.squareRoot(v);
                currentDisplay = fmt(res);
                startNewNumber = true;
                justComputedResult = true;
                updateDisplay();
            } catch (CalculatorException ex) {
                showError(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}