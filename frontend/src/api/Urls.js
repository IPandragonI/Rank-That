const Urls = {
    auth: {
        login: '/api/auth/login',
        register: '/api/auth/register',
    },
    tierlist: {
        create: '/api/tierlist/create',
        get: (id) => `/api/tierlist/${id}`,
        update: (id) => `/api/tierlist/${id}/update`,
    }
}

const BASE_URL = 'http://localhost:3000';

Urls.getFullUrl = function(path) {
    return BASE_URL + path;
}

export default Urls;