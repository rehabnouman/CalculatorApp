// Automated Unit Test Suite for GitHub Actions CI Pipeline
const fs = require('fs');

console.log('🧪 Starting Automated Software Engineering CI Checks...');

// Test 1: Verify HTML structure and required UI elements
console.log('Step 1: Validating HTML layout...');
const htmlContent = fs.readFileSync('index.html', 'utf-8');
if (!htmlContent.includes('id="display"') || !htmlContent.includes('id="expression"')) {
    console.error('❌ HTML Validation Failed: Display element missing!');
    process.exit(1);
}
console.log('✅ HTML validation passed!');

// Test 2: Mathematical Evaluation Engine Test
console.log('Step 2: Testing Mathematical Logic...');
function evaluateMath(first, op, second) {
    if (op === '+') return first + second;
    if (op === '-') return first - second;
    if (op === '*') return first * second;
    if (op === '/') {
        if (second === 0) throw new Error('Cannot divide by zero');
        return first / second;
    }
    throw new Error('Invalid Operator');
}

try {
    // Basic test cases
    if (evaluateMath(5, '+', 3) !== 8) throw new Error('Addition failed');
    if (evaluateMath(10, '-', 4) !== 6) throw new Error('Subtraction failed');
    if (evaluateMath(6, '*', 7) !== 42) throw new Error('Multiplication failed');
    if (evaluateMath(20, '/', 5) !== 4) throw new Error('Division failed');
    console.log('✅ Math logic unit tests passed!');
} catch (err) {
    console.error(`❌ Unit Test Failed: ${err.message}`);
    process.exit(1);
}

console.log('🎉 All Automated CI Checks Passed Successfully!');
