
const headerepeyCss = `

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


    #menu {
        float: left;
        position: relative;
        width: 970px;
        height: 44px;
        background: #d96142;
        border-top: 1px solid #d96142;
        display: block;
        border-bottom: 1px solid #fff;
        -moz-border-radius: 3px;
        -webkit-border-radius: 3px;
        border-radius: 0 0 3px 3px;
        font-size: 14px;
        z-index: 500;
    }

    #menu ul {
        width: 100%;
        text-align: left;
        display: inline;
        list-style: none;
        padding: 0;
        margin: 1px;
        z-index: 900;
    }
    #menu ul li.seviye1 {
        font-weight: 700;
    }

    #menu ul li {
        width: auto;
        display: inline-block;
        padding: 12px 10px;
        color: #fff;
        height: 20px;
    }

    #menu ul li a.seviye1 {
        color: #fff;
    }

    #menu ul .ayrac {
        font-weight: 400;
        width: 1px;
        height: 20px;
        color: #fff;
        font-size: 22px;
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
     #ust {
         float: left;
         width: 970px;
         height: 40px;
         margin: 0 5px;
         padding: 10px 0 5px;
         background: #fff;
     }

     div {
         display: block;
     }

     #logo {
         float: left;
         margin: 3px 0 0 15px;
     }

     a {
         text-decoration: none;
         color: #000;
     }
     #garama {
         float: left;
         width: 650px;
         height: 40px;
         border: 1px solid #d96142;
         -moz-border-radius: 3px;
         -webkit-border-radius: 3px;
         margin-left: 25px;
         background: #fff;
         margin-right: 5px;
         background: #e9e8e8;
     }

     .genel_ara {
         z-index: 1000;
     }

     script {
         display: none;
     }

     form {
         display: block;
         margin-top: 0em;
     }

    #garama .arama {
        width: 559px;
        font-size: 15px;
        margin-top: 4px;
        font: bold 13px/20px 'Titillium Web',Arial;
    }
    
     #garama .ara {
         width: 70px;
         height: 38px;
         background: url(https://resim.epey.com/tema/aratmp.png) no-repeat center #d96142;
         cursor: pointer;
         margin: 1px 1px 0 0;
         padding: 5px 0;
         border-radius: 0 3px 3px 0;
     }

    #garama input {
        float: left;
        padding: 7px 10px;
        border: none;
        background: 0 0;
        outline: 0!important;
        -webkit-appearance: none;
        -moz-border-radius: 3px;
        -webkit-border-radius: 3px;
    }




     #garama .aramasonuc {
         float: left;
         width: 580px;
         position: relative;
         z-index: 100000;
     }

     #uye_panel {
         position: relative;
         width: 102px;
         height: 36px;
         /* border: 1px solid #42d98c; */
         cursor: pointer;
         padding: 4px 0 0 35px;
         border-radius: 3px;
         float: left;
         background: url(https://resim.epey.com/tema/user.png) 5px 10px no-repeat;
     }

     .panelliste {
         font-size: 15px;
     }

     .panelliste {
         font-size: 15px;
     }

     #uye_panel {
         position: relative;
         width: 102px;
         height: 36px;
         /* border: 1px solid #42d98c; */
         cursor: pointer;
         padding: 4px 0 0 35px;
         border-radius: 3px;
         float: left;
         background: url(https://resim.epey.com/tema/user.png) 5px 10px no-repeat;
     }

     .panelliste .descikon {
         float: right;
         width: 25px;
         height: 17px;
         background: url(https://resim.epey.com/tema/down.png?v=3) no-repeat;
         margin: 10px 0 0;
     }

     .panelliste span.sp1 {
         font-size: 16px;
         font-weight: 700;
     }
     .panelliste span {
         display: block;
         font-size: 12px;
         line-height: 17px;
     }

     .panelliste span {
         display: block;
         font-size: 12px;
         line-height: 17px;
     }

`

export default headerepeyCss;

