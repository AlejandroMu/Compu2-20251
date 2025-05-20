import { Card, Typography } from "@mui/joy"
import { useEffect } from "react";
import { getTasks } from "../services/taskService";

const TaskManagerPage = () => {

    useEffect(()=>{
        getTasks()
    },[]);

    return (
        <Card>
            <Typography>Tasks</Typography>
        </Card>
    )
}

export default TaskManagerPage;