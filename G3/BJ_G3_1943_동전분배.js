const { rawListeners } = require("process");
const { getSystemErrorMap } = require("util");
let N;
const answer = [];
const solution = (input) => {
  const dp = Array.from({ length: 100001 }, () => false);

  let sum = 0;
  for (let i = 0; i < N; i++) {
    const { key: k, value: v } = input[i];
    sum += k * v;
    for (let j = 1; j < v; j++) {
      dp[j * k] = true;
    }
  }

  if (sum % 2 == 1) {
    answer.push(0);
    return;
  } else if (dp[sum / 2]) {
    answer.push(1);
    return;
  }

  dp[0] = true;
  for (let i = 0; i < N; i++) {
    const { key: k, value: v } = input[i];

    for (let j = sum / 2; j >= k; j--) {
      if (dp[j - k]) {
        for (let x = 1; x <= v; x++) {
          dp[j - k + k * x] = true;
        }
      }
    }
  }
  answer.push(dp[sum / 2] ? 1 : 0);
  return;
};

let input = [];
let isN = false;
let cnt = 0;
require("readline")
  .createInterface(process.stdin, process.stdout)
  .on("line", (line) => {
    if (!isN && cnt === 0) {
      if (N !== undefined) {
        solution(input);
      }
      N = parseInt(line);
      cnt = N;
      isN = true;
      input = [];
      return;
    } else {
      const [k, v] = line.split(" ").map((v) => parseInt(v));
      input.push({
        key: k,
        value: v,
      });
      cnt--;
      isN = false;
    }
  })
  .on("close", () => {
    solution(input);
    console.log(answer.join("\n"));
    process.exit();
  });
