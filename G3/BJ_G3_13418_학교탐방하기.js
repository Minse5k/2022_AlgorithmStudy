let N, M;
const input = [];
const solution = () => {
  const union = (x, y) => {
    const find = v => {
      if (parent[v] < 0) return v;
      return (parent[v] = find(parent[v]));
    };

    const x2 = find(x);
    const y2 = find(y);
    if (x2 != y2) {
      parent[y2] = x2;
      return true;
    }
    return false;
  };

  let cost = 0;
  const parent = Array(N + 1).fill(-1);
  input.sort((a, b) => a.w - b.w);
  input.forEach(node => {
    if (union(node.v1, node.v2)) {
      cost += node.w;
    }
  });
  const maxCost = (N - cost) * (N - cost);
  input.sort((a, b) => b.w - a.w);
  cost = 0;
  parent.fill(-1);
  input.forEach(node => {
    if (union(node.v1, node.v2)) {
      cost += node.w;
    }
  });
  const minCost = (N - cost) * (N - cost);

  console.log(maxCost - minCost);
};

const rl = require('readline')
  .createInterface(process.stdin, process.stdout)
  .on('line', line => {
    if (!N) {
      [N, M] = line.split(' ').map(v => parseInt(v));
      return;
    }
    const [v1, v2, w] = line.split(' ').map(v => parseInt(v));
    input.push({ v1, v2, w });
  })
  .on('close', () => {
    solution();
    process.exit();
  });
