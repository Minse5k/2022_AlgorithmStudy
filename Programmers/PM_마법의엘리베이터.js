function solution(storey) {
    let ans = 0;
    const length = storey.toString().length;
    let i = 1;
    while(storey !== 0) {
        const num = storey%10;
        
        if(num > 5) {
            storey += 10 - num;
            ans += 10 - num;
        } else if(num === 5 && Math.floor(storey / 10) % 10 >=5) {
            ans += 10 - num;
            storey += 10 - num;
        } else {
            ans += num;
            storey = Math.floor(storey/10);
        }
        i++;
    }
    return ans;
}
