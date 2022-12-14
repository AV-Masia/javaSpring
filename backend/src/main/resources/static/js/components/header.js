
const headerTemplate = document.createElement('template');
import headerCss from '../../css/header.css.js';


headerTemplate.innerHTML =`
     <style>`
        + headerCss +
    `</style>
        <div id="frame" xmlns:th=http://www.thymeleaf.org>
            <div class="frame">
                <div id="ust">
                    <div id="logo">
                        <a href = "http://localhost:8082/index">
                        <img alt="Epey Logo" src="https://resim.epey.com/tema/logo.png"></a>
                    </div>
                    <div id="search-box" class="search-key-box">
                        <form action="  " method="get">
                            <input aria-label="call" class="call general_call" type="text" value="" name="search" autocomplete="off" placeholder="sitede search" >
                            <input type="hidden" name="floor" value="">
                            <input type="submit" class="search" value="">
                        </form>
                        <div class="callconclusion general_conclusion"></div>
                    </div>
                    <div id="user_panel">
                        <div class="panelliste">
                            <a href="http://localhost:8082/login.html">
                                <div class="descikon"></div>
                                <span class="sp1">Login</span>
                            </a>
                            <a href="/logout ">
                                <span class="sp1">Logout</span>
                            </a>
                        </div>
                    </div>
                </div>
                <div id="menu">
                    <ul>
                        <li class="level">
                            <span class="s1">
                                <a href="https://www.epey.com/akilli-telefonlar/" title="PAGE1" class="level">TELEFON</a>
                            </span>
                        </li>
                        <span class="brace">|</span>
                        <li class="level">
                            <span class="s1">
                                <a href="https://www.epey.com/akilli-telefonlar/" title="PAGE2" class="level">TELEFON</a>
                            </span>
                        </li>
                        <span class="brace">|</span>
                        <li  class="level">
                            <span class="s1">
                                <a href="https://www.epey.com/akilli-telefonlar/" title="PAGE3" class="level" th:text='hello'></a>
                            </span>
                        </li>
                        <span class="brace">|</span>
                    </ul>
                </div>
            </div>
        </div>
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