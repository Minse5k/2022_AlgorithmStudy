let N, C, M;
const input = [];

const solution = () => {
  const weight = Array.from({ length: N + 1 }, () => 0);
  input.sort((a, b) => {
    if (a.end === b.end) {
      return a.st - b.st;
    }
    return a.end - b.end;
  });
  let ans = 0;

  input.forEach(({ st, end, size }) => {
    let maxCnt = 0;
    for (let j = st; j < end; j++) {
      maxCnt = Math.max(weight[j], maxCnt);
    }
    const val = Math.min(size, C - maxCnt);
    ans += val;
    for (let j = st; j < end; j++) {
      weight[j] += val;
    }
  });

  console.log(ans);
};

require('readline')
  .createInterface(process.stdin, process.stdout)
  .on('line', line => {
    if (!N) {
      [N, C] = line.split(' ').map(v => parseInt(v));
      return;
    }
    if (!M) {
      M = parseInt(line);
      return;
    }
    const [st, end, size] = line.split(' ').map(v => parseInt(v));
    input.push({ st, end, size });
  })
  .on('close', () => {
    solution();
    process.exit();
  });
