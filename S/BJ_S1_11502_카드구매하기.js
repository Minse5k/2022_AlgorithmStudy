const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");
const N = parseInt(input[0]);
const cards = input[1].split(" ").map((v) => parseInt(v));
const dp = Array.from({ length: N + 1 }, () => 0);

for (let i = 1; i <= N; i++) {
  for (let j = 1; j <= i; j++) {
    dp[i] = Math.max(dp[i], dp[i - j] + cards[j - 1]);
  }
}

console.log(dp[N]);
