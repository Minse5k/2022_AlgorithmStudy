const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(filePath).toString().split("\n");
const TC = parseInt(input[0]);
let n = 1;
let ans = "";
const solution = () => {
  for (let t = 1; t <= TC; t++) {
    const S = Array.from({ length: 3 }, () => new Set());
    const T = Array.from({ length: 2 }, () => new Set());

    input[n++].split("").forEach((v, idx) => {
      switch (v) {
        case "0":
          S[0].add(idx);
          break;
        case "1":
          S[1].add(idx);
          break;
        case "?":
          S[2].add(idx);
          break;
      }
    });
    input[n++].split("").forEach((v, idx) => {
      switch (v) {
        case "0":
          T[0].add(idx);
          break;
        case "1":
          T[1].add(idx);
          break;
      }
    });
    if (S[1].size > T[1].size) {
      ans += "Case " + t + ": -1\n";
    } else {
      let cnt = 0;
      // T의 1이 S의 ?와 같다면 S의 ?를 1로 치환
      for (const v of T[1]) {
        if (S[1].size === T[1].size) break;
        if (S[2].has(v)) {
          S[2].delete(v);
          S[1].add(v);
          cnt++;
        }
      }
      // S의 남은 ? 를 0으로 바꿉니다.
      cnt += S[2].size;
      // S의 1이 T의 1과 다른경우를 바꿔줍니다.
      // 현재까지 0, 1의 개수가 정확히 일치하는 상황이므로 swap했다고 가정함.
      for (const v of T[1]) {
        if (!S[1].has(v)) {
          cnt++;
        }
      }
      ans += "Case " + t + ": " + cnt + "\n";
    }
  }
  console.log(ans);
};

solution();

// 0 > 1 가능.
// ? >> 0, 1 가능
