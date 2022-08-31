const fs = require('fs');
const filePath =
  process.platform === 'linux' ? '/dev/stdin' : '../../input.txt';
const input = fs.readFileSync(filePath).toString().split('\n');
let map = [];
let max = -1;
const SIZE = 11;

const solution = () => {
  const TC = parseInt(input.shift());
  for (let t = 1; t <= TC; t++) {
    map = [];
    max = -1;
    for (let i = 1; i <= SIZE; i++) {
      map.push(
        input
          .shift()
          .split(' ')
          .map(v => parseInt(v)),
      );
    }
    combination(
      0,
      0,
      0,
      Array.from({ length: SIZE }, () => false),
    );
    console.log(max);
  }
};

const combination = (start, cnt, score, visited) => {
  if (cnt === SIZE) {
    max = Math.max(max, score);
    return;
  }

  for (let j = start; j < SIZE; j++) {
    for (let i = 0; i < SIZE; i++) {
      if (visited[i] || map[i][j] === 0) continue;
      visited[i] = true;
      combination(j + 1, cnt + 1, score + map[i][j], visited);
      visited[i] = false;
    }
  }
};

solution();
