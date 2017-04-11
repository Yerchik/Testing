<%--
  Created by IntelliJ IDEA.
  User: Yerchik
  Date: 26.03.2017
  Time: 23:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Pass A Test</title>

    <link href="/resources/css/style.default.css" rel="stylesheet">
    <link href="/resources/css/morris.css" rel="stylesheet">
    <link href="/resources/css/select2.css" rel="stylesheet"/>
    <link href="/resources/js/css/css3clock/css/style.css" rel="stylesheet">
    <link href="/resources/css/style.calendar.css" rel="stylesheet">

</head>

<body>

<header>
    <div class="headerwrapper">
        <div class="header-left">
            <a href="#" class="logo">
                <img src="/resources/images/text2.png" alt=""/>
            </a>
            <div class="pull-right">
                <a href="#" class="menu-collapse">
                    <i class="fa fa-bars"></i>
                </a>
            </div>
        </div><!-- header-left -->

    </div><!-- headerwrapper -->
</header>

<section>
    <div class="mainwrapper">
        <div class="leftpanel">
            <div class="media profile-left">
                <a class="pull-left profile-thumb" href="#">
                    <sec:authorize access="hasRole('ROLE_USER')"><img class="img-circle"
                                                                      src="/resources/images/photos/user.png"
                                                                      alt=""></sec:authorize>
                </a>
                <div class="media-body">
                    <h4 class="media-heading" id="credentials"></h4>
                    <span class="user-options">
                              <a href="/logout"><i class="glyphicon glyphicon-log-out"></i></a>
							</span>
                </div>
            </div><!-- media -->


            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a href="/"><i class="fa fa-home"></i> <span>Home page</span></a></li>


            </ul>

        </div><!-- leftpanel -->

        <div class="mainpanel">

            <div class="panel panel-default'" hidden>
                <label id="subject">${subject}</label><label id="topic">${topic}</label><label
                    id="time">${time}</label><label id="number">${number}</label>
            </div>


            <div id="content" class="contentpanel">

                <div class='panel panel-default' id="test">

                    <div class='panel-body'>
                        <h1 class='md-my mt0 mb5' id="question"></h1>
                    </div>
                    <div class='panel-footer md-my' id="answer1">

                        <%--<p>--%>
                        <%--<label for="r1" class="input-group-addon md-my" style="text-align: left">--%>
                        <%--<input type="radio" id="r1" value="first_checkbox" name="answer">--%>
                        <%--This is the first </label>--%>
                        <%--</p>--%>

                    </div>



                </div>
                <div id="errorMessage"></div>

                <div id="timer">
                    <br/>
                    <div class="panel-footer col-sm-2 pull-left" style="text-align: center">
                        <p><label class='md-title mt0 mb5'> Time left: </label><wbr></p>
                        <p><nobr><label class='md-title mt0 mb5' id="hours"></label>
                            <label class='md-title mt0 mb5' id="minuts"></label>
                            <label class='md-title mt0 mb5' id="seconds"></label></nobr></p>
                    </div>

                    <div class="col-sm-6 pull-right">
                        <button id="nextbut" type="submit" class="btn btn-success"
                                onclick="next()">
                            Next<i
                                class="fa fa-angle-right ml5"></i></button>
                    </div>
                </div>

                <div class='panel panel-default' id="end" hidden>
                    <div class='panel-body'>
                    <h1 class='md-my mt0 mb5' id="result"> Your result: </h1>
                    </div>

                    <div class="clearfix mt10 mb10">
                        <a href="/">
                            <div class=" col-sm-10 pull-left">
                                <button id="home" type="submit" class="btn btn-success">Home page<i
                                        class="fa fa-angle-right ml5"></i></button>
                            </div>
                        </a>
                    </div>
                </div>



            </div><!-- contentpanel -->
        </div><!-- mainpanel -->
    </div><!-- mainwrapper -->
</section>

<script src="/resources/js/jquery-1.11.1.min.js"></script>
<script src="/resources/js/jquery-migrate-1.2.1.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/modernizr.min.js"></script>
<script src="/resources/js/pace.min.js"></script>
<script src="/resources/js/retina.min.js"></script>
<script src="/resources/js/jquery.cookies.js"></script>
<script src="/resources/js/jquery.scrollTo.min.js"></script>
<script src="/resources/js/jquery.slimscroll.js"></script>

<script src="/resources/js/jquery.sparkline.min.js"></script>
<script src="/resources/js/morris.min.js"></script>
<script src="/resources/js/raphael-2.1.0.min.js"></script>
<script src="/resources/js/bootstrap-wizard.min.js"></script>
<script src="/resources/js/select2.min.js"></script>
<script src="/resources/js/css3clock/js/css3clock.js"></script>
<script src="/resources/js/jquery-ui-1.10.3.min.js"></script>
<script src="/resources/js/moment.min.js"></script>
<script src="/resources/js/fullcalendar.min.js"></script>
<script src="/resources/js/custom.js"></script>
<script src="/resources/MyJS/passATest.js"></script>
</body>
</html>

