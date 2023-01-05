const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");
const N = parseInt(input[0]);
const snow = input[1].split(" ").map((v) => parseInt(v));

snow.sort((a, b) => a - b);

let ans = Infinity;

for (let i = 0; i < N - 3; i++) {
  for (let j = i + 3; j < N; j++) {
    let left = i + 1;
    let right = j - 1;

    while (left < right) {
      const elsa = snow[i] + snow[j];
      const anna = snow[left] + snow[right];
      const diff = anna - elsa;

      ans = Math.min(ans, Math.abs(diff));

      if (diff > 0) {
        right--;
      } else {
        left++;
      }
    }
  }
}

console.log(ans);
