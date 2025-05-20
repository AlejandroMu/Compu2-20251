import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: import.meta.env.VITE_BASE_URL,

  headers: {
    'X-Requested-With': 'XMLHttpRequest',
    Accept: 'application/json',
  },
  validateStatus: function validateStatus(status) {
    if (status >= 200 && status < 300) {
      return true;
    } else {
      return false;
    }
  },
});


function addTokenInterceptor(instance) {
  instance.interceptors.request.use((config) => {
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  });
}

addTokenInterceptor(axiosInstance);

export { axiosInstance };
