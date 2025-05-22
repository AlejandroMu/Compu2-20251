import { createBrowserRouter, Outlet } from 'react-router';
import TaskManagerPage from '../pages/TaskManagerPage';
import { Login } from '../components/Login';

const router = createBrowserRouter([
  {
    path: '/',
    Component: () => (
      <>
        <Outlet></Outlet>
        <div>Hello World</div>
      </>
    ),
    children: [
      { path: 'children', Component: () => <div> children component</div> },
      { path: 'children2', Component: () => <div> children 2 component</div> },
      
    ],
  },
  {
    path: '/tasks',
    Component: TaskManagerPage,
  },
  {
    path: 'login',
    Component: Login
  }
]);

export default router;
