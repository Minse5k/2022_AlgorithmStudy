const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");

const T = parseInt(input[0]);
const K = parseInt(input[1]);
const P = Array.from({ length: K }, () => 0);
const N = Array.from({ length: K }, () => 0);
const dp = Array.from({ length: T + 1 }, () => 0);

for (let i = 2; i < 2 + K; i++) {
  [P[i - 2], N[i - 2]] = input[i].split(" ").map((v) => parseInt(v));
}

// 0원을 만드는 경우의 수 = 1
dp[0] = 1;

for (let i = 0; i < K; i++) {
  for (let j = T; j >= 1; j--) {
    let sum = 0;
    for (let k = 0; k < N[i]; k++) {
      sum += P[i];
      if (j - sum >= 0 && dp[j - sum] > 0) dp[j] += dp[j - sum];
    }
  }
}

console.log(dp[T]);
