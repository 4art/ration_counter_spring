<!DOCTYPE html>
<html lang="de">
<head>
    <title>Metra</title>
    <#--${meta}-->
    <#include "includes/meta.ftl" />
</head>
<body>

<div class="container">
    <#include "includes/nav-bar.ftl"/>
    <!-- Carousel

  ================================================== -->
    <#--<h1>${hello.test}</h1>-->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img class="first-slide" src="images/diet.jpg" alt="First slide">
                <div class="container">
                    <div class="carousel-caption">
                        <h2>Rationszähler.</h2>
                        <!--<p>Note: If you're viewing this page via a <code>file://</code> URL, the "next" and "previous" Glyphicon buttons on the left and right might not load/display properly due to web browser security rules.</p>-->
                        <p>Probieren Sie Gratis den neuen Rechner deines Rationes!</p>
                        <p><a class="btn btn-lg btn-primary" href="/products" role="button">Nährwertzähler</a></p>
                    </div>
                </div>
            </div>
            <div class="item">
                <img class="second-slide" src="images/girl.jpg" alt="Second slide">
                <div class="container">
                    <div class="carousel-caption">
                        <h2>Rationszähler.</h2>
                        <!--<p>Note: If you're viewing this page via a <code>file://</code> URL, the "next" and "previous" Glyphicon buttons on the left and right might not load/display properly due to web browser security rules.</p>-->
                        <p>Probieren Sie Gratis den neuen Rechner deines Rationes!</p>
                        <p><a class="btn btn-lg btn-primary" href="/products" role="button">Nährwertzähler</a></p>
                    </div>
                </div>
            </div>
            <div class="item">
                <img class="third-slide" src="images/boy.jpg" alt="Third slide">
                <div class="container">
                    <div class="carousel-caption">
                        <h2>Rationszähler.</h2>
                        <!--<p>Note: If you're viewing this page via a <code>file://</code> URL, the "next" and "previous" Glyphicon buttons on the left and right might not load/display properly due to web browser security rules.</p>-->
                        <p>Probieren Sie Gratis den neuen Rechner deines Rationes!</p>
                        <p><a class="btn btn-lg btn-primary" href="/products" role="button">Nährwertzähler</a></p>
                    </div>
                </div>
            </div>
        </div>
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div><!-- /.carousel -->


    <br><br> <div class="container marketing">

    <!-- FOOTER -->
    <#include "includes/footer.ftl"/>
</div>

    <#include "includes/js.html" />
</body>
</html>
