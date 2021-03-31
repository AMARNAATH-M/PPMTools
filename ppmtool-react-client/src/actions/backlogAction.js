import axios from "axios";

export const addProjectTask = (backlog_id, project_task, history) => async (
  dispatch
) => {
  await axios.post(
    `http://localhost:8080/api/backlog/${backlog_id}`,
    project_task
  );
  history.push(`projectBoard/${backlog_id}`);
};
