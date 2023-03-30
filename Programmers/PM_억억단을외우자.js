const solution = (e, starts) => {
    const counts = new Array(e + 1).fill(1);
    const results = new Array(e + 1).fill(0);
    
    for (let num = 2; num <= e; num ++) {
        for (let divider = num; divider <= e; divider += num) {
            counts[divider] += 1;
        }
    }
    console.log(counts);

    for (let i = results.length - 1; i >= 0; i --) {
        if (i === results.length - 1) {
            results[i] = i;
            continue;
        }

        const prevMaxIdx = results[i + 1];

        results[i] = counts[i] >= counts[prevMaxIdx] ? i : prevMaxIdx;
    }

    return starts.map((start) => results[start]);
};
