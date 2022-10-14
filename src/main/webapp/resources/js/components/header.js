const headerTemplate = document.createElement('template');

headerTemplate.innerHTML =`
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
        <div class="wrapper">
            <div class="logo" style="flex-basis:25%">
            </div>
            <div class="information" style="flex-basis:50%; text-align: center" >
                <ul>
                    <li><a href="about.html">About</a></li>
                    <li><a href="work.html">Work</a></li>
                    <li><a href="contact.html">Contact</a></li>
                </ul>
            </div>

            <div class= "login" style="flex-basis:25%; text-align: right" >
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
                </form>
            </div>
        </div>
    </header>
`;

class Header extends HTMLElement{
    constructor() {
        super();
    }

    connectedCallback(){
        const shadowRoot = this.attachShadow({mode: 'closed'});

        shadowRoot.appendChild(headerTemplate.content);
    }
}
customElements.define('header-component', Header);