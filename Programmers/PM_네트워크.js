function solution(n, computers) {
  let answer = 0;
  const length = computers.length;
  const visited = Array.from({ length: n }, () => false);

  const dfs = idx => {
    visited[idx] = true;

    for (let i = 0; i < length; i++) {
      if (computers[idx][i] !== 1 || visited[i]) continue;
      dfs(i);
    }
  };

  for (let i = 0; i < length; i++) {
    if (visited[i]) continue;
    dfs(i);
    answer++;
  }

  return answer;
}
solution(3, [
  [1, 1, 0],
  [1, 1, 1],
  [0, 1, 1],
]);
