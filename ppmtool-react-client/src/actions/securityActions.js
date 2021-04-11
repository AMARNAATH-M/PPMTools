import axios from "axios";
import { GET_ERRORS } from "./type";

export const createNewUser = (newUser, history) => async (dispatch) => {
  try {
    await axios.post("http://localhost:8080/api/users/register", newUser);
    history.push("/login");
    dispatch({
      type: GET_ERRORS,
      payload: {},
    });
  } catch (err) {
    dispatch({
      type: GET_ERRORS,
      payload: err.response.data,
    });
  }
};
