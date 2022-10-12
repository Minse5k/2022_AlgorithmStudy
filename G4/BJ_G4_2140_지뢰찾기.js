const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");

const N = parseInt(input.shift());
const dist = [
  [1, 0],
  [0, 1],
  [-1, 0],
  [0, -1],
  [1, 1],
  [1, -1],
  [-1, 1],
  [-1, -1],
];

const getMap = () => {
  const arr = [];
  input.forEach((v) => arr.push(v.split("").slice(0, N)));
  return arr;
};

const solution = () => {
  if (N <= 2) {
    console.log(0);
    return;
  }
  const map = getMap();
  let cnt = (N - 2) * (N - 2);

  for (let i = 1; i < N - 1; i++) {
    for (let j = 1; j < N - 1; j++) {
      let isZero = false;
      for (let d = 0; d < 8; d++) {
        const nx = i + dist[d][0];
        const ny = j + dist[d][1];
        if (parseInt(map[nx][ny]) === 0) {
          cnt--;
          isZero = true;
          break;
        }
      }
      if (!isZero) {
        dist.forEach(([dx, dy]) => {
          const nx = i + dx;
          const ny = j + dy;
          if (map[nx][ny] !== "#") map[nx][ny] -= 1;
        });
      }
    }
  }
  console.log(cnt);
};

solution();
