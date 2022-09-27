const fs = require('fs');
const filePath =
  process.platform === 'linux' ? '/dev/stdin' : '../../input.txt';
const input = fs.readFileSync(filePath).toString().split('\n');
const [N, M] = input
  .shift()
  .split(' ')
  .map(v => parseInt(v));

const visited = Array.from({ length: N }, () =>
  Array.from({ length: M }, () => 0),
);

const dist = [
  [1, 0],
  [-1, 0],
  [0, 1],
  [0, -1],
];

let infectionList = [];

input.forEach((col, r) =>
  col.split(' ').forEach((v, c) => {
    if (v === '-1') {
      visited[r][c] = -1;
    } else if (v !== '0') {
      visited[r][c] = parseInt(v);
      infectionList.push([parseInt(v), r, c]);
    }
  }),
);
const isIn = (x, y) => {
  return 0 <= x && x < N && 0 <= y && y < M;
};

const bfs = () => {
  const tmpInfectionList = [];

  for ([num, r, c] of infectionList) {
    if (visited[r][c] === 3) continue;
    for (let i = 0; i < 4; i++) {
      const nx = r + dist[i][0];
      const ny = c + dist[i][1];

      if (!isIn(nx, ny) || visited[nx][ny] !== 0) continue;
      tmpInfectionList.push([num, nx, ny]);
    }
  }

  infectionList = [];
  for ([num, r, c] of tmpInfectionList) {
    if (visited[r][c] !== 0 && visited[r][c] !== num) {
      visited[r][c] = 3;
    } else {
      infectionList.push([num, r, c]);
      visited[r][c] = num;
    }
  }
};

const solution = () => {
  while (infectionList.length > 0) {
    bfs();
  }

  let one = 0;
  let two = 0;
  let three = 0;
  visited.forEach(r => {
    r.forEach(c => {
      if (c === 1) one++;
      else if (c === 2) two++;
      else if (c === 3) three++;
    });
  });
  console.log(one + ' ' + two + ' ' + three);
};

solution();
