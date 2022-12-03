function solution(stones, k) {
    let left = 1;
    let right = 200000000;
    
    while(left <= right) {
        const mid = Math.floor((left + right) /2)
        let cnt = 0;
        for(const num of stones) {
            if(num-mid <= 0) cnt++;
            else cnt = 0;
            if(cnt >= k) break;
        }
        if(cnt >= k) right = mid-1;
        else {
            left = mid + 1;
        }
    }
    
    return left;
}
