import { useState } from "react";
import { Link } from "react-router-dom";
import { CustomerDto, HttpRequestError, isHttpRequestError, signUp } from "./service/requests";

export function SignUpPage(): JSX.Element {

    const [email, setEmail] = useState<string>('user@example.com');
    const [name, setName] = useState<string>('user');
    const [password, setPassword] = useState<string>('');
    const [customerDtoResponse, setCustomerDtoResponse] = useState<CustomerDto | null>(null);
    const [responseDisplay, setResponseDisplay] = useState<JSX.Element>(<div></div>);

    function handleSignUp() {
        const customerDto: CustomerDto = {
            email: email,
            password: password,
            name: name
        }; 
        signUp(customerDto)
        .then((data: CustomerDto | HttpRequestError) => handleSignUpResponse(data))
        .catch((error) => console.warn(error))
    }

    function handleSignUpResponse(data: CustomerDto | HttpRequestError) {
        if(isHttpRequestError(data)) {
            const error: HttpRequestError = (data as HttpRequestError);
            setResponseDisplay(
                <div className="error-container">
                    Error when attempting to create the user.
                    <br />
                    {error.message}
                </div>
            )
        }
        else {
            const customerResponse : CustomerDto = (data as CustomerDto);
            setCustomerDtoResponse(customerResponse);
            setResponseDisplay(
                <div className="success-container">
                    The user {customerResponse?.email} was successfully created! You can login now.
                </div>
            );
        }

    }
    
    return (
        <div>
            <h1>Sign up</h1>

            <div className="sign-up-form">

                <div className="input-container">
                    <label className="input-label">
                        Enter your email
                        <br />
                        <input type="email" onChange={e => setEmail(e.target.value)} className="input-text" /> 
                    </label>
                </div>

                <div className="input-container">
                    <label className="input-label">
                        Enter your name
                        <br />
                        <input type="text" onChange={e => setName(e.target.value)} className="input-text" /> 
                    </label>
                </div>

                <div className="input-container">
                    <label>
                        Enter your password here
                        <br />
                        <input type="password" onChange={e => setPassword(e.target.value)} className="input-text" />
                    </label>
                </div>

                {responseDisplay}


                <button onClick={handleSignUp}>Register</button>

            </div>

        
            <br />
            <div className="small-text">
                Already registered?
            </div>

            <Link to="/login"><button>Login</button></Link>
        </div>
    )
}