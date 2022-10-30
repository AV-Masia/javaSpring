const footerCss =`
        footer {
            height: 60px;
            padding: 0 10px;
            display: flex;
            list-style: none;
            justify-content: space-between;
            align-items: center;
            background: #6bd7ce;
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