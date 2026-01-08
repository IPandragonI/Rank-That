import {Outlet} from "react-router-dom";
import {useAuth} from "../../AuthProvider.jsx";
import {useEffect} from "react";

const ProfileLayout = () => {

    const {isAuthenticated} = useAuth()

    useEffect(() => {
        if (!isAuthenticated) {
            window.location = '/auth/login'
        }
    }, []);

    return (
        <Outlet />
    )
}

export default ProfileLayout