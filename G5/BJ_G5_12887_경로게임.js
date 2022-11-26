const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
const N = parseInt(input[0]);
const dist = [
  [1, 0],
  [-1, 0],
  [0, 1],
  [0, -1],
];
const map = [];
let whiteCnt = 0;
for (let i = 1; i <= 2; i++) {
  map.push(
    input[i].split("").map((v, idx) => {
      if (v === "#") return true;
      else {
        whiteCnt++;
        return false;
      }
    })
  );
}

const isIn = (x, y) => {
  return 0 <= x && x < 2 && 0 <= y && y < N;
};

const bfs = (visited, sx, sy) => {
  let len = 1;
  const queue = [];
  queue.push([sx, sy]);
  visited[sx][sy] = true;

  while (queue.length > 0) {
    const size = queue.length;

    for (let s = 0; s < size; s++) {
      const [x, y] = queue.shift();
      if (y === N - 1) {
        return len;
      }
      for (let i = 0; i < 4; i++) {
        const nx = x + dist[i][0];
        const ny = y + dist[i][1];

        if (!isIn(nx, ny) || visited[nx][ny]) continue;
        visited[nx][ny] = true;
        queue.push([nx, ny]);
      }
    }
    len++;
  }
  return Infinity;
};

const getMap = () => {
  const tmp = Array.from({ length: 2 }, () => Array.from({ length: N }));
  map.forEach((r, i) => {
    r.forEach((v, j) => {
      tmp[i][j] = v;
    });
  });
  return tmp;
};

const solution = () => {
  const len = [Infinity, Infinity];
  for (let i = 0; i < 2; i++) {
    const copyMap = getMap();
    if (copyMap[i][0]) continue;
    len[i] = bfs(copyMap, i, 0);
  }
  console.log(whiteCnt - Math.min(...len));
};

solution();
