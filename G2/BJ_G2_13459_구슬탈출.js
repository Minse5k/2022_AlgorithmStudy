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

const [N, M] = input.shift().split(" ");
const map = Array.from({ length: N }, () => Array.from({ length: M }, () => ""));
let finish = { x: 0, y: 0 };
let redBall = { x: 0, y: 0 };
let blueBall = { x: 0, y: 0 };

const dist = [
  [-1, 0],
  [0, 1],
  [1, 0],
  [0, -1],
];
const RED = 0;
const BLUE = 1;
const TOP = 0;
const RIGHT = 1;
const DOWN = 2;
const LEFT = 3;

const createMap = () => {
  input.forEach((row, i) => {
    const arr = row.split("").slice(0, M);
    arr.forEach((v, j) => {
      map[i][j] = v;
      if (v === "R") {
        redBall = { x: i, y: j };
        map[i][j] = ".";
      } else if (v === "B") {
        blueBall = { x: i, y: j };
        map[i][j] = ".";
      } else if (v === "O") {
        finish = { x: i, y: j };
      }
    });
  });
};

const isFinish = (ball) => {
  return ball.x === finish.x && ball.y === finish.y;
};

const isMoveRedFirst = (red, blue, dir) => {
  return (
    (dir === TOP && red.x < blue.x) ||
    (dir === RIGHT && red.y > blue.y) ||
    (dir === DOWN && red.x > blue.x) ||
    (dir === LEFT && red.y < blue.y)
  );
};

const moveBall = (ball, subBall, dir) => {
  while (true) {
    const nx = ball.x + dist[dir][0];
    const ny = ball.y + dist[dir][1];
    const nv = map[nx][ny];
    if (nv === "O") {
      ball.x = nx;
      ball.y = ny;
      break;
    } else if ((nx === subBall.x && ny === subBall.y) || nv === "#") break;
    else if (nv === ".") {
      ball.x = nx;
      ball.y = ny;
    }
  }
};

const bfs = () => {
  const q = new Queue();
  const visited = Array.from({ length: N }, () =>
    Array.from({ length: M }, () =>
      Array.from({ length: N }, () => Array.from({ length: M }, () => false))
    )
  );
  let cnt = 0;

  visited[redBall.x][redBall.y][blueBall.x][blueBall.y] = true;
  q.enqueue([redBall, blueBall]);

  while (q.len() !== 0 && cnt < 10) {
    const len = q.len();
    for (let l = 0; l < len; l++) {
      const now = q.dequeue();

      for (let d = 0; d < 4; d++) {
        let red = { ...now[RED] };
        let blue = { ...now[BLUE] };
        if (isMoveRedFirst(red, blue, d)) {
          moveBall(red, blue, d);
          moveBall(blue, red, d);
        } else {
          moveBall(blue, red, d);
          moveBall(red, blue, d);
        }
        if (isFinish(blue)) continue;
        if (isFinish(red)) {
          console.log(1);
          return;
        }
        if (visited[red.x][red.y][blue.x][blue.y]) continue;
        visited[red.x][red.y][blue.x][blue.y] = true;
        q.enqueue([red, blue]);
      }
    }
    cnt++;
  }
  console.log(0);
};

const solution = () => {
  createMap();
  bfs();
};

solution();
