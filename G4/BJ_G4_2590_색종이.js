const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");

const paperNumArr = Array.from({ length: 7 }, () => 0);

const setPaper = () => {
  let paperCnt = 0;
  for (let i = 1; i <= 6; i++) {
    paperNumArr[i] = parseInt(input.shift());
    paperCnt += paperNumArr[i];
  }

  return paperCnt;
};

const isAttachPaper = (x, y, size, paperArr) => {
  for (let i = x; i < x + size; i++) {
    for (let j = y; j < y + size; j++) {
      if (!paperArr[i][j]) {
        return false;
      }
    }
  }
  return true;
};

const setAttachPaper = (x, y, size, paperArr) => {
  for (let i = x; i < x + size; i++) {
    for (let j = y; j < y + size; j++) {
      paperArr[i][j] = false;
    }
  }
};

const attachPaper = (paperArr, size) => {
  for (let i = 0; i <= 6 - size; i++) {
    for (let j = 0; j <= 6 - size; j++) {
      if (!paperArr[i][j]) continue;
      if (isAttachPaper(i, j, size, paperArr)) {
        setAttachPaper(i, j, size, paperArr);
        return true;
      }
    }
  }
  return false;
};

const solution = () => {
  paperCnt = setPaper();
  let cnt = 0;

  while (paperCnt > 0) {
    const paperArr = Array.from({ length: 6 }, () => Array.from({ length: 6 }, () => true));
    for (let i = 6; i >= 1; i--) {
      while (paperNumArr[i] !== 0 && attachPaper(paperArr, i)) {
        paperNumArr[i]--;
        paperCnt--;
      }
    }
    cnt++;
  }
  console.log(cnt);
};

solution();
