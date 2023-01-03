const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
let [N, L, I] = fs
  .readFileSync(filePath)
  .toString()
  .split(" ")
  .map((v) => parseInt(v));

const memo = Array.from({ length: 32 }, () => Array.from({ length: 32 }, () => -1));

const dp = (i, j) => {
  if (j === L || i === N) return 1;
  if (memo[i][j] !== -1) return memo[i][j];
  return (memo[i][j] = dp(i + 1, j) + dp(i + 1, j + 1));
};

let ans = "";
for (let i = 1, j = 0; i <= N; i++) {
  if (dp(i, j) >= I) ans += "0";
  else {
    ans += "1";
    I -= dp(i, j);
    j++;
  }
}

console.log(ans);
