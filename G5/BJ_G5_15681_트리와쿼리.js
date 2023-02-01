const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\r\n");
const [N, R, Q] = input[0].split(" ").map((v) => parseInt(v));
const graph = Array.from({ length: N + 1 }, () => []);
const dp = Array.from({ length: 1000001 }, () => 0);

const dfs = (now, prev) => {
  dp[now] = 1;
  for (const next of graph[now]) {
    if (next === prev) continue;
    dp[now] += dfs(next, now);
  }
  return dp[now];
};

const solution = () => {
  for (let i = 1; i < N; i++) {
    const [U, V] = input[i].split(" ").map((v) => parseInt(v));
    graph[U].push(V);
    graph[V].push(U);
  }

  dfs(R, 0);
  let ans = "";
  for (let i = N; i < N + Q; i++) {
    ans += dp[parseInt(input[i])] + "\n";
  }
  console.log(ans);
};

solution();
