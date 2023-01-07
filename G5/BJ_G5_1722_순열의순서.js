const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");
const N = parseInt(input[0]);
const [K, ...per] = input[1].split(" ").map((v) => parseInt(v));

let cnt = 0;
let finish = false;
const permutation = (size, arr, visited) => {
  if (finish) return;
  if (size === N) {
    cnt++;
    if (K === 1) {
      if (cnt === per[0]) {
        console.log(arr.join(" "));
        finish = true;
      }
    } else {
      let isEqual = true;
      for (let i = 0; i < N; i++) {
        if (arr[i] !== per[i]) {
          isEqual = false;
          break;
        }
      }
      if (isEqual) {
        console.log(cnt);
        finish = true;
      }
    }
    return;
  }
  for (let i = 1; i <= N; i++) {
    if (visited[i]) continue;
    arr[size] = i;
    visited[i] = true;
    permutation(size + 1, arr, visited);
    visited[i] = false;
  }
};

const solution = () => {
  permutation(
    0,
    Array.from({ length: N }),
    Array.from({ length: N + 1 }, () => false)
  );
};

solution();
