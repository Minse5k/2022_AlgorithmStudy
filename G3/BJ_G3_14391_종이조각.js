let N, M;
const input = [];
let map = [];
let visited = [];
let max = 0;

const dfs = (x, y, sum) => {
  if (x >= N || y >= M || visited[x][y]) return;
  visited[x][y] = true;
  //가로
  if (map[x][y] === 1) {
    if (y + 1 < M && map[x][y] === map[x][y + 1] && !visited[x][y + 1]) {
      sum = dfs(x, y + 1, sum * 10 + input[x][y]);
    } else return sum * 10 + input[x][y];
  } else {
    if (x + 1 < N && map[x][y] === map[x + 1][y] && !visited[x + 1][y]) {
      sum = dfs(x + 1, y, sum * 10 + input[x][y]);
    } else return sum * 10 + input[x][y];
  }
  return sum;
};

const getSum = () => {
  let sum = 0;
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < M; j++) {
      if (!visited[i][j]) {
        sum += dfs(i, j, 0);
      }
    }
  }
  return sum;
};

const solution = idx => {
  if (idx === N * M) {
    visited = Array.from({ length: N }, () =>
      Array.from({ length: M }, () => false),
    );
    max = Math.max(max, getSum());
    return;
  }

  map[parseInt(idx / M)][parseInt(idx % M)] = 1;
  solution(idx + 1);
  map[parseInt(idx / M)][parseInt(idx % M)] = 0;
  solution(idx + 1);
};

require('readline')
  .createInterface(process.stdin, process.stdout)
  .on('line', line => {
    if (!N) {
      [N, M] = line.split(' ').map(v => parseInt(v));
      return;
    }
    input.push(line.split('').map(v => parseInt(v)));
  })
  .on('close', () => {
    map = Array.from({ length: N }, () =>
      Array.from(
        {
          length: M,
        },
        () => 0,
      ),
    );
    solution(0);
    console.log(max);
    process.exit();
  });
