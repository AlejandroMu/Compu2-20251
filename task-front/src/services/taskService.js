import { axiosInstance } from "./axiosService";

export const getTasks = () => {
    axiosInstance.get('/tasks').then(
        (response) => {
            console.log(response)
        }
    );

}