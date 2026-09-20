// Automated Unit Test Suite for GitHub Actions CI Pipeline (Failed CI Test Demonstration)
const fs = require('fs');

console.log('🧪 Starting Automated Software Engineering CI Checks...');

// Test 1: Verify HTML structure
console.log('Step 1: Validating HTML layout...');
const htmlContent = fs.readFileSync('index.html', 'utf-8');
if (!htmlContent.includes('id="display"') || !htmlContent.includes('id="expression"')) {
    console.error('❌ HTML Validation Failed: Display element missing!');
    process.exit(1);
}
console.log('✅ HTML validation passed!');

// Test 2: Intentional Failure Demonstration
console.log('Step 2: Testing Mathematical Logic (Intentional Failure)...');
function evaluateMath(first, op, second) {
    if (op === '+') return first + second;
    return 0;
}

try {
    // Intentional error assertion: 5 + 3 is 8, but we expect 999 to force a CI failure
    if (evaluateMath(5, '+', 3) !== 999) {
        throw new Error('Intentional Test Error: 5 + 3 does not equal 999!');
    }
} catch (err) {
    console.error(`❌ CI Test Failed as expected: ${err.message}`);
    process.exit(1);
}
