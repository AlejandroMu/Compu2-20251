import { Button, Grid } from '@mui/joy';
import { useEffect, useState } from 'react';
import ListTask from '../components/ListTask';
import { createTask, getTasksList } from '../services/taskService';
import useWebSocket from '../services/webSocketService';

const TaskManagerPage = () => {
  const [listTasks, setListTask] = useState([]);
  let count = 0;
  const ws = useWebSocket((data) => {
    console.log(data);
    updateTask();

  });

  const updateTask = () => {
      getTasksList().then((data) => {
        console.log(data);
    
        setListTask(data);
      });

  }

  useEffect(() => {
    updateTask();

    return () => {
        ws.close();
    }
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
      {listTasks.map((l, i) => {
        return <ListTask key={i} list={l}></ListTask>;
      })}
      <Button
        onClick={() => {
          const newTask = {
            name: 'Creada desde el front' + count++ ,
            description: 'Desc',
            notes: 'Ninguna',
            priority: 1,
            listId: 1,
          };
          createTask(newTask);
        }}
      >
        Crear Tarea
      </Button>
    </Grid>
  );
};

export default TaskManagerPage;
