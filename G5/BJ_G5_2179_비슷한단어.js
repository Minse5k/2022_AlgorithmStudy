let N = 0;

const solution = (input) => {
  input.sort((a, b) => {
    return a.value > b.value ? 1 : -1;
  });

  let max = -Infinity;
  let ans = [];
  for (let i = 0; i < N - 1; i++) {
    for (let j = i + 1; j < N; j++) {
      const { key: idxI, value: StringI } = input[i];
      const { key: idxJ, value: StringJ } = input[j];

      if (StringI.charAt(0) !== StringJ.charAt(0)) break;
      const len = getSameWordLength(StringI, StringJ);
      if (max < len) {
        max = len;
        const [minKey, maxKey] = [Math.min(idxI, idxJ), Math.max(idxI, idxJ)];

        ans = [minKey, maxKey];
      } else if (max === len) {
        const [minKey, maxKey] = [Math.min(idxI, idxJ), Math.max(idxI, idxJ)];

        if (ans[0] > minKey || (ans[0] === minKey && ans[1] > maxKey)) {
          ans[0] = minKey;
          ans[1] = maxKey;
        }
      }
    }
  }
  console.log(input.find((object) => object.key === ans[0]).value);
  console.log(input.find((object) => object.key === ans[1]).value);
};

const getSameWordLength = (str1, str2) => {
  const len = Math.min(str1.length, str2.length);
  let cnt = 0;

  for (let i = 0; i < len; i++, cnt++) {
    if (str1.charAt(i) !== str2.charAt(i)) {
      return cnt;
    }
  }
  return cnt;
};
input = [];
let index = 0;
require("readline")
  .createInterface(process.stdin, process.stdout)
  .on("line", (line) => {
    if (N === 0) {
      N = parseInt(line);
      return;
    }
    input.push({
      key: index++,
      value: line,
    });
  })
  .on("close", () => {
    solution(input);
    process.exit();
  });
