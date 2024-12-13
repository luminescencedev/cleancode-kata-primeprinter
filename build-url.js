function buildUrl(url, options) {
    let builtUrl = initializeUrl(url);

    if (options) {
        builtUrl = appendPath(builtUrl, options.path);
        builtUrl = appendQueryParams(builtUrl, options.queryParams);
        builtUrl = appendHash(builtUrl, options.hash);
    }

    return builtUrl;
}

function initializeUrl(url) {
    if (url === null || typeof(url) === 'object') {
      return '';
    }
    return url;
}

function appendPath(url, path) {
    if (path) {
      return url + '/' + path;
    }
    return url;
}

function appendQueryParams(url, queryParams) {
    if (queryParams) {
        const queryString = Object.keys(queryParams)
            .map(key => `${key}=${queryParams[key]}`)
            .join('&');
        return url + '?' + queryString;
    }
    return url;
}

function appendHash(url, hash) {
    if (hash) {
        return url + '#' + hash;
    }
    return url;
}