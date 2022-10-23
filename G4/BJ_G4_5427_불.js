const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");

class Queue {
  constructor() {
    this.storage = {};
    this.front = 0;
    this.rear = 0;
  }

  len() {
    if (this.storage[this.rear] === undefined) {
      return 0;
    } else {
      return this.rear - this.front + 1;
    }
  }

  enqueue(value) {
    if (this.len() === 0) {
      this.storage["0"] = value;
    } else {
      this.rear += 1;
      this.storage[this.rear] = value;
    }
  }

  dequeue() {
    const temp = this.storage[this.front];
    delete this.storage[this.front];
    if (this.front === this.rear) {
      this.front = 0;
      this.rear = 0;
    } else {
      this.front += 1;
    }
    return temp;
  }
}

let N, M;
let map = [];
let sangKeunPos = new Queue();
let fire = new Queue();
const ans = [];
const dist = [
  [1, 0],
  [-1, 0],
  [0, 1],
  [0, -1],
];

const isIn = (x, y) => {
  return 0 <= x && x < N && 0 <= y && y < M;
};

const bfs = () => {
  const visited = Array.from({ length: N }, () => Array.from({ length: M }, () => false));
  let time = 1;

  visited[sangKeunPos.storage[0][0]][sangKeunPos.storage[0][1]] = true;

  while (sangKeunPos.len() > 0) {
    const fireLen = fire.len();

    for (let i = 0; i < fireLen; i++) {
      const [x, y] = fire.dequeue();

      for (let j = 0; j < 4; j++) {
        const [nx, ny] = [x + dist[j][0], y + dist[j][1]];
        if (!isIn(nx, ny) || map[nx][ny] !== ".") continue;

        map[nx][ny] = "*";
        fire.enqueue([nx, ny]);
      }
    }
    const sangKeunLen = sangKeunPos.len();

    for (let i = 0; i < sangKeunLen; i++) {
      const [x, y] = sangKeunPos.dequeue();

      for (let j = 0; j < 4; j++) {
        const [nx, ny] = [x + dist[j][0], y + dist[j][1]];
        if (!isIn(nx, ny)) return time;
        if (visited[nx][ny] || map[nx][ny] !== ".") continue;
        visited[nx][ny] = true;
        sangKeunPos.enqueue([nx, ny]);
      }
    }
    time++;
  }

  return "IMPOSSIBLE";
};

const solution = () => {
  const TC = parseInt(input.shift());
  for (let t = 0; t < TC; t++) {
    [M, N] = input
      .shift()
      .split(" ")
      .map((v) => parseInt(v));
    map = [];
    fire = new Queue();
    sangKeunPos = new Queue();
    for (let i = 0; i < N; i++) {
      map.push(input.shift().split(""));
      for (let j = 0; j < M; j++) {
        if (map[i][j] === "@") {
          sangKeunPos.enqueue([i, j]);
          map[i][j] = ".";
        } else if (map[i][j] === "*") {
          fire.enqueue([i, j]);
        }
      }
    }

    ans.push(bfs());
  }
  console.log(ans.join("\n"));
};

solution();
