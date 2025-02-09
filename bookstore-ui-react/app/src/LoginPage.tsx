import { useState } from "react";
import { CustomerDto, ResponseLoginDto } from "./service/requests";
import { Link } from "react-router-dom";

export function LoginPage(): JSX.Element {

    const [credentials, setCredentials] = useState<CustomerDto>({
        email: '',
        password: '',
      });
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);


    function handleLogin(): void {
        
        //return null;
    }


    return (
        <div>
            <h1>Login</h1>

            <div className="sign-up-form">

                <div className="input-container">
                    <label className="input-label">
                        Enter your email
                        <br />
                        <input type="email" className="input-text" onChange={(e) => console.log(e)} /> 
                    </label>
                </div>

                <br />
                <label>
                    Enter your password here
                    <br />
                    <input type="password" />
                </label>
                <br />
                <button>Log in</button>

            </div>

            
            <div className="small-text">
                New to the bookstore?
            </div>
            <Link to="/sign-up"><button>Sign up</button></Link>
        </div>
    );
}