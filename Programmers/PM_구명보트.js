function solution(people, limit) {
    people.sort((a, b) => a - b);
    let count = 0;
    let j = 0;
    
    while(people.length > 0) {
        const weight = people.pop();
        
        if(weight + people[j] <= limit) {
            people.shift();
        }
        count++;
    }
    return count;
}
