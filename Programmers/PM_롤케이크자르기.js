function solution(topping) {
    var answer = 0;
    const len = topping.length;
    const front = new Set();
    const back = new Set();
    const frontCnt = Array.from({length: len});
    const backCnt = Array.from({length: len});
    topping.forEach((v, idx) => {
        front.add(v);
        back.add(topping[len-idx-1]);
        backCnt[len-1-idx] = back.size;
        frontCnt[idx] = front.size;
    });
    console.log(frontCnt, backCnt)
    for(let i=0; i<len-1; i++) {
        if(frontCnt[i] === backCnt[i+1]) answer++;
    }
  return answer;
}
