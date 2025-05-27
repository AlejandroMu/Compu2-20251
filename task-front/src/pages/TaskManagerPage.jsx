import { Card, Grid, Typography } from '@mui/joy';
import { useEffect, useState } from 'react';
import { getTasksList } from '../services/taskService';
import ListTask from '../components/ListTask';

const TaskManagerPage = () => {
  const [listTasks, setListTask] = useState([]);

  useEffect(() => {
    getTasksList().then((data) => {
      console.log(data);

      setListTask(data);
    });
  }, []);

  return (
    <Grid
      sx={{
        width: '100%',
        display: 'flex',
        flexDirection: 'row',
        alignContent: 'space-between',
      }}
    >
      {listTasks.map((l) => {
        return <ListTask list={l}></ListTask>;
      })}
    </Grid>
  );
};

export default TaskManagerPage;
