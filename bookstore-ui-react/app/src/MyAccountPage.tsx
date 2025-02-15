import { CurrentUser } from "./service/requests";

export function MyAccountPage(): JSX.Element {
    return (
        <div>
            <h1>My account</h1>

            <h2>TODO</h2>

            <div>email: {CurrentUser?.email}</div>
            <div>name: {CurrentUser?.name}</div>
            
        </div>
    );
}