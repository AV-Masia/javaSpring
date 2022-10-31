const footerCss =`
 html {
        margin: 0;
        display: block;
    }

    head {
        display: none;
    }

    body {
        margin: 0;
        padding: 0;
        font: 13px/20px 'Titillium Web',Arial;
        font-weight: 400;
        max-width: 100%;
        overflow-x: hidden;
        display: block;
        margin: 8px;
    }
    footer {
        height: 60px;
        padding: 0 10px;
        display: flex;
        list-style: none;
        justify-content: space-between;
        align-items: center;
        background: #6bd7ce;
    }
    #cerceve {
            width: 990px;
            margin: 0 auto;
            margin-top: 5px;
    }
    .cerceve {
        float: left;
        width: 970px;
        background: #fff;
        margin: 0;
        padding: 0 10px 10px;
        -moz-border-radius: 3px;
        -webkit-border-radius: 3px;
        z-index: 9999;
    }

    ul li {
        list-style: none;
        display: inline;
        }

    a {
        margin: 0 15px;
        color:  #545151;
        text-decoration: none;
    }

    a:hover {
        padding-bottom: 5px;
        box-shadow: inset 0 -2px 0 0 #333;
    }

    .social-row {
        font-size: 20px;
    }

    .social-row li a {
        margin: 0 15px;
    }
`
export default footerCss;