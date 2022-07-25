let N;
const input = [];
const isFront = [];

const solution = () => {
  const map = new Map();
  input.sort((a, b) => b.value - a.value);
  if (input.length === 10) {
    // 0 배치하기
    for (let i = 9; i >= 0; i--) {
      const key = input[i].key;
      if (!isFront[key]) {
        map.set(key, 0);
        break;
      }
    }
  }
  let num = 9;
  input.forEach(({ key }) => {
    if (!map.has(key)) {
      map.set(key, num--);
    }
  });
  let sum = 0;
  input.forEach(({ key, value }) => {
    sum += value * map.get(key);
  });
  console.log(sum);
};

require('readline')
  .createInterface(process.stdin, process.stdout)
  .on('line', line => {
    if (!N) {
      N = parseInt(line);
      return;
    }
    let len = line.length;
    line.split('').forEach((c, idx) => {
      const obj = input.find(({ key }) => key === c);
      const value = Math.pow(10, len - idx - 1);
      if (!obj) {
        input.push({ key: c, value });
      } else {
        obj.value += value;
      }

      if (idx == 0) {
        isFront[c] = true;
      }
    });
  })
  .on('close', () => {
    solution();
    process.exit();
  });
