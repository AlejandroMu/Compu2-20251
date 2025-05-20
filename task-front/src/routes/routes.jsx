import { createBrowserRouter, Outlet } from 'react-router';
import TaskManagerPage from '../pages/TaskManagerPage';

const router = createBrowserRouter([
  {
    path: '/',
    Component: () => (
      <>
        <div>Hello World</div>
        <Outlet></Outlet>
      </>
    ),
    children: [
      { path: 'children', Component: () => <div> children coplonent</div> },
    ],
  },
  {
    path:'/tasks',
    Component: TaskManagerPage
  }
]);

export default router;
