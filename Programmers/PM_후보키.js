function combination(arr, selectNum) {
  const result = [];

  if (selectNum === 1) return arr.map((v) => [v]);
  arr.forEach((v, idx, arr) => {
    const fixed = v;
    const restArr = arr.slice(idx + 1);
    const combinationArr = combination(restArr, selectNum - 1);
    const combinationFix = combinationArr.map((v) => [fixed, ...v]);
    result.push(...combinationFix);
  });

  return result;
}

function isUniqueness(keyList, relation, rowNum) {
  const set = new Set();

  relation.forEach((r) => {
    let string = "";
    keyList.forEach((k) => {
      string += r[k];
    });
    set.add(string);
  });

  return rowNum === set.size;
}

function isMinimality(comb, minimality) {
  let isMinimality = true;
  minimality.forEach((i) => {
    let cnt = 0;
    i.forEach((j) => {
      if (comb.indexOf(j) !== -1) cnt++;
    });
    if (cnt === i.length) isMinimality = false;
  });

  return isMinimality;
}

function solution(relation) {
  const columnNum = relation[0].length;
  const arr = new Array(columnNum).fill().map((v, i) => (v = i));
  let comb = [];
  const minimality = [];
  for (let i = 1; i <= columnNum; i++) {
    comb = [...comb, ...combination(arr, i)];
  }

  let ans = 0;
  comb.forEach((c) => {
    if (isMinimality(c, minimality) && isUniqueness(c, relation, relation.length)) {
      ans++;
      minimality.push(c);
    }
  });
  return ans;
}
