let N, M;
const input = [];
const BLACK_BLOCK = -1;
const RAINBOW_BLOCK = 0;
const EMPTY_BLOCK = -2;
const dist = [
  [1, 0],
  [-1, 0],
  [0, 1],
  [0, -1],
];
let ans = 0;
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

const removeBlock = group => {
  group.forEach(v => {
    input[v.x][v.y] = EMPTY_BLOCK;
  });
};

const isIn = (x, y) => {
  return 0 <= x && x < N && 0 <= y && y < N;
};

const bfs = (x, y, isGroup) => {
  const q = new Queue();
  q.enqueue({ x, y });
  const visited = Array.from({ length: N }, () =>
    Array.from({ length: N }, () => false),
  );
  visited[x][y] = true;
  isGroup[x][y] = true;
  const nowColor = input[x][y];
  const groupSize = { blockCnt: 1, rainbowCnt: 0, x, y };
  const group = [{ x, y }];
  while (q.len() > 0) {
    const now = q.dequeue();
    for (let i = 0; i < 4; i++) {
      const nx = now.x + dist[i][0];
      const ny = now.y + dist[i][1];

      if (
        !isIn(nx, ny) ||
        visited[nx][ny] ||
        (input[nx][ny] !== RAINBOW_BLOCK && input[nx][ny] !== nowColor)
      )
        continue;

      groupSize.blockCnt++;
      visited[nx][ny] = true;
      group.push({ x: nx, y: ny });
      q.enqueue({ x: nx, y: ny });
      if (input[nx][ny] !== RAINBOW_BLOCK) {
        isGroup[nx][ny] = true;
      } else {
        if (groupSize.x < nx) {
          groupSize.x = nx;
          groupSize.y = ny;
        } else if (groupSize.x === nx && groupSize.y < ny) {
          groupSize.x = nx;
          groupSize.y = ny;
        }
        groupSize.rainbowCnt++;
      }
    }
  }
  return [group, groupSize];
};

const getBlockGroup = () => {
  const maxGroupSize = { blockCnt: 0, rainbowCnt: 0, x: 0, y: 0 };
  let maxGroup = [];
  const visited = Array.from({ length: N }, () =>
    Array.from({ length: N }, () => false),
  );
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (
        input[i][j] === EMPTY_BLOCK ||
        input[i][j] === BLACK_BLOCK ||
        input[i][j] === RAINBOW_BLOCK ||
        visited[i][j]
      )
        continue;

      const [blockGroup, blockGroupSize] = bfs(i, j, visited);

      if (maxGroupSize.blockCnt < blockGroupSize.blockCnt) {
        maxGroupSize.blockCnt = blockGroupSize.blockCnt;
        maxGroupSize.rainbowCnt = blockGroupSize.rainbowCnt;
        maxGroup = blockGroup;
      } else if (maxGroupSize.blockCnt === blockGroupSize.blockCnt) {
        if (maxGroupSize.rainbowCnt < blockGroupSize.rainbowCnt) {
          maxGroupSize.blockCnt = blockGroupSize.blockCnt;
          maxGroupSize.rainbowCnt = blockGroupSize.rainbowCnt;
          maxGroup = blockGroup;
        } else if (maxGroupSize.rainbowCnt === blockGroupSize.rainbowCnt) {
          if (maxGroupSize.x < blockGroupSize.x) {
            maxGroupSize.blockCnt = blockGroupSize.blockCnt;
            maxGroupSize.rainbowCnt = blockGroupSize.rainbowCnt;
            maxGroup = blockGroup;
          } else if (
            maxGroupSize.x === blockGroupSize.x &&
            maxGroupSize.y < blockGroupSize.y
          ) {
            maxGroupSize.blockCnt = blockGroupSize.blockCnt;
            maxGroupSize.rainbowCnt = blockGroupSize.rainbowCnt;
            maxGroup = blockGroup;
          }
        }
      }
    }
  }

  if (maxGroupSize.blockCnt >= 2) {
    removeBlock(maxGroup);
    ans += maxGroupSize.blockCnt * maxGroupSize.blockCnt;
    return true;
  }
  return false;
};

const moveDown = () => {
  for (let j = 0; j < N; j++) {
    let bottom = N - 1;
    for (let i = N - 1; i >= 0; i--) {
      if (input[i][j] === EMPTY_BLOCK) continue;
      if (input[i][j] === BLACK_BLOCK) {
        bottom = i - 1;
      } else {
        const tmp = input[i][j];
        input[i][j] = EMPTY_BLOCK;
        input[bottom--][j] = tmp;
      }
    }
  }
};

const rotate = () => {
  const rotTmp = Array.from({ length: N }, () =>
    Array.from({ length: N }, () => 0),
  );

  input.forEach((r, i) => {
    r.forEach((c, j) => {
      rotTmp[N - 1 - j][i] = c;
    });
  });

  rotTmp.forEach((r, i) => {
    r.forEach((c, j) => {
      input[i][j] = c;
    });
  });
};

const solution = () => {
  while (getBlockGroup()) {
    moveDown();
    rotate();
    moveDown();
  }
};

require('readline')
  .createInterface(process.stdin, process.stdout)
  .on('line', line => {
    if (!N) {
      [N, M] = line.split(' ').map(v => parseInt(v));
      return;
    }

    input.push(line.split(' ').map(v => parseInt(v)));
  })
  .on('close', () => {
    solution();
    console.log(ans);
    process.exit();
  });
