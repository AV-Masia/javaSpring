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