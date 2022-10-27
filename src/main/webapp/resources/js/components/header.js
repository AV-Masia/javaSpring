
const headerTemplate = document.createElement('template');
import headerCss from '../../css/header.css.js';


headerTemplate.innerHTML =`
     <style>`
        + headerCss +
    `</style>
    <header>
        <div id="ust" class="wrapper">
            <div class="logo">
            </div>
            <div class="information">
                <ul>
                    <li><a href="about.html">About</a></li>
                    <li><a href="work.html">Work</a></li>
                    <li><a href="contact.html">Contact</a></li>
                </ul>
            </div>
        </div>
    </header>
`
;

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