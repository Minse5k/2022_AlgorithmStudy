function solution(arr)
{
    const array = [];
    arr.forEach((value, idx) => {
        if(array.length === 0) array.push(value);
        else {
            if(array[array.length - 1] !== value) array.push(value);
        }
    })
    
    return array;
}
