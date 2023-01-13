
function solution(gems) {
    const gemCnt = [...new Set(gems)].length;
    let min = 100002;
    let answer = [1, gems.length];
    let gemMap = new Map();
    
    for(let i = 0; i < gems.length; i++) {
        gemMap.delete(gems[i]);
        gemMap.set(gems[i], i);
        if(gemMap.size === gemCnt) {
            const cand = [gemMap.values().next().value + 1, i + 1];
            answer = answer[1] - answer[0] > cand[1] - cand[0] ? cand : answer;
        }
    }
    return answer;
}
