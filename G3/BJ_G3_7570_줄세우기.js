const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");

const N = parseInt(input[0]);

const solution = () => {
  const dp = Array.from({ length: N + 1 }, () => 0);
  const arr = input[1].split(" ").map((v) => parseInt(v));
  let max = 0;
  for (let i = 1; i <= N; i++) {
    const num = arr[i - 1];
    dp[num] = dp[num - 1] + 1;
    max = Math.max(dp[num], max);
  }
  console.log(N - max);
};
solution();
