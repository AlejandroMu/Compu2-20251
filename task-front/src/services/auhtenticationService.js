import { axiosInstance } from './axiosService';

export const login = (user, password) => {
  const data = {
    username: user,
    password: password,
  };
  return axiosInstance.post('/login', data).then(
    (response) => {
        return response.data;
    }
  );
};
