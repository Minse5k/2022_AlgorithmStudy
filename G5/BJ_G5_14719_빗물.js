const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");
const WALL = 1;
const BLANK = 0;
const [N, M] = input[0].split(" ").map((v) => parseInt(v));
const map = Array.from({ length: N }, () => Array.from({ length: M }, () => BLANK));

input[1].split(" ").forEach((v, j) => {
  const height = parseInt(v);
  for (let i = 0; i < height; i++) {
    map[i][j] = WALL;
  }
});

const solution = () => {
  let cnt = 0;
  for (let i = 0; i < N; i++) {
    let flag = false;
    let waterCnt = 0;
    for (let j = 0; j < M; j++) {
      if (map[i][j] === WALL && waterCnt === 0) {
        flag = true;
      } else if (map[i][j] === BLANK && flag === true) {
        waterCnt++;
      } else if (map[i][j] === WALL && flag === true) {
        cnt += waterCnt;
        waterCnt = 0;
      }
    }
  }
  console.log(cnt);
};

solution();
