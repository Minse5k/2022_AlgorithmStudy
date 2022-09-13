const fs = require('fs');
const filePath =
  process.platform === 'linux' ? '/dev/stdin' : '../../input.txt';
const input = fs.readFileSync(filePath).toString().split('\n');
const [M, N] = input
  .shift()
  .split(' ')
  .map(v => parseInt(v));

const dist = [
  [1, 0],
  [-1, 0],
  [0, 1],
  [0, -1],
];

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
      this.storage['0'] = value;
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

const thingArray = [];
const start = [];
const end = [];
const map = input.map((v, i) =>
  v.split('').map((c, j) => {
    if (c === 'X') {
      thingArray.push([i, j]);
      return '.';
    } else if (c === 'S') {
      start.push([i, j]);
      return '.';
    } else if (c === 'E') {
      end.push([i, j]);
      return '.';
    }
    return c;
  }),
);

const isIn = (x, y) => {
  return 0 <= x && x < N && 0 <= y && y < M;
};

const getPermutations = (arr, selectNumber) => {
  const results = [];
  if (selectNumber === 1) return arr.map(el => [el]);

  arr.forEach((fixed, index, origin) => {
    const rest = [...origin.slice(0, index), ...origin.slice(index + 1)];
    const permutations = getPermutations(rest, selectNumber - 1);
    const attached = permutations.map(el => [fixed, ...el]);
    results.push(...attached);
  });

  return results;
};

const bfs = (st, ed) => {
  const visited = Array.from({ length: N }, () =>
    Array.from({ length: M }, () => false),
  );
  const q = new Queue();
  visited[st[0]][st[1]] = true;
  q.enqueue([...st, 0]);
  while (q.len() > 0) {
    const len = q.len();
    for (let l = 0; l < len; l++) {
      const [x, y, cnt] = q.dequeue();

      if (x === ed[0] && y === ed[1]) {
        return cnt;
      }

      for (let i = 0; i < 4; i++) {
        const nx = x + dist[i][0];
        const ny = y + dist[i][1];

        if (!isIn(nx, ny) || map[nx][ny] !== '.' || visited[nx][ny]) continue;
        visited[nx][ny] = true;
        q.enqueue([nx, ny, cnt + 1]);
      }
    }
  }
};
const solution = () => {
  let min = Infinity;

  getPermutations(thingArray, thingArray.length).forEach(arr => {
    let dist = 0;
    arr.forEach((destination, idx) => {
      if (idx === 0) {
        dist += bfs(start[0], destination);
      } else {
        dist += bfs(arr[idx - 1], destination);
      }
    });
    dist += bfs(end[0], arr[arr.length - 1]);
    min = min > dist ? dist : min;
  });

  console.log(min === Infinity ? bfs(start[0], end[0]) : min);
};

solution();
