const checkBlock = (boardArray, visited, x, y) => {
  const squareBlocks = [
    boardArray[x + 1][y + 1],
    boardArray[x][y + 1],
    boardArray[x + 1][y],
  ];

  if (squareBlocks.every(c => c === boardArray[x][y])) {
    visited[x][y] = true;
    visited[x + 1][y] = true;
    visited[x][y + 1] = true;
    visited[x + 1][y + 1] = true;
    return true;
  }
  return false;
};

const removeBlock = (m, n, boardArray, visited, count) => {
  visited.forEach((vArr, i) =>
    vArr.forEach((visit, j) => {
      if (visit) {
        boardArray[i][j] = 0;
        count++;
      }
    }),
  );
  return count;
};

const shiftBlock = (m, n, boardArray) => {
  for (let i = m - 1; i >= 0; i--) {
    for (let j = 0; j < n; j++) {
      if (boardArray[i][j] === 0) {
        for (let k = i - 1; k >= 0; k--) {
          if (boardArray[k][j] !== 0) {
            boardArray[i][j] = boardArray[k][j];
            boardArray[k][j] = 0;
            break;
          }
        }
      }
    }
  }
};

function solution(m, n, board) {
  const boardArray = [];
  board.forEach(value => {
    boardArray.push(value.split(''));
  });
  let count = 0;

  while (true) {
    const visited = Array.from({ length: m }, () =>
      Array.from({ length: n }, () => false),
    );
    for (let i = 0; i < m - 1; i++) {
      for (let j = 0; j < n - 1; j++) {
        if (boardArray[i][j] === 0) continue;
        checkBlock(boardArray, visited, i, j);
      }
    }

    if (visited.every(arr => !arr.includes(true))) break;
    count += removeBlock(m, n, boardArray, visited, 0);
    shiftBlock(m, n, boardArray);
  }
  return count;
}
