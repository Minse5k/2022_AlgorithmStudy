const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");

const N = parseInt(input[0]);
let arr = [];
for (let i = 1; i <= N; i++) {
  arr.push(...input[i].split(" "));
}
arr = arr.map((v) => v.split("-"));
const sortArr = [...arr];
sortArr.sort((a, b) => {
  if (a[0] === b[0]) {
    return parseInt(a[1]) - parseInt(b[1]);
  } else if (a[0] > b[0]) {
    return a[0] > b[0];
  } else {
    return -1;
  }
});
const stack = [];
let isGood = false;
let j = 0;
let i = 0;
while (i < 5 * N && j < 5 * N) {
  if (arr[i] == sortArr[j]) i++, j++;
  else if (stack.length > 0 && stack[stack.length - 1] == sortArr[j]) stack.pop(), j++;
  else stack.push(arr[i++]);
}

while (stack.length > 0) {
  if (stack[stack.length - 1] !== sortArr[j++]) {
    isGood = true;
    break;
  }
  stack.pop();
}

if (isGood) console.log("BAD");
else console.log("GOOD");
