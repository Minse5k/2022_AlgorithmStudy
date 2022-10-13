const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");
const N = parseInt(input.shift());

const arr = [];
input.forEach((v) => {
  const [st, end] = v.split(" ").map((v) => parseInt(v));
  arr.push({ node: st, cnt: +1 });
  arr.push({ node: end, cnt: -1 });
});
arr.sort((a, b) => {
  if (a.node === b.node) return a.cnt - b.cnt;
  return a.node - b.node;
});
const { max } = arr.reduce(
  (acc, cur) => {
    const cnt = acc.cnt + cur.cnt;
    return { cnt, max: Math.max(acc.max, cnt) };
  },
  { cnt: 0, max: 0 }
);

console.log(max);
