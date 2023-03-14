const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");
const N = parseInt(input[0]);
const dp = Array.from({ length: N + 1 }, () => Array.from({ length: N + 1 }, () => 0));
const arr = [0];
arr.push(...input[1].split(" ").map((v) => parseInt(v)));
const M = parseInt(input[2]);

const solution = () => {
  //길이 1
  for (let i = 1; i <= N; i++) {
    dp[i][i] = 1;
  }
  //길이 2
  for (let i = 1; i <= N - 1; i++) {
    if (arr[i] === arr[i + 1]) dp[i][i + 1] = 1;
  }
  //길이 3이상
  for (let i = 2; i <= N; i++) {
    for (let j = 1; j <= N - 1; j++) {
      if (arr[j] === arr[j + i] && dp[j + 1][j + i - 1] === 1) {
        dp[j][j + i] = 1;
      }
    }
  }

  let ans = "";
  for (let i = 3; i < 3 + M; i++) {
    const [s, e] = input[i].split(" ").map((v) => parseInt(v));
    ans += `${dp[s][e]}\n`;
  }
  console.log(ans);
};
solution();
