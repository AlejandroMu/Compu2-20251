import { Grid } from "@mui/joy";
import Task from "./Task";

const ListTask = ({list}) => {

    return (
        <Grid sx={{
            backgroundColor:'white',
            margin:'30px'
        }}>
            {
                list.tasks.map((t, i) => <Task key={i} task = {t}></Task>)
            }
        </Grid>
    );

}

export default ListTask;