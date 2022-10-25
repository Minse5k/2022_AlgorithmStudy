const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().trim().split("\n");
let idx = 0;
const ans = [];
const solution = (num, command) => {
  const stack = [num];
  for (const com of command) {
    if (com[0] === "N") {
      const [, num] = com.split(" ");
      stack.push(parseInt(num));
    } else {
      let n;
      let m;
      switch (com) {
        case "POP":
          if (stack.length < 1) {
            return "ERROR";
          }
          stack.pop();
          break;
        case "INV":
          if (stack.length < 1) {
            return "ERROR";
          }
          n = stack.pop();
          stack.push(-n);
          break;
        case "DUP":
          if (stack.length < 1) {
            return "ERROR";
          }
          n = stack[stack.length - 1];
          stack.push(n);
          break;
        case "SWP":
          if (stack.length < 2) {
            return "ERROR";
          }
          n = stack.pop();
          m = stack.pop();
          stack.push(n);
          stack.push(m);
          break;
        case "ADD":
          if (stack.length < 2) {
            return "ERROR";
          }
          n = stack.pop();
          m = stack.pop();
          if (Math.abs(n + m) > 1000000000) return "ERROR";
          stack.push(n + m);
          break;
        case "SUB":
          if (stack.length < 2) {
            return "ERROR";
          }
          n = stack.pop();
          m = stack.pop();
          if (Math.abs(m - n) > 1000000000) return "ERROR";
          stack.push(m - n);
          break;
        case "MUL":
          if (stack.length < 2) {
            return "ERROR";
          }
          n = stack.pop();
          m = stack.pop();
          if (Math.abs(n * m) > 1000000000) return "ERROR";
          stack.push(m * n);
          break;
        case "DIV":
          if (stack.length < 2) {
            return "ERROR";
          }
          n = stack.pop();
          m = stack.pop();
          stack.push(parseInt(m / n));
          break;
        case "MOD":
          if (stack.length < 2) {
            return "ERROR";
          }
          n = stack.pop();
          m = stack.pop();
          const num = Math.abs(m % n);
          stack.push(m < 0 ? -num : num);
          break;
      }
    }
  }
  return stack.length === 1 ? stack.pop() : "ERROR";
};

while (input[idx] !== "QUIT") {
  const command = [];
  while (input[idx] !== "END\r") {
    command.push(input[idx].slice(0, input[idx++].length - 1));
  }
  idx++;
  const N = parseInt(input[idx++]);
  for (let i = 0; i < N; i++) {
    const num = parseInt(input[idx++]);
    ans.push(solution(num, command));
  }
  idx++;
  ans.push("");
}

console.log(ans.slice(0, ans.length - 1).join("\n"));
