let M;
const moveShark = (N, map, shark, sharkDist, priorityDist) => {
  const dist = [[], [-1, 0], [1, 0], [0, -1], [0, 1]];

  for (let sharkNum = 1; sharkNum < shark.length; sharkNum++) {
    if (shark[sharkNum][0] === -1) continue;
    let isPriority = false;
    let tmpNx, tmpNy, tmpDist;
    for (let j = 1; j <= 4; j++) {
      const nowDist = priorityDist[sharkNum][sharkDist[sharkNum]][j];
      const nx = shark[sharkNum][0] + dist[nowDist][0];
      const ny = shark[sharkNum][1] + dist[nowDist][1];

      if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

      if (map[nx][ny] !== 0) {
        if (map[nx][ny][0] === sharkNum && !isPriority) {
          isPriority = true;
          tmpNx = nx;
          tmpNy = ny;
          tmpDist = nowDist;
        }
        continue;
      }
      shark[sharkNum][0] = nx;
      shark[sharkNum][1] = ny;
      sharkDist[sharkNum] = nowDist;
      isPriority = false;
      break;
    }
    if (isPriority) {
      shark[sharkNum][0] = tmpNx;
      shark[sharkNum][1] = tmpNy;
      sharkDist[sharkNum] = tmpDist;
    }
  }
};
const killShark = (shark) => {
  for (let i = 1; i < shark.length - 1; i++) {
    if (shark[i][0] === -1) continue;
    for (let j = i + 1; j < shark.length; j++) {
      if (shark[j][0] === -1) continue;
      if (shark[j][0] === shark[i][0] && shark[j][1] === shark[i][1]) {
        shark[j][0] = -1;
        shark[j][1] = -1;
        M--;
      }
    }
  }
};
const deleteSmell = (map, N) => {
  for (let i = 0; i < N; i++) {
    for (let j = 0; j < N; j++) {
      if (map[i][j][0] === 0) continue;
      map[i][j][1]--;
      if (map[i][j][1] === 0) map[i][j] = 0;
    }
  }
};
const createSmell = (map, shark, K) => {
  for (let sharkNum = 1; sharkNum < shark.length; sharkNum++) {
    if (shark[sharkNum][0] === -1) continue;
    const x = shark[sharkNum][0];
    const y = shark[sharkNum][1];

    map[x][y] = [sharkNum, K];
  }
};

const solution = (input) => {
  let i = 0;
  let N, K;
  [N, M, K] = input[i++].split(" ").map((v) => parseInt(v));

  const map = [];
  const shark = [];
  for (; i < 1 + N; i++) {
    map[i - 1] = input[i].split(" ").map((v, idx) => {
      const parsedInt = parseInt(v);
      if (v === "0") {
        return parsedInt;
      }
      shark[parsedInt] = [i - 1, idx];
      return 0;
    });
  }

  const sharkDist = [, ...input[i++].split(" ").map((v) => parseInt(v))];
  const priorityDist = [];
  let cnt = 0;
  let tmpArr = [[]];
  for (; i < N + 2 + 4 * M; i++, cnt++) {
    if (cnt % 4 === 0) {
      priorityDist.push(tmpArr);
      tmpArr = [[]];
    }
    tmpArr.push([, ...input[i].split(" ").map((v) => parseInt(v))]);
  }
  priorityDist.push(tmpArr);
  let time = 0;
  createSmell(map, shark, K);
  while (time++ < 1000) {
    moveShark(N, map, shark, sharkDist, priorityDist);
    killShark(shark);
    deleteSmell(map, N);
    createSmell(map, shark, K);
    if (M == 1) {
      console.log(time);
      return;
    }
  }
  console.log(-1);
};

const input = [];

require("readline")
  .createInterface(process.stdin, process.stdout)
  .on("line", (line) => {
    input.push(line);
  })
  .on("close", () => {
    solution(input);
    process.exit();
  });
