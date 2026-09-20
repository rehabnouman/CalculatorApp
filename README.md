# Scientific Calculator App 🧮

A modern, dark-themed **JavaFX Desktop Calculator Application** built using **Object-Oriented Programming (OOP)** principles, clean **Model-View-Controller (MVC)** design, and custom **Exception Handling**.

---

## 🌟 Key Features

* **Core Math Operations**: Addition (`+`), Subtraction (`−`), Multiplication (`×`), Division (`÷`).
* **Advanced Utilities**: Square Root (`√`), Percentage (`%`), Negation (`±`), Backspace (`⌫`), and Clear (`C`).
* **Smart Operation State**:
  * **Chained Expression Evaluation**: Supports continuous calculations (e.g. `5 + 3 + 2 = 10`).
  * **Operation Repetition**: Pressing `=` repeatedly re-applies the last operation.
  * **Expression Display**: Dual-line display showing the live mathematical expression and output.
  * **Dynamic Font Scaling**: Automatically scales font size based on input length.
* **Error Handling**: Custom exception hierarchy that catches invalid input, numeric overflow, and division by zero—displaying clean, human-readable UI error messages.

---

## 🏗️ Architecture & Design Patterns

The project separates concerns cleanly between user interface, state management, and mathematical evaluation.

```
                  ┌─────────────────────────────────────────┐
                  │              CalculatorApp              │
                  │   (View & Controller - JavaFX GUI)      │
                  └────────────────────┬────────────────────┘
                                       │ Delegates state & math
                                       ▼
                  ┌─────────────────────────────────────────┐
                  │             CalculatorModel             │
                  │        (Application State & Engine)     │
                  └────────────────────┬────────────────────┘
                                       │ Strategy Pattern
                                       ▼
                  ┌─────────────────────────────────────────┐
                  │               Calculable                │
                  │            << Interface >>              │
                  └────────────────────┬────────────────────┘
                                       │ Implemented by
                                       ▼
                  ┌─────────────────────────────────────────┐
                  │              MathOperation              │
                  │          << Abstract Class >>           │
                  └────────────────────┬────────────────────┘
                                       │ Extended by
                     ┌─────────────────┼─────────────────┐
                     ▼                 ▼                 ▼
                 Addition         Subtraction      Multiplication ...
```

### OOP Principles Applied:
1. **Abstraction & Strategy Pattern**: The `Calculable` interface defines the behavior contract for calculations. `MathOperation` extends this interface with reusable validation logic (`validateResult`).
2. **Polymorphism**: Concrete operations (`Addition`, `Subtraction`, `Multiplication`, `Division`) dynamically compute results according to their specific mathematical rules.
3. **Encapsulation**: `CalculatorModel` protects internal state variables (`firstOperand`, `pendingOperation`, `lastResult`) and exposes safe accessor methods.
4. **Custom Exception Hierarchy**:
   * `CalculatorException` *(Base checked exception)*
     * `DivisionByZeroException` *(Division by zero protection)*
     * `OverflowException` *(Numeric range overflow / Infinity / NaN protection)*
     * `InvalidInputException` *(Parsing failure or invalid domain like $\sqrt{-x}$)*

---

## 📂 Project Directory Structure

```
Calculator/
├── src/
│   ├── Calculable.java             # Core operation interface
│   ├── MathOperation.java          # Abstract base operation class
│   ├── BasicOperations.java        # Concrete arithmetic implementations
│   ├── CalculatorModel.java       # Model state & engine
│   ├── CalculatorApp.java         # JavaFX GUI Application & Controller
│   ├── CalculatorException.java    # Parent checked exception
│   ├── DivisionByZeroException.java# Exception for division by zero
│   ├── OverflowException.java      # Exception for numeric overflow
│   └── InvalidInputException.java  # Exception for invalid inputs
├── .vscode/
│   └── launch.json                 # Pre-configured VS Code debug config
├── run.bat                         # Windows 1-click batch launcher
└── README.md                       # Project documentation
```

---

## 🚀 How to Run

### Command Line / Terminal
Run the following commands in your terminal (requires JavaFX SDK):

```bash
# Compile
javac --module-path "C:\path\to\javafx-sdk\lib" --add-modules javafx.controls -d bin src/*.java

# Run
java --module-path "C:\path\to\javafx-sdk\lib" --add-modules javafx.controls -cp bin CalculatorApp
```

---

## 🛠️ Prerequisites
* **Java Development Kit (JDK)**: JDK 17 or JDK 21+
* **JavaFX SDK**: Version 21.0+
