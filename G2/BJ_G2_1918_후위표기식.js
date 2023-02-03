const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");

const solution = () => {
  let ans = "";
  const stack = [];
  for (const char of input[0]) {
    if ("A" <= char && char <= "Z") {
      ans += char;
    } else if (char === "(") {
      stack.push(char);
    } else if (char === ")") {
      while (stack.length > 0 && stack[stack.length - 1] !== "(") {
        ans += stack.pop();
      }
      stack.pop();
    } else {
      while (stack.length > 0 && priority(stack[stack.length - 1]) >= priority(char)) {
        ans += stack.pop();
      }
      stack.push(char);
    }
  }
  while (stack.length > 0) {
    ans += stack.pop();
  }
  console.log(ans);
};

const priority = (char) => {
  if (char === "(" || char === ")") return 0;
  else if (char === "+" || char === "-") return 1;
  else return 2;
};

solution();
