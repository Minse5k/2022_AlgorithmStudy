const fs = require('fs');
const filePath =
  process.platform === 'linux' ? '/dev/stdin' : '../../input.txt';
const input = fs.readFileSync(filePath).toString().split('\n');
const [r1, c1, r2, c2] = input[0].split(' ').map(v => parseInt(v));
const dist = [
  [0, 1],
  [-1, 0],
  [0, -1],
  [1, 0],
];

let size = (r2 - r1 + 1) * (c2 - c1 + 1);

const arr = Array.from({ length: r2 - r1 + 1 }, () =>
  Array.from({ length: c2 - c1 + 1 }, () => 0),
);

const isIn = (x, y) => {
  return r1 <= x && x <= r2 && c1 <= y && y <= c2;
};

function blankPad(input, length) {
  return ' '.repeat(length - input.length) + input;
}

const solution = () => {
  let x = 0;
  let y = 0;
  let num = 1;
  let dir = 0;
  let repeat = 1;
  let maxNum = 0;
  while (size > 0) {
    for (let i = 0; i < 2; i++) {
      for (let j = 0; j < repeat; j++) {
        if (isIn(x, y)) {
          arr[x - r1][y - c1] = num.toString();
          size--;
          maxNum = Math.max(maxNum, num);
        }
        x += dist[dir][0];
        y += dist[dir][1];
        num += 1;
      }
      dir = (dir + 1) % 4;
    }
    repeat++;
  }
  const maxLen = maxNum.toString().length;

  arr.forEach(r => {
    let str = '';
    r.forEach(c => {
      str += blankPad(c, maxLen);
      str += ' ';
    });
    console.log(str);
  });
};

solution();
