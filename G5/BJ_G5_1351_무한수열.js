const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const [N, P, Q] = fs
  .readFileSync(filePath)
  .toString()
  .split(" ")
  .map((v) => parseInt(v));

const map = new Map();

const dfs = (num) => {
  if (num === 0) return 1;
  if (map.has(num)) return map.get(num);

  const left = Math.floor(num / P);
  const right = Math.floor(num / Q);
  map.set(num, dfs(left) + dfs(right));
  return map.get(num);
};

console.log(dfs(N));
