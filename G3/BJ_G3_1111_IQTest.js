const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");
const N = parseInt(input[0]);

const arr = input[1].split(" ").map((v) => parseInt(v));
let a, b;

const getAnswer = (a, b) => {
  for (let i = 1; i < N; i++) {
    if (arr[i] !== arr[i - 1] * a + b) {
      console.log("B");
      return;
    }
  }
  console.log(arr[N - 1] * a + b);
};

const solution = () => {
  if (N === 1) console.log("A");
  else if (N === 2) {
    if (arr[0] === arr[1]) console.log(arr[0]);
    else console.log("A");
  } else {
    if (arr[0] === arr[1]) {
      a = 0;
      b = arr[1];
    } else {
      a = parseInt((arr[2] - arr[1]) / (arr[1] - arr[0]));
      b = arr[1] - arr[0] * a;
    }

    getAnswer(a, b);
  }
};

solution();
