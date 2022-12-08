const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");
const N = parseInt(input[0]);

const arr = [];
for (let i = 1; i <= N; i++) {
  arr.push(input[i].split(" ").map((v) => parseInt(v)));
}
arr.sort((a, b) => {
  return b[1] - a[1];
});
let time = arr[0][1];
for (let i = 0; i < N; i++) {
  const [t, s] = arr[i];
  time = Math.min(time, s);
  time -= t;
}
console.log(time < 0 ? -1 : time);
