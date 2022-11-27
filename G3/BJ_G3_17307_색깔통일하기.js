const fs = require('fs');
const filePath =
  process.platform === 'linux' ? '/dev/stdin' : '../../input.txt';
const input = fs.readFileSync(filePath).toString().split('\n');
const [N, C] = input[0].split(' ').map(v => parseInt(v));
const array = input[1].split(' ').map(v => parseInt(v));
const RIGHT = 1;
const LEFT = 0;

const getDiff = (basic, change) => {
  if (basic > change) {
    return C - basic + change;
  } else {
    return change - basic;
  }
};

const getArray = array => {
  const arr = Array.from({ length: N }, () =>
    Array.from({ length: 2 }, () => 0),
  );
  arr[0][RIGHT] = getDiff(array[0], array[1]);
  for (let i = 1; i < N - 1; i++) {
    arr[i][LEFT] = getDiff(array[i], array[i - 1]);
    arr[i][RIGHT] = getDiff(array[i], array[i + 1]);
  }
  arr[N - 1][LEFT] = getDiff(array[N - 1], array[N - 2]);
  return arr;
};

const solution = () => {
  if (N === 1) {
    console.log(1);
    console.log(0);
    return;
  }
  let idx = 0;
  const arr = getArray(array);
  let rightSum = 0;
  let leftSum = 0;
  arr.forEach(v => {
    rightSum += v[RIGHT];
  });
  let min = rightSum;
  for (let i = 1; i < N; i++) {
    rightSum -= arr[i - 1][RIGHT];
    leftSum += arr[i][LEFT];
    const sum = Math.max(rightSum, leftSum);
    if (min > sum) {
      idx = i;
      min = sum;
    }
  }

  console.log(idx + 1);
  console.log(min);
};

solution();
