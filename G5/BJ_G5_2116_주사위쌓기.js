const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");
const N = parseInt(input.shift());
const dice = input.map((r) => r.split(" ").map((v) => parseInt(v)));
const facToFace = {
  0: 5,
  1: 3,
  2: 4,
  3: 1,
  4: 2,
  5: 0,
};
let max = 0;

const dfs = (diceIdx, topNum, sum) => {
  if (diceIdx === N) {
    max = Math.max(sum, max);
    return;
  }
  const bottomIdx = dice[diceIdx].findIndex((v) => v === topNum);
  const topIdx = facToFace[bottomIdx];

  const maxNum = Math.max(
    ...dice[diceIdx].filter((v, idx) => {
      if (idx !== bottomIdx && idx !== topIdx) return v;
    })
  );
  dfs(diceIdx + 1, dice[diceIdx][topIdx], sum + maxNum);
};
const solution = () => {
  dice[0].forEach((_, idx) => {
    const bottomIdx = idx;
    const topIdx = facToFace[bottomIdx];
    const maxNum = Math.max(
      ...dice[0].filter((v, idx) => {
        if (idx !== bottomIdx && idx !== topIdx) return v;
      })
    );
    dfs(1, dice[0][topIdx], maxNum);
  });
  console.log(max);
};

solution();
