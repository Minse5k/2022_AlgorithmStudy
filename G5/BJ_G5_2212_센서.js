const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");

const N = parseInt(input[0]);
const K = parseInt(input[1]);
const arr = input[2].split(" ").map((v) => parseInt(v));

const solution = () => {
  arr.sort((a, b) => a - b);
  const diffArr = [];
  for (let i = 1; i < N; i++) {
    diffArr.push(arr[i] - arr[i - 1]);
  }
  diffArr.sort((a, b) => b - a);
  let sum = 0;
  for (let i = K - 1; i < N - 1; i++) {
    sum += diffArr[i];
  }
  console.log(sum);
};

solution();
