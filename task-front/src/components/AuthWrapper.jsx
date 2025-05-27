import { Typography } from '@mui/joy';
import { useEffect, useState } from 'react';
import { Outlet, useLocation, useNavigate } from 'react-router';

const AuthWrapper = ({
  authorities,
  redirect = import.meta.env.VITE_BASE_LOGIN_URL,
}) => {
  const [isAuthorizated, setIsAutorizated] = useState();

  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const nav = useNavigate();

  useEffect(() => {
    if (queryParams.has('token')) {
      localStorage.setItem('token', queryParams.get('token'));
      window.history.replaceState({}, '', window.location.pathname);
    }
    const token = localStorage.getItem('token');
    if (token) {
      try {
        const payload = token.split('.')[1];
        const decoded = JSON.parse(atob(payload));
        setIsAutorizated(
          !authorities ||
            authorities.length === 0 ||
            authorities.some((role) => decoded.authorities?.includes(role))
        );
      } catch {
        nav(redirect);
      }
    } else {
      setIsAutorizated(false);
    }
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  if (!isAuthorizated) {
    return <Typography>No esta autorizado</Typography>;
  }

  return <Outlet></Outlet>;
};

export default AuthWrapper;
