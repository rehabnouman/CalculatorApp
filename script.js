let currentDisplay = '0';
let expressionText = '';
let startNewNumber = true;
let inErrorState = false;

const displayEl = document.getElementById('display');
const expressionEl = document.getElementById('expression');

function updateDisplay() {
    displayEl.textContent = currentDisplay;
    expressionEl.textContent = expressionText;

    // Adjust font size dynamically for long numbers
    const len = currentDisplay.length;
    if (len > 12) {
        displayEl.style.fontSize = '1.8rem';
    } else if (len > 8) {
        displayEl.style.fontSize = '2.2rem';
    } else {
        displayEl.style.fontSize = '2.8rem';
    }
}

function appendNum(num) {
    if (inErrorState) {
        clearAll();
    }
    if (startNewNumber) {
        currentDisplay = num;
        startNewNumber = false;
    } else {
        if (currentDisplay === '0' && num !== '.') {
            currentDisplay = num;
        } else if (currentDisplay.length < 15) {
            currentDisplay += num;
        }
    }
    updateDisplay();
}

function appendDot() {
    if (inErrorState) {
        clearAll();
    }
    if (startNewNumber) {
        currentDisplay = '0.';
        startNewNumber = false;
    } else if (!currentDisplay.includes('.')) {
        currentDisplay += '.';
    }
    updateDisplay();
}

function appendOp(op) {
    if (inErrorState) {
        clearAll();
    }
    expressionText = `${currentDisplay} ${op}`;
    startNewNumber = true;
    updateDisplay();
}

function clearAll() {
    currentDisplay = '0';
    expressionText = '';
    startNewNumber = true;
    inErrorState = false;
    updateDisplay();
}

function backspace() {
    if (inErrorState || startNewNumber) {
        clearAll();
        return;
    }
    if (currentDisplay.length > 1) {
        currentDisplay = currentDisplay.slice(0, -1);
        if (currentDisplay === '-') currentDisplay = '0';
    } else {
        currentDisplay = '0';
        startNewNumber = true;
    }
    updateDisplay();
}

function toggleSign() {
    if (inErrorState || currentDisplay === '0') return;
    currentDisplay = (parseFloat(currentDisplay) * -1).toString();
    updateDisplay();
}

function calcFunc(func) {
    if (inErrorState) return;
    const val = parseFloat(currentDisplay);
    let result = 0;

    try {
        if (func === 'sin') result = Math.sin((val * Math.PI) / 180);
        else if (func === 'cos') result = Math.cos((val * Math.PI) / 180);
        else if (func === 'tan') result = Math.tan((val * Math.PI) / 180);
        else if (func === 'sqrt') {
            if (val < 0) throw new Error('Invalid Input');
            result = Math.sqrt(val);
        }

        currentDisplay = formatResult(result);
        expressionText = `${func}(${val})`;
        startNewNumber = true;
        updateDisplay();
    } catch (err) {
        showError(err.message || 'Error');
    }
}

function calculateResult() {
    if (inErrorState || !expressionText) return;

    const parts = expressionText.split(' ');
    if (parts.length < 2) return;

    const first = parseFloat(parts[0]);
    const op = parts[1];
    const second = parseFloat(currentDisplay);
    let result = 0;

    try {
        if (op === '+') result = first + second;
        else if (op === '-') result = first - second;
        else if (op === '*') result = first * second;
        else if (op === '/') {
            if (second === 0) throw new Error('Cannot divide by zero');
            result = first / second;
        } else if (op === '%') {
            result = first % second;
        }

        expressionText = `${first} ${op} ${second} =`;
        currentDisplay = formatResult(result);
        startNewNumber = true;
        updateDisplay();
    } catch (err) {
        showError(err.message);
    }
}

function formatResult(val) {
    if (isNaN(val) || !isFinite(val)) {
        throw new Error('Numeric Overflow');
    }
    if (Number.isInteger(val)) return val.toString();
    return parseFloat(val.toFixed(8)).toString();
}

function showError(msg) {
    currentDisplay = msg;
    expressionText = '';
    inErrorState = true;
    startNewNumber = true;
    updateDisplay();
}

// Keyboard Integration
document.addEventListener('keydown', (e) => {
    if (e.key >= '0' && e.key <= '9') appendNum(e.key);
    else if (e.key === '.') appendDot();
    else if (e.key === '+') appendOp('+');
    else if (e.key === '-') appendOp('-');
    else if (e.key === '*') appendOp('*');
    else if (e.key === '/') appendOp('/');
    else if (e.key === '%') appendOp('%');
    else if (e.key === 'Enter' || e.key === '=') calculateResult();
    else if (e.key === 'Backspace') backspace();
    else if (e.key === 'Escape') clearAll();
});
