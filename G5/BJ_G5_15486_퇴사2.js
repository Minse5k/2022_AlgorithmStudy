const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");

const N = parseInt(input[0]);
const arr = [[]];
const dp = Array.from({ length: N + 2 }, () => 0);
for (let i = 1; i <= N; i++) {
  arr.push(input[i].split(" ").map((v) => parseInt(v)));
}

for (let i = 1; i <= N; i++) {
  const next = i + arr[i][0];
  if (next <= N + 1) {
    dp[next] = Math.max(dp[next], dp[i] + arr[i][1]);
  }
  dp[i + 1] = Math.max(dp[i], dp[i + 1]);
}
console.log(dp[N + 1]);
