'use strict';
const readline = require('readline');
const rl = readline.createInterface({
    input : process.stdin,
    output : process.stdout
});
let count = -1;
const input = [];
rl.on('line', function(line) {
    if(count === -1) {
        count = parseInt(line);
        return;
    }
    input.push(line.split(' ').map((v) => parseInt(v)));
    count--;
    if(count === 0) rl.close();
}).on('close', function() {
    const n = input.length;
    const dp = new Array(n).fill(0);
    dp[1] = input[0][0];
    for(let i = 1; i < n; i++) {
        if(input[i][1] === 0) {
            dp[i + 1] = input[i][0];
            continue;
        }
        let max = 0;
        for(let j = 0; j < input[i][1]; j++) {
            max = Math.max(max, dp[input[i][2 + j]]);
        }
        dp[i + 1] = max + input[i][0];
    }
    console.log(Math.max(...dp));
    process.exit();
})
