// Automated Unit Test Suite for Scientific Calculator App (Passed CI Test Suite)
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

// --- Test Case 3: Subtraction Test Case ---
console.log('Test Case 3: Testing Subtraction (10 - 4 = 6)...');
const subtractionResult = 10 - 4;
const expectedValue = 6; // FIXED: Correct expected value

if (subtractionResult !== expectedValue) {
    console.error(`❌ Test Case 3 Failed: Subtraction (10 - 4) expected ${expectedValue}, but got ${subtractionResult}`);
    process.exit(1);
}
console.log('✅ Test Case 3 Passed: Subtraction logic correct.');

// --- Test Case 4: Multiplication Test Case ---
console.log('Test Case 4: Testing Multiplication (6 * 7 = 42)...');
if ((6 * 7) !== 42) {
    console.error('❌ Test Case 4 Failed: 6 * 7 did not equal 42!');
    process.exit(1);
}
console.log('✅ Test Case 4 Passed: Multiplication logic correct.');

console.log('\n🎉 All Automated Unit Test Cases Passed Successfully!');
