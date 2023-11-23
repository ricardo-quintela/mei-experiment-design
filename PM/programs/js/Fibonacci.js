function generateFibonacci(n) {
    let fibonacciNumbers = [0, 1];

    for (let i = 2; i < n; i++) {
        // Add the last two numbers to get the next Fibonacci number
        fibonacciNumbers.push(fibonacciNumbers[i - 1] + fibonacciNumbers[i - 2]);
    }

    return fibonacciNumbers;
}

// Print the first 10 Fibonacci numbers
const fibonacciSequence = generateFibonacci(10);
console.log("Fibonacci Numbers:", fibonacciSequence);
