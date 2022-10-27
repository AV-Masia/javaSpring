
const headerCss = `

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

        header .login {
            flex-basis:25%;
            text-align: right
            padding: 0 15px;

        }
        header .logo {
            flex-basis:25%;
        }
        header .information {
            flex-basis:50%;
            text-align: center;
        }

`

export default headerCss;