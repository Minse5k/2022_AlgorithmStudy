const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");

const N = parseInt(input);
const dp = Array.from({ length: N + 1 }, () => Array.from({ length: N + 1 }, () => 0));

dp[2][1] = 2;

for (let i = 3; i < N + 1; i++) {
  for (let j = 1; j < i; j++) {
    dp[i][j] = (dp[i - 1][j] * 2 + dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 10007;
  }
}

let sum = 0;
for (let i = 1; i < N; i++) {
  sum += dp[N][i];
}
console.log(sum % 10007);
