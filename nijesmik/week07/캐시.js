const CACHE_HIT = 1;
const CACHE_MISS = 5;

function solution(cacheSize, cities) {
    if (cacheSize === 0) {
        return cities.length * CACHE_MISS;
    }

    const cache = Array(cacheSize);
    let time = 0;
    for (let i = 0; i < cities.length; i++) {
        time += search(cache, cities[i].toLowerCase());
    }
    return time;
}

const search = (cache, city) => {
    for (let i = 0; i < cache.length; i++) {
        if (cache[i] === city) {
            cache.splice(i, 1);
            cache.push(city);
            return CACHE_HIT;
        }
    }
    cache.shift();
    cache.push(city);
    return CACHE_MISS;
}
