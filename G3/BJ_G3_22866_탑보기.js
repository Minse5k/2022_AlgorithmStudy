const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");

const N = parseInt(input[0]);
const arr = [0];
arr.push(...input[1].split(" ").map((v) => parseInt(v)));
const ans = Array.from({ length: N + 1 }, () => ({ len: Infinity, idx: 0, cnt: 0 }));

const getLeft = () => {
  const stack = [];
  for (let i = 1; i <= N; i++) {
    const height = arr[i];
    while (stack.length > 0 && stack[stack.length - 1].h <= height) stack.pop();
    ans[i].cnt += stack.length;

    if (stack.length > 0) {
      const stackLastIdx = stack.length - 1;
      const len = Math.abs(stack[stackLastIdx].idx - i);
      if (len < ans[i].len) {
        ans[i].len = len;
        ans[i].idx = stack[stackLastIdx].idx;
      } else if (len === ans[i].len && ans[i].idx > stack[stackLastIdx].idx) {
        ans[i].idx = stack[stackLastIdx].idx;
      }
    }

    stack.push({ idx: i, h: height });
  }
};

const getRight = () => {
  const stack = [];
  for (let i = N; i >= 1; i--) {
    const height = arr[i];

    while (stack.length > 0 && stack[stack.length - 1].h <= height) stack.pop();
    ans[i].cnt += stack.length;

    if (stack.length > 0) {
      const stackLastIdx = stack.length - 1;
      const len = Math.abs(stack[stackLastIdx].idx - i);
      if (len < ans[i].len) {
        ans[i].len = len;
        ans[i].idx = stack[stackLastIdx].idx;
      } else if (len === ans[i].len && ans[i].idx > stack[stackLastIdx].idx) {
        ans[i].idx = stack[stackLastIdx].idx;
      }
    }

    stack.push({ idx: i, h: height });
  }
};

const solution = () => {
  getLeft();
  getRight();

  let str = "";
  for (let i = 1; i <= N; i++) {
    if (ans[i].len === Infinity) {
      str += "0\n";
    } else {
      str += `${ans[i].cnt} ${ans[i].idx}\n`;
    }
  }
  console.log(str);
};

solution();
