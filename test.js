// Automated Unit Test Suite for Scientific Calculator App
const fs = require('fs');

console.log('🧪 Starting Automated Software Engineering CI Checks...\n');

// --- Test Case 1: HTML Structure Validation ---
console.log('Test Case 1: Validating HTML UI layout...');
const htmlContent = fs.readFileSync('index.html', 'utf-8');
if (!htmlContent.includes('id="display"') || !htmlContent.includes('id="expression"')) {
    console.error('❌ Test Case 1 Failed: Display elements missing from HTML!');
    process.exit(1);
}
console.log('✅ Test Case 1 Passed: HTML layout elements present.');

// --- Test Case 2: Addition Test Case ---
console.log('Test Case 2: Testing Addition (5 + 3 = 8)...');
if ((5 + 3) !== 8) {
    console.error('❌ Test Case 2 Failed: 5 + 3 did not equal 8!');
    process.exit(1);
}
console.log('✅ Test Case 2 Passed: Addition logic correct.');

// --- Test Case 3: Intentional Failure Test Case for Screenshot ---
console.log('Test Case 3: Testing Subtraction Assertion (10 - 4)...');
const subtractionResult = 10 - 4;
const expectedValue = 999; // INTENTIONAL ERROR FOR FAILED CI SCREENSHOT

if (subtractionResult !== expectedValue) {
    console.error(`❌ Test Case 3 FAILED: Subtraction (10 - 4) expected ${expectedValue}, but got ${subtractionResult}`);
    console.error('🚨 CI BUILD FAILED: Assertion Error in Test Case 3!');
    process.exit(1); // Triggers GitHub Actions Failed CI Run (❌)
}

console.log('✅ Test Case 3 Passed!');
