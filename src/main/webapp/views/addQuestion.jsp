<%--
  Created by IntelliJ IDEA.
  User: Yerchik
  Date: 28.03.2017
  Time: 22:42
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

    <title>Add Queston</title>

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
                    <sec:authorize access="hasRole('ROLE_ADMIN')"><img class="img-circle"
                                                                       src="/resources/images/photos/admin-icon.jpg"
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
            <div style="display: none;" class="panel panel-default'">
                <label id="subject">${subject}</label><label id="topic">${topic}</label><label
                    id="namberOfQuestions">${namberOfQuestions}</label>
            </div>

            <div id="content" class="contentpanel">
                <div class='panel panel-default'>
                    <div class='panel-body'>
                        <h1 class='md-title mt0 mb5' id="namberLeft"></h1>
                    </div>
                    <div class='panel-footer'>
                        <input id="addQuestion" type="text" class="form-control">
                    </div>
                </div>

                <div class='panel panel-default'>
                    <div class='panel-body'>
                        <h1 class='md-title mt0 mb5'>Add answers</h1>
                    </div>
                    <div class='panel-footer'>
                        <div class="input-group mb5">
                                        <span class="input-group-addon"><p
                                                class='md-title mt0 mb0'>Righ Answer:</p></span>
                            <input id="addRighAnswer" type="text" class="form-control">
                        </div>
                    </div>

                    <div class='panel-footer'>
                        <div class="input-group mb5">
                                        <span class="input-group-addon"><p
                                                class='md-title mt0 mb0'>wrong Answer:</p></span>
                            <input id="addWrongAnswer1" type="text" class="form-control">
                        </div>
                    </div>
                    <div class='panel-footer' id="1" hidden>
                        <div class="input-group mb0">
                                        <span class="input-group-addon"><p
                                                class='md-title mt0 mb0'>wrong Answer:</p></span>
                            <input id="addWrongAnswer2" type="text" class="form-control">
                        </div>
                    </div>
                    <div class='panel-footer' id="2" hidden>
                        <div class="input-group mb0">
                                        <span class="input-group-addon"><p
                                                class='md-title mt0 mb0'>wrong Answer:</p></span>
                            <input id="addwronganswer3" type="text" class="form-control">
                        </div>
                    </div>
                    <div class='panel-footer' id="3" hidden>
                        <div class="input-group mb0">
                                        <span class="input-group-addon"><p
                                                class='md-title mt0 mb0'>wrong Answer:</p></span>
                            <input id="addwronganswer4" type="text" class="form-control">
                        </div>
                    </div>
                    <div class='panel-footer' id="4" hidden>
                        <div class="input-group mb0">
                                        <span class="input-group-addon"><p
                                                class='md-title mt0 mb0'>wrong Answer:</p></span>
                            <input id="addwronganswer5" type="text" class="form-control">
                        </div>
                    </div>
                    <div id="errorMessage"></div>

                    <div class='panel-footer '>
                        <div class="clearfix mt10 mb10">
                            <div class="pull-left">
                                <button id="addWrong" type="submit" class="btn btn-success" onclick="wrongAdd()">Add
                                    Wrong<i
                                            class="fa fa-angle-right ml5"></i></button>
                            </div>

                            <div class="pull-right">
                                <button id="addNewQuestion" type="submit" class="btn btn-success"
                                        onclick="addClearPage()">
                                    Add New Question<i
                                        class="fa fa-angle-right ml5"></i></button>
                            </div>
                        </div>
                        <div class="clearfix mt10 mb10">
                            <a href="/">
                                <div class="pull-right">
                                    <button id="home" type="submit" class="btn btn-success">Home page<i
                                            class="fa fa-angle-right ml5"></i></button>
                                </div>
                            </a>
                        </div>
                    </div>
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
<script src="/resources/MyJS/addQuestion.js"></script>
</body>
</html>
