


var element = document.getElementById("rebotifyChatbox");
var loadingCount = 0;
addReference(element)

function addReference(element) {
    if (!window.jQuery) {
        var script = document.createElement('script');
        script.type = "text/javascript";
        script.src = "https://www.rebotify.com/js/jquery-2.1.1.js";
        document.getElementsByTagName('head')[0].appendChild(script);
        // console.log('loading')
        // console.log(loadingCount)
        this.loadingCount++;
        if (loadingCount <= 1000)
            setTimeout(function () {
                addReference(element)
            }, 100);
    }
    else {
        loadRestJsReference(element)
    }
}


function loadRestJsReference(element) {

    // //js
    // var jqueryminjs = document.createElement("script");
    // jqueryminjs.setAttribute("type", "text/javascript");
    // jqueryminjs.setAttribute("src", "https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js");
    // element.appendChild(jqueryminjs);
    // // <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

    var slick = document.createElement("link");
    slick.setAttribute("rel", "stylesheet");
    // slick.setAttribute("href", "https://www.rebotify.com/style/css/slick-carousel/1.6.0/slick.min.css");
    slick.setAttribute("href", "https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick.min.css");
    element.appendChild(slick);
    // <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick.min.css" />

    var slicktheme = document.createElement("link");
    slicktheme.setAttribute("rel", "stylesheet");
    // slicktheme.setAttribute("href", "https://www.rebotify.com/style/css/slick-carousel/1.6.0/slick-theme.min.css");
    slicktheme.setAttribute("href", "https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick-theme.min.css");
    element.appendChild(slicktheme);
    // <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick-theme.min.css" />

    var slickminjs = document.createElement("script");
    slickminjs.setAttribute("type", "text/javascript");
    slickminjs.setAttribute("src", "https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick.min.js");
    element.appendChild(slickminjs);
    // <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick.min.js"></script>

    var fontawesome = document.createElement("link");
    fontawesome.setAttribute("rel", "stylesheet");
    fontawesome.setAttribute("href", "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css");
    element.appendChild(fontawesome);
    // <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">

    // var bootstrap_enabled = (typeof $().modal == 'function');
    var bootstrap_enabled = (typeof jQuery(function ($) { return $().modal == 'function' }));
    // console.log(bootstrap_enabled)

    var bootstrap = document.createElement("link");
    bootstrap.setAttribute("rel", "stylesheet");
    //bootstrap.setAttribute("href", "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css");
    element.appendChild(bootstrap);
    // <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    if (!bootstrap_enabled) {
        //css

        var bootstrapjs = document.createElement("script");
        bootstrapjs.setAttribute("type", "text/javascript");
        //bootstrapjs.setAttribute("src", "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js");
        element.appendChild(bootstrapjs);
        // <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    }

    //chatbox
    var chatboxcss = document.createElement("link");
    chatboxcss.setAttribute("rel", "stylesheet");
    chatboxcss.setAttribute("href", "https://www.rebotify.com/js/chatbox/chatbox.css");
    element.appendChild(chatboxcss);
    // <link href="https://www.rebotify.com/js/chatbox/chatbox.css" rel="stylesheet">

    var chatboxjs = document.createElement("script");
    chatboxjs.setAttribute("type", "text/javascript");
    chatboxjs.setAttribute("src", "https://www.rebotify.com/js/chatbox/chatbox.js");
    element.appendChild(chatboxjs);

}
