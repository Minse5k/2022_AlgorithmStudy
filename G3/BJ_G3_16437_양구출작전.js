const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");
const N = parseInt(input.shift());
const graph = Array.from({ length: N + 1 }, () => []);
let ans = 0;

const setGraph = () => {
  input.forEach((v, idx) => {
    const [t, a, p] = v.split(" ");
    graph[idx + 2].push({ type: t, cnt: parseInt(a), to: parseInt(p) });
  });
};

const dfs = (node, sum) => {
  if (sum <= 0) return;
  if (node === 1) {
    ans += sum;
    return;
  }
  if (graph[node].length === 0) return;

  const { type, cnt, to } = graph[node][0];
  if (type === "W") {
    graph[node][0].cnt = cnt - sum < 0 ? 0 : cnt - sum;
    dfs(to, sum - cnt);
  } else dfs(to, sum);
};
const solution = () => {
  setGraph();

  for (let i = 2; i <= N; i++) {
    if (graph[i].length > 0 && graph[i][0].type === "W") continue;
    dfs(graph[i][0].to, graph[i][0].cnt);
  }
  console.log(ans);
};

solution();
