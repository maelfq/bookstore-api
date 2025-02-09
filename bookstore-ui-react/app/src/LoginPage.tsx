import { useState } from "react";
import { CurrentUser, CustomerDto, HttpRequestError, isHttpRequestError, login, ResponseLoginDto } from "./service/requests";
import { Link } from "react-router-dom";

export function LoginPage(): JSX.Element {

    const [email, setEmail] = useState<string>('test');
    const [password, setPassword] = useState<string>('');
    const [responseDisplay, setResponseDisplay] = useState<JSX.Element>(<div></div>);



    function handleLogin(): void {
        const loginDto: CustomerDto = {
            email: email,
            password: password
        }
        login(loginDto)
        .then(
            (response) => {
                if(isHttpRequestError(response)) {
                    const errorResponse: HttpRequestError = (response as HttpRequestError);
                    setResponseDisplay(
                        <div className="error-container">
                            Error when attempting to log in.
                            <br />
                            {errorResponse.message}
                        </div>
                    )
                }
                else {
                    const loginResponse : ResponseLoginDto = (response as ResponseLoginDto);
                    CurrentUser.email = loginResponse.email;
                    CurrentUser.name = loginResponse?.name;
                    setResponseDisplay(
                        <div className="success-container">
                            Logged in successfully!
                        </div>
                    );
                }
            }
        )

    }


    return (
        <div>
            <h1>Login</h1>

            <div className="sign-up-form">

                <div className="input-container">
                    <label className="input-label">
                        Enter your email
                        <br />
                        <input type="email" className="input-text" placeholder="test@example.com" onChange={(e) => setEmail(e.target.value)} /> 
                    </label>
                </div>
                <div className="input-container">
                    <label className="input-label">
                        Enter your password here
                        <br />
                        <input type="password" className="input-text" onChange={e => setPassword(e.target.value)}/>
                    </label>
                </div>
                <button onClick={handleLogin}>Log in</button>

            </div>
            <br />

            {responseDisplay}

            <br />
            <div className="small-text">
                New to the bookstore?
            </div>
            <Link to="/sign-up"><button>Sign up</button></Link>
        </div>
    );
}
