function solution(d, budget) {
    d.sort((a, b) => a - b);
    
    let result = 0;
    let sum = 0;
    
    for(let i = 0; i < d.length; i++) {
        if(sum + d[i] > budget) return result;
        sum += d[i];
        result++;
    }
    return result;
}
