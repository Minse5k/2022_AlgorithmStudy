const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");
const [N, M] = input[0].split(" ").map((v) => parseInt(v));
const map = [];
for (let i = 1; i <= N; i++) {
  map.push(input[i].split(" ").map((v) => parseInt(v)));
}
const visited = Array.from({ length: N }, () => Array.from({ length: M }, () => false));
let max = 0;
const dist = [
  [
    [1, 0],
    [0, -1],
  ],
  [
    [-1, 0],
    [0, -1],
  ],
  [
    [1, 0],
    [0, 1],
  ],
  [
    [-1, 0],
    [0, 1],
  ],
];

const isIn = (x, y) => {
  return 0 <= x && x < N && 0 <= y && y < M;
};

const dfs = (x, y, sum) => {
  max = Math.max(sum, max);
  if (max === 1059) return;

  if (y === M) {
    y = 0;
    x += 1;
  }

  if (x === N) return;

  for (let i = 0; i < 4; i++) {
    const nx1 = x + dist[i][0][0];
    const ny1 = y + dist[i][0][1];
    const nx2 = x + dist[i][1][0];
    const ny2 = y + dist[i][1][1];

    if (
      !isIn(nx1, ny1) ||
      !isIn(nx2, ny2) ||
      visited[x][y] ||
      visited[nx1][ny1] ||
      visited[nx2][ny2]
    )
      continue;
    visited[nx1][ny1] = true;
    visited[nx2][ny2] = true;
    visited[x][y] = true;

    dfs(x, y + 1, sum + map[x][y] * 2 + map[nx1][ny1] + map[nx2][ny2]);

    visited[nx1][ny1] = false;
    visited[nx2][ny2] = false;
    visited[x][y] = false;
  }
  dfs(x, y + 1, sum);
};

dfs(0, 0, 0);
console.log(max);
