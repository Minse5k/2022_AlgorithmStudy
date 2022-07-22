let N, M;
const input = [];
const dist = [
  [-1, 0],
  [0, 1],
  [1, 0],
  [0, -1],
]; // U R D L
const dir = { U: 0, R: 1, D: 2, L: 3 };
let dp = [];

const solution = () => {
  let ans = 0;
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      const isPossible = dfs(i, j);
      if (isPossible) ans++;
    }
  }
  console.log(ans);
};

const isIn = (x, y) => {
  return 0 <= x && x < N && 0 <= y && y < M;
};

const dfs = (x, y) => {
  if (dp[x][y] !== -1) return dp[x][y];
  dp[x][y] = 0;

  const nDir = dir[input[x][y]];
  const nx = x + dist[nDir][0];
  const ny = y + dist[nDir][1];

  if (!isIn(nx, ny)) {
    dp[x][y] = 1;
  } else {
    dp[x][y] = dfs(nx, ny);
  }
  return dp[x][y];
};

require("readline")
  .createInterface(process.stdin, process.stdout)
  .on("line", (line) => {
    if (!N) {
      [N, M] = line.split(" ").map((v) => parseInt(v));
      return;
    }
    input.push(line.split(""));
  })
  .on("close", () => {
    dp = Array.from({ length: N }, () => Array.from({ length: M }, () => -1));
    solution();
    process.exit();
  });
