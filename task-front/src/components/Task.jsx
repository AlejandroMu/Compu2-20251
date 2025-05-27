import { Grid, Typography } from "@mui/joy";

const Task = ({task}) => {

    return (
        <Grid sx = {{
            height: '50px',
            width: '100%'
        }}>
            <Typography>{task.name}</Typography>
        </Grid>
    )
}

export default Task;