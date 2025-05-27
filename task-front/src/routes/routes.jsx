import { Typography } from '@mui/joy';
import { createBrowserRouter } from 'react-router';
import AuthWrapper from '../components/AuthWrapper';
import TaskManagerPage from '../pages/TaskManagerPage';

const router = createBrowserRouter([
  
  {
    path: '/tasks-board',
    element: <AuthWrapper/>,
    children:[
      {
        index: true,
        Component: TaskManagerPage
      }
    ] 
  },
  {
    path: '/**',
    Component: () => <Typography>404</Typography>
  }
]);

export default router;
