const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");

const N = parseInt(input.shift());
const ANSWER_NUM = "012".repeat(N / 3);
let numArr = input
  .shift()
  .split(" ")
  .map((v) => parseInt(v));
const orderArr = input
  .shift()
  .split(" ")
  .map((v) => parseInt(v));

const set = new Set();

const suffleCard = () => {
  const tmpArr = Array.from({ length: N }, () => 0);

  numArr.forEach((v, idx) => {
    tmpArr[orderArr[idx]] = v;
  });

  numArr = [...tmpArr];
};

const solution = () => {
  let num = numArr.join("");
  if (num === ANSWER_NUM) {
    console.log(0);
    return;
  }

  let cnt = 0;
  while (!set.has(num)) {
    cnt++;
    set.add(num);
    suffleCard();
    num = numArr.join("");
    if (num === ANSWER_NUM) {
      console.log(cnt);
      return;
    }
  }

  console.log(-1);
};

solution();
