
const headerTemplate = document.createElement('template');
import headerepeyCss from '../../css/headerepey.css.js';


headerTemplate.innerHTML =`
     <style>`
        + headerepeyCss +
    `</style>
      <div id="ust">
          <div id="logo">
              <img alt="Epey Logo" src="https://resim.epey.com/tema/logo.png"></a>
          </div>
          <div id="garama" class="genel_ara">
              <form action="  " method="get">
                  <input aria-label="arama" class="arama genel_arama" type="text" value="" name="ara" autocomplete="off" placeholder="sitede ara" >
                  <input type="hidden" name="kat" value="">
                  <input type="submit" class="ara" value="">
              </form>
              <div class="aramasonuc aramasonuc1 genel_sonuc"></div>
          </div>
          <div id="uye_panel">
              <div class="panelliste">
                  <a href=" ">
                      <div class="descikon"></div>
                      <span class="sp1">Login</span>
                      <span class="sp2">Registration</span>
                  </a>
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