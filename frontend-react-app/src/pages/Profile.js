import "../App.css";
import { useState } from "react";
import { useSelector, useDispatch } from "react-redux";
//import { addUser, deleteUser, updateUsername } from "../reducers/productSlice";

const Profile = () => {
    const dispatch = useDispatch();
    const userList = useSelector((state) => state.productReducer.value);

    const [name, setName] = useState("");
    const [username, setUsername] = useState("");
    const [newUsername, setNewUsername] = useState("");

  return (
    <div className="App">

    </div>
  );
}

export default Profile;
