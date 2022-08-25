let N;
let M = 0;
let input = [];
let dp = [];
const dfs = (left, right, kTurn) => {
  if (left > right) return 0;

  if (dp[left][right] !== 0) return dp[left][right];

  if (kTurn) {
    return (dp[left][right] = Math.max(
      input[left] + dfs(left + 1, right, false),
      input[right] + dfs(left, right - 1, false),
    ));
  } else {
    return (dp[left][right] = Math.min(
      dfs(left + 1, right, true),
      dfs(left, right - 1, true),
    ));
  }
};
const solution = () => {
  dp = Array.from({ length: M }, () => Array.from({ length: M }, () => 0));

  dfs(0, M - 1, true);
  console.log(dp[0][M - 1]);
};
require('readline')
  .createInterface(process.stdin, process.stdout)
  .on('line', line => {
    if (!N) {
      N = parseInt(line);
      return;
    }
    if (M === 0) {
      M = parseInt(line);
      return;
    }
    input = line.split(' ').map(v => parseInt(v));
    solution();
    M = 0;
  })
  .on('close', () => {
    process.exit();
  });
