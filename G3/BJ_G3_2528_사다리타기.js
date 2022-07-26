let N, L;
const input = [];
let cur = 0;

const solution = () => {
  let time = 0;

  while (true) {
    moveUp();
    move();
    if (cur === N - 1) break;
    time++;
  }
  console.log(time);
};

const moveUp = () => {
  while (cur < N - 1) {
    const now = input[cur];
    const next = input[cur + 1];

    if (
      (now.end >= next.st && now.end <= next.end) ||
      (now.st >= next.st && now.st <= next.end) ||
      (now.st >= next.st && now.end <= next.end) ||
      (now.st < next.st && now.end > next.st)
    ) {
      cur++;
    } else break;
  }
};

const move = () => {
  for (let i = cur; i < N; i++) {
    const now = input[i];
    if (now.d == 0) {
      now.st++;
      now.end++;
      if (now.end === L) {
        now.dir = 1;
      }
    } else {
      now.st--;
      now.end--;
      if (now.end === 0) {
        now.dir = 0;
      }
    }
  }
};

require('readline')
  .createInterface(process.stdin, process.stdout)
  .on('line', line => {
    if (!N && !L) {
      [N, L] = line.split(' ').map(v => parseInt(v));
      return;
    }
    const [len, d] = line.split(' ').map(v => parseInt(v));
    input.push(d === 0 ? { st: 0, end: len, d } : { st: L - len, end: L, d });
  })
  .on('close', () => {
    solution();
    process.exit();
  });
