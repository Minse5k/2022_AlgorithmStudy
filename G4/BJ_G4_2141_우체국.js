const fs = require('fs');
const { readlink } = require('fs/promises');
const filePath =
  process.platform === 'linux' ? '/dev/stdin' : '../../input.txt';
const input = fs.readFileSync(filePath).toString().split('\n');

const N = parseInt(input[0]);
const arr = Array.from({ length: N }, () => [0, 0]);
const sumArr = Array.from({ length: N }, () => 0);
let maxIdx = 0;
for (let i = 0; i < input.length - 1; i++) {
  const [idx, v] = input[i + 1].split(' ').map(v => parseInt(v));
  arr[i] = [idx, v];
  maxIdx = Math.max(maxIdx);
}
arr.sort((a, b) => a[0] - b[0]);
for (let i = 0; i < N; i++) {
  if (i === 0) {
    sumArr[i] = arr[i][1];
  } else {
    sumArr[i] = arr[i][1] + sumArr[i - 1];
  }
}
let left = 0;
let right = N - 1;
while (left <= right) {
  let mid = Math.floor((left + right) / 2);
  let leftSum = sumArr[mid];
  let rightSum = sumArr[N - 1] - sumArr[mid];
  if (leftSum >= rightSum) {
    right = mid - 1;
  } else {
    left = mid + 1;
  }
}
console.log(arr[left][0]);
