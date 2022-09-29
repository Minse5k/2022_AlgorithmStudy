const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");
const N = parseInt(input.shift());

const map = [];
const teacher = [];
const dist = [
  [1, 0],
  [-1, 0],
  [0, 1],
  [0, -1],
];

const setMap = () => {
  input.forEach((v, i) => {
    const arr = v.split(" ");
    arr[N - 1] = arr[N - 1].slice(0, 1);
    map.push(arr);
    arr.forEach((c, j) => {
      if (c === "T") {
        teacher.push([i, j]);
      }
    });
  });
};

const isIn = (x, y) => {
  return 0 <= x && x < N && 0 <= y && y < N;
};

const isHideTeacher = () => {
  for ([x, y] of teacher) {
    for ([dx, dy] of dist) {
      let nx = x + dx;
      let ny = y + dy;

      while (isIn(nx, ny)) {
        if (map[nx][ny] === "S") return false;
        if (map[nx][ny] === "O") break;
        nx += dx;
        ny += dy;
      }
    }
  }

  return true;
};
const Permutation = (start, cnt) => {
  if (cnt === 3) {
    if (isHideTeacher()) {
      console.log("YES");
      process.exit();
    }
    return;
  }
  for (let i = start; i < N * N; i++) {
    const x = Number(i / N);
    const y = Number(i % N);
    if (map[x][y] !== "X") continue;

    map[x][y] = "O";
    Permutation(i + 1, cnt + 1);
    map[x][y] = "X";
  }
};

const solution = () => {
  setMap();
  Permutation(0, 0);
  console.log("NO");
};

solution();
