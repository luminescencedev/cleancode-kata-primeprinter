function buildUrl(url, options) {
    builtUrl = initializeUrl(url);
    builtUrl = addPath(options);
    builtUrl = addQueryParams(options);
    builtUrl = addHash(options);
    return builtUrl;
}

function initializeUrl(url) {
    if (url === null) {
        builtUrl = '';
    } 
    else if (typeof(url) === 'object') {
        builtUrl = '';
        options = url;
    } 
    else {
        builtUrl = url;
    }
    return builtUrl;
}

function addPath(options) {
    if (options.path) {
        builtUrl += '/' + options.path;
    }
    return builtUrl;
}

function addQueryParams(options) {
    var queryString = [];
    var key;

    if (options.queryParams) {
        for (key in options.queryParams) {
            if (options.queryParams.hasOwnProperty(key)) {
                queryString.push(key + '=' + options.queryParams[key]);
            }
        }
        builtUrl += '?' + queryString.join('&');
    }
    return builtUrl;
}

function addHash(options) {
    if (options.hash) {
        builtUrl += '#' + options.hash;
    }
    return builtUrl;
}