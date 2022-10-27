function solution(topping) {
  var answer = 0;
  const front = new Set();
  topping.forEach((v, idx) => {
    front.add(v);
    const back = new Set();
    for (let i = idx + 1; i < topping.length; i++) {
      back.add(topping[i]);
    }
    if (front.size === back.size) answer++;
  });
  return answer;
}

solution([1, 2, 1, 3, 1, 4, 1, 2]);
