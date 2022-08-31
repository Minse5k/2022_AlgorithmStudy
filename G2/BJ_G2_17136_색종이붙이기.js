const fs = require('fs');
const filePath =
  process.platform === 'linux' ? '/dev/stdin' : '../../input.txt';
const input = fs.readFileSync(filePath).toString().split('\n');
const N = 10;
let minCnt = Infinity;
const paperCnt = Array.from({ length: 6 }, () => 5);
let map = [];

const solution = () => {
  map = input.map(row =>
    row.split(' ').map(col => {
      if (col === '0') return false;
      else return true;
    }),
  );

  dfs(0, 0);
  console.log(minCnt === Infinity ? -1 : minCnt);
};

const dfs = (idx, cnt) => {
  if (idx >= N * N) {
    minCnt = Math.min(cnt, minCnt);
    return;
  }

  if (minCnt <= cnt) return;

  const r = parseInt(idx / N);
  const c = idx % N;
  if (map[r][c]) {
    for (let size = 5; size >= 1; size--) {
      if (paperCnt[size] === 0 || !isSetPaper(r, c, size)) continue;
      paperCnt[size]--;
      setPaper(r, c, size, false);
      dfs(idx + 1, cnt + 1);
      paperCnt[size]++;
      setPaper(r, c, size, true);
    }
  } else {
    dfs(idx + 1, cnt);
  }
};

const isSetPaper = (r, c, size) => {
  for (let i = r; i < r + size; i++) {
    for (let j = c; j < c + size; j++) {
      if (!isIn(i, j) || !map[i][j]) return false;
    }
  }
  return true;
};

const setPaper = (r, c, size, isPaper) => {
  for (let i = r; i < r + size; i++) {
    for (let j = c; j < c + size; j++) {
      map[i][j] = isPaper;
    }
  }
};

const isIn = (r, c) => {
  return 0 <= r && r < N && 0 <= c && c < N;
};

solution();
