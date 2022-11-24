const fs = require('fs');
const filePath =
  process.platform === 'linux' ? '/dev/stdin' : '../../input.txt';
let [N, input] = fs.readFileSync(filePath).toString().split('\n');
N = parseInt(N);
const getSum = () => {
  let sum = 0;
  input = input.split(' ').map(v => {
    const num = parseInt(v);
    sum += num;
    return num;
  });
  return sum;
};

const solution = () => {
  let sum = getSum();
  let cnt = 0;

  while (sum !== 0) {
    let isAllMultipleOfTwo = true;
    for (let i = 0; i < N; i++) {
      if (input[i] % 2 === 0) continue;
      input[i]--;
      sum--;
      cnt++;
      isAllMultipleOfTwo = false;
    }

    if (isAllMultipleOfTwo) {
      for (let i = 0; i < N; i++) {
        sum -= input[i] / 2;
        input[i] /= 2;
      }
      cnt++;
    }
  }
  console.log(cnt);
};

solution();
