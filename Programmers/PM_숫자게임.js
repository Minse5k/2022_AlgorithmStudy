function solution(A, B) {
  A.sort((a, b) => a - b);
  B.sort((a, b) => a - b);
  let left = 0;
  return B.reduce((pre, cur) => {
    if (cur > A[left]) {
      left++;
      pre = pre + 1;
    }
    return pre;
  }, 0);
}

solution([5, 1, 3, 7], [2, 2, 6, 8]);
