const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");

const [N, M] = input
  .shift()
  .split(" ")
  .map((v) => parseInt(v));

const printAnswer = (map) => {
  map.forEach((v) => console.log(v.join(" ")));
};

const solution = () => {
  const array = Array.from({ length: 2 * N - 1 }, () => 1);
  const map = Array.from({ length: N }, () => Array.from({ length: N }, () => 0));
  for (let n = 0; n < M; n++) {
    const [zero, one, two] = input[n].split(" ").map((v) => parseInt(v));
    for (let i = zero; i < zero + one; i++) {
      array[i] += 1;
    }
    for (let i = zero + one; i < zero + one + two; i++) {
      array[i] += 2;
    }
  }
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      map[i][j] = j === 0 ? array[N - 1 - i] : array[N - 1 + j];
    }
  }
  printAnswer(map);
};

solution();
