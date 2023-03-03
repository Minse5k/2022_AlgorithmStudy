//const isOdd = number => number % 2 !== 0;

function solution(str)
{
    if(str.length % 2 !== 0) return 0;
    const stringStack = [];
    
    [...str].forEach((value) => {
        
        const stackTop = stringStack.length - 1;
        
        stringStack[stackTop] === value ? stringStack.pop() : stringStack.push(value);
    })
    
    return Number(stringStack.length === 0);
    /*
    if (isOdd(str.length)) return 0;
    
    const result = [...str].reduce((stack, cur) => {
        const top = stack.length - 1;
    
        stack[top] === cur ? stack.pop() : stack.push(cur);
        return stack;
    }, [])
    
    return Number(result.length === 0);
    */
}
    
    //내가 푼 풀이
    /*
    const stringStack = [];
    
    [...s].forEach((value) => {
        
        const stackTop = stringStack.length - 1;
        
        stringStack[stackTop] === value ? stringStack.pop() : stringStack.push(value);
    })
    
    return Number(stringStack.length === 0);
    */
