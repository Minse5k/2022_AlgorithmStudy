function solution(n, k) {
    var answer = 0;
    const num = n.toString(k).split('0')
    const isDecimal = (num) => {
        if(num === 1 || !num) return false;
        else {
            for(let i=2; i*i<=num; i++) {
                if(num%i === 0) return false;
            }
        }
        return true;
    }
    num.forEach(v => {
        answer += isDecimal(parseInt(v)) ? 1 : 0})
    
    return answer;
}
