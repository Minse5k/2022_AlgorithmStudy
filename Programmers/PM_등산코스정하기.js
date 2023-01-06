function solution(n, paths, gates, summits) {
    const answer = [Infinity, Infinity];
    const graph = Array.from({length: n+1}, () => []);
    for(const[s, e, cost] of paths) {
        graph[s].push([e, cost]);
        graph[e].push([s, cost]);
    }
    let q = gates;
    const summitSet = new Set();
    for(const summit of summits) {
        summitSet.add(summit);
    }
    
    const dp = new Array(n+1).fill(Infinity);
    gates.forEach(v=>dp[v] = -1);
    
    while(q.length > 0){
        let len = q.length;
        while(len-- > 0){
            const now = q.shift();
            if(summitSet.has(now)) continue;
            for(let [next, cost] of graph[now]){
                const maxCost = Math.max(dp[now], cost);
                if(dp[next] > maxCost){
                    dp[next] = maxCost;
                    q.push(next);
                }
            }
        }
    }
    for(const summit of summits) {
        if(answer[1] > dp[summit]) {
            answer[0] = summit;
            answer[1] = dp[summit];
        } else if(answer[1] === dp[summit] && answer[0] > summit) {
            answer[0] = summit;
        }
    }
   
    
    return answer;
}
