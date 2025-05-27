import { Button, Card, Typography } from '@mui/joy';
import { useState } from 'react';
import { useNavigate } from 'react-router';
import { login } from '../services/auhtenticationService';

export const Login = () => {
  const [data, setData] = useState({});
  const nav = useNavigate();

  const loginBtn = () => {
    if (data.username && data.password) {
      login(data.username, data.password).then((token) => {
        localStorage.setItem('token', token);
        nav('/tasks')
      });
    }
  };
  return (
    <Card>
      <Typography>Username</Typography>
      <input
        value={data.username}
        onChange={(e) =>
          setData({ password: data.password, username: e.target.value })
        }
      ></input>
      <Typography>Password</Typography>
      <input
        value={data.password}
        onChange={(e) =>
          setData({ password: e.target.value, username: data.username })
        }
      ></input>
      <Button onClick={loginBtn}>Login</Button>
    </Card>
  );
};
