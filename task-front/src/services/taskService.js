import { axiosInstance } from './axiosService';

export const getTasksList = () => {
  return axiosInstance.get('/api/tasks-list').then((response) => {
    console.log(response.data)
    return response.data; 
  });
};
