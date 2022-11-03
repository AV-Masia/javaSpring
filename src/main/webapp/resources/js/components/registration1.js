const registrationTemplate = document.createElement('template');

registrationTemplate.innerHTML =`
    <style>

        div.wrapper {
            height: 120px;
            display: flex;
            align-items: stretch;
            justify-content: center;
            background-color: #0a0a23;
        }
        div.information ul li{
            list-style: none;
            display: inline;
            text-align: center;

        }
        .login {
            padding: 0 15px;
        }

        ul li {
            padding: 0 0 10px;
            }

        a {
            font-weight: 700;
            margin: 0 25px;
            color: #fff;
            text-decorator: none;
        }

        a:hover {
            padding-bottom: 5px;
            box-shadow: 0 -2px 0 0 #fff;
        }
    </style>
    <header>
            <div class= "registration"  >
                <form class="form-registration" method='Post' action="/j_spring_security_check" >

                            <div class= "login" >
                                <form class="form-login" method='Post' action="/j_spring_security_check" >
                                    <ul>
                                        <li>
                                               <label for="username"></label>
                                               <input id="username" type="text" placeholder= "login" required>
                                        </li>
                                        <li>
                                           <label for="password"></label>
                                           <input id="password" type="password" placeholder= "password" required>
                                        </li>
                                    </ul>
                                       <button class="bottom" type="submit">submit</button>


                    <ul>
                        <li>
                               <label for="firstName"></label>
                               <input id="firstName" type="text" placeholder= "first name" required>
                        </li>
                        <li>
                           <label for="lastName"></label>
                               <input id="lastName" type="text" placeholder= "last name" required>
                        </li>
                        <li>
                           <label for="email"></label>
                               <input id="email" type="text" placeholder= "email" required>
                        </li>
                        <li>
                           <label for="password"></label>
                              <input id="password" type="password" placeholder= "password" required>
                        </li>

                    </ul>
                   <button class="bottom" type="submit">submit</button>
                </form>
            </div>
        </div>
    </header>
`;

class Registration extends HTMLElement{
    constructor() {
        super();
    }

    connectedCallback(){
        const shadowRoot = this.attachShadow({mode: 'closed'});

        shadowRoot.appendChild(registrationTemplate.content);
    }
}
customElements.define('registration-component', Registration);