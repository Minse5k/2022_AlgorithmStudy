const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\r\n");
const [N, M] = input[0].split(" ").map((v) => parseInt(v));
const arr = [];
for (let i = 1; i <= M; i++) {
  arr.push(input[i].split(" ").map((v) => parseInt(v)));
}
arr.sort((a, b) => {
  if (a[0] === b[0]) {
    a[1] - b[1];
  }
  return a[0] - b[0];
});
const dp = Array.from({ length: N }, () => 1);
arr.forEach(([A, B]) => {
  dp[B - 1] = Math.max(dp[B - 1], dp[A - 1] + 1);
});
console.log(dp.join(" "));
            
