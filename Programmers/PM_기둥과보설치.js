const PILLAR = 0;
const PANEL = 1;
const BUILD = 1;

function solution(n, build_frame) {
  const answer = [];
  build_frame.forEach(([x, y, a, b]) => {
    if (b === BUILD) {
      buildFrame(x, y, a, answer);
    } else {
      destroyFrame(x, y, a, answer);
    }
  });
  answer.sort((ans1, ans2) => {
    if (ans1[0] === ans2[0]) {
      if (ans1[1] === ans2[1]) {
        return ans1[2] - ans2[2];
      }
      return ans1[1] - ans2[1];
    }
    return ans1[0] - ans2[0];
  });

  return answer;
}
const destroyFrame = (x, y, a, answer) => {
  const idx = answer.findIndex(([x1, y1, a1]) => x1 === x && y1 === y && a1 === a);
  const copy = [...answer];
  copy.splice(idx, 1);
  for ([x1, y1, a1] of copy) {
    if (a1 === PANEL) {
      if (!isBuildPanel(x1, y1, copy)) return;
    } else {
      if (!isBuildPillar(x1, y1, copy)) return;
    }
  }
  answer.splice(idx, 1);
};

const isBuildPillar = (x, y, answer) => {
  if (y === 0) return true;
  else {
    for ([x1, y1, a1] of answer) {
      if (a1 === PILLAR && x1 === x && y1 === y - 1) return true;
      if (a1 === PANEL && y1 === y && (x1 === x - 1 || x1 === x)) return true;
    }
  }
  return false;
};
const isBuildPanel = (x, y, answer) => {
  for ([x1, y1, a1] of answer) {
    if (a1 === PILLAR && y1 === y - 1 && (x1 === x || x1 === x + 1)) return true;
  }
  if (
    answer.find(([x1, y1, a1]) => x1 === x + 1 && y1 === y && a1 === PANEL) &&
    answer.find(([x1, y1, a1]) => x1 === x - 1 && y1 === y && a1 === PANEL)
  )
    return true;
  return false;
};

const buildFrame = (x, y, a, answer) => {
  if (a === PILLAR) {
    if (isBuildPillar(x, y, answer)) {
      answer.push([x, y, a]);
    }
  } else {
    if (isBuildPanel(x, y, answer)) {
      answer.push([x, y, a]);
    }
  }
};

solution(5, [
  [0, 0, 0, 1],
  [2, 0, 0, 1],
  [4, 0, 0, 1],
  [0, 1, 1, 1],
  [1, 1, 1, 1],
  [2, 1, 1, 1],
  [3, 1, 1, 1],
  [2, 0, 0, 0],
  [1, 1, 1, 0],
  [2, 2, 0, 1],
]);
