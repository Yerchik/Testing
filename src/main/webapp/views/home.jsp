<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Yulian
  Date: 20.03.2017
  Time: 13:17
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

    <title>Testing</title>

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
                    <sec:authorize access="hasRole('ROLE_ADMIN')"><img class="img-circle"
                                                                       src="/resources/images/photos/admin-icon.jpg"
                                                                       alt=""></sec:authorize>
                </a>
                <div class="media-body">
                    <h4 class="media-heading" id="credentials"></h4>
                    <span class="user-options"><a href="#"><i class="glyphicon glyphicon-user"></i></a>
                              <a href="#"><i class="glyphicon glyphicon-envelope"></i></a>
                              <a href="#"><i class="glyphicon glyphicon-cog"></i></a>
                              <a href="/logout"><i class="glyphicon glyphicon-log-out"></i></a>
							</span>
                </div>
            </div><!-- media -->


            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a href="/"><i class="fa fa-home"></i> <span>Home page</span></a></li>
                <li class="parent"><a href="#"><i class="fa fa-suitcase"></i> <span>Menu:</span></a>
                    <ul class="children">
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <li><a href="#" onclick="example()">Pass a test</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li><a href="#" onclick="addTest()">Add test</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li><a href="#" onclick="editTest()">Edit or delete test</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <li><a href="#" onclick="usersResult()">Show result of users</a></li>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_USER')">
                            <li><a href="#" onclick="showMyResult()">Show my result</a></li>
                        </sec:authorize>
                    </ul>
                </li>

            </ul>

        </div><!-- leftpanel -->

        <div class="mainpanel">

            <div id="content" class="contentpanel">

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div>

                    <div class='panel panel-default'>
                            <%--<a href="/addTest">--%>
                        <div class='panel-body'>
                            <h1 class='md-title mt0 mb5'>Add test</h1>
                        </div>
                        <div class='panel-footer' style="display: none;" id="AddTestPanel">
                            <form action="/addTest" method="post">
                                <div class="input-group mb15">
                                        <span class="input-group-addon"><p
                                                class='md-title mt0 mb5'>New Subject:</p></span>
                                    <input id="addsubject" type="text" class="form-control">

                                    <label for="subjectselekt" class="input-group-addon">
                                        <p class='md-title mt0 mb0'>or select a subject: <select name="subject"
                                                                                                 id="subjectselekt"
                                                                                                 class='md-title mt0 mb0 '>

                                        </select></p>

                                    </label>

                                </div><!-- input-group -->

                                <div class="input-group mb15">
                                        <span class="input-group-addon"><p
                                                class='md-title mt0 mb5'>topic of test:</p></span>
                                    <input id="addtopic" type="text" class="form-control">
                                </div><!-- input-group -->

                                <div class="row">
                                    <div class="col-sm-5">
                                        <div class="input-group mb15">
                                            <span class="input-group-addon"><p class='md-title mt0 mb5'>number Of Questions:</p></span>
                                            <input id="addnumberOfQuestions" type="text" class="form-control">
                                        </div><!-- input-group -->
                                    </div>
                                    <div class="col-sm-4">
                                        <div class="input-group mb15">
                                            <span class="input-group-addon"><p
                                                    class='md-title mt0 mb5'>number in a day:</p></span>
                                            <input id="addnumberInADay" type="text" class="form-control">
                                        </div><!-- input-group -->
                                    </div>
                                    <div class="col-sm-3">
                                        <div class="input-group mb15">
                                                <span class="input-group-addon"><p
                                                        class='md-title mt0 mb5'>time (min):</p></span>
                                            <input id="addtime" type="text" class="form-control">
                                        </div><!-- input-group -->
                                    </div>
                                </div>

                                <div class="clearfix">
                                    <div id="errorMessage"></div>
                                    <div class="pull-right">
                                        <button id="addTestSubmit" type="submit" class="btn btn-success">Next <i
                                                class="fa fa-angle-right ml5"></i></button>
                                    </div>
                                </div>
                            </form>
                        </div>

                    </div>

                    <div class='panel panel-default'>

                        <div class='panel-body'>
                            <h1 class='md-title mt0 mb5'>Edit or delete test</h1>
                        </div>
                        <div class='panel-footer' style="display: none;" id="EditTestPanel">


                            <div class="col-sm-6">
                                <p></p><label for="subjectselekt" class="input-group-addon">
                                <p class='md-title mt0 mb0'>select a subject: <select name="subject"
                                                                                      id="editSubjectSelekt"
                                                                                      class='md-title mt0 mb0" '>

                                </select></p>
                            </label>
                                <p></p>
                                <br>
                                <div class="clearfix">
                                    <div id="errorEditSubjectMessage" hidden>
                                        <div class='alert alert-danger'>
                                            <button type='button' class='close' data-dismiss='alert'
                                                    aria-hidden='true'>&times;</button>
                                            <strong>Oh snap!</strong> Select a Subject</a>
                                        </div>
                                    </div>
                                    <div class="pull-right">
                                        <button id="deleteSubjectSubmit" type="submit" class="btn btn-success"
                                                onclick="deleteSubject()">
                                            Delete Subject<i
                                                class="fa fa-angle-right ml5"></i></button>
                                    </div>
                                </div>
                            </div>
                            <wbr>

                            <div id="editTestDiv" class="col-sm-6" hidden>
                                <p></p><label for="subjectselekt" class="input-group-addon">
                                <p class='md-title mt0 mb0'>select a test: <select name="subject"
                                                                                   id="editTestSelekt"
                                                                                   class='md-title mt0 mb0 '>

                                </select></p>
                            </label>
                                <p></p>
                                <br>
                                <div class="clearfix">
                                    <div id="errorEditTestMessage" hidden>
                                        <div class='alert alert-danger'>
                                            <button type='button' class='close' data-dismiss='alert'
                                                    aria-hidden='true'>&times;</button>
                                            <strong>Oh snap!</strong> Select a Subject</a>
                                        </div>
                                    </div>
                                    <div class="pull-right">
                                        <button id="deleteTestSubmit" type="submit" class="btn btn-success"
                                                onclick="deleteTopic()">Delete
                                            Test<i
                                                    class="fa fa-angle-right ml5"></i></button>
                                    </div>
                                </div>
                            </div>
                            <br>
                            <div class="clearfix" id="addQuestionButton" hidden>

                                <div class="pull-left">
                                    <div id="errorEditAddQuestMessage" hidden>
                                        <div class='alert alert-danger'>
                                            <button type='button' class='close' data-dismiss='alert'
                                                    aria-hidden='true'>&times;</button>
                                            <strong>Oh snap!</strong> Select a Subject and Topic.</a>
                                        </div>
                                    </div>
                                    <button id="addQuestionSubmit" type="submit" class="btn btn-success"
                                            onclick="addQuestion()">Add Question <i
                                            class="fa fa-angle-right ml5"></i></button>
                                </div>
                            </div>


                        </div>

                    </div>


                    <div class='panel panel-default'>

                        <div class='panel-body'>
                            <h1 class='md-title mt0 mb5'>show result of users</h1>
                        </div>
                        <div class='panel-footer' style="display: none;" id="ShowUsersResult">

                            <div class="col-sm-6">
                                <p></p><label for="subjectselekt" class="input-group-addon">
                                <p class='md-title mt0 mb0'>select User: <select name="subject"
                                                                                 id="selektUser"
                                                                                 class='md-title mt0 mb0" '>

                                </select></p>
                            </label>
                                <p></p>
                                <br>

                            </div>
                        </div>

                        <div class='panel-footer' id="usershowMyResult" hidden>

                            <div class='panel-footer'>
                                <div class="col-sm-3">
                                    <div class="clearfix">
                                        <div class="pull-left">
                                            <button id="usershowAllResult" type="submit" class="btn btn-success"
                                                    onclick="usershowUserResult()">Show
                                                all Users result<i
                                                        class="fa fa-angle-right ml5"></i></button>
                                        </div>
                                    </div>


                                </div>
                                <div class="col-sm-3">
                                    <div class="clearfix">
                                        <div class="pull-left">
                                            <button id="userfilterBySubject" type="submit" class="btn btn-success"
                                                    onclick="usershowsubjectSelect()">
                                                Filter by subject<i
                                                    class="fa fa-angle-right ml5"></i></button>
                                        </div>
                                    </div>

                                    <br/>
                                    <div class="panel-footer md-title mt0 mb5" id="usersubjectFilterSelektor"
                                         hidden>
                                        <label for="subjectPass"
                                               style="text-align: left">
                                            select a
                                            subject: <select name="subject" id="usersubjectFilter"
                                                             class='md-my2 mt0 mb5'></select></label>
                                    </div>
                                </div>

                                <div class="col-sm-3">
                                    <div class="clearfix">
                                        <div class="pull-left">
                                            <button id="userfilterByTest" type="submit" class="btn btn-success"
                                                    onclick="usershowTopicSelect()">Filter
                                                by test<br/> of subject<i
                                                        class="fa fa-angle-right ml5"></i></button>
                                        </div>
                                    </div>
                                    <br/>
                                    <div class="panel-footer md-title mt0 mb5" id="usertopicFilterSelektor"
                                         hidden>
                                        <label for="subjectPass"
                                               style="text-align: left">
                                            select a
                                            topic: <select name="subject" id="usertopicFilter"
                                                           class='md-my2 mt0 mb5'></select></label>
                                    </div>

                                </div>

                                <div class="col-sm-3">
                                    <div class="clearfix">
                                        <div class="pull-left">
                                            <button id="userfilterByDate" type="submit" class="btn btn-success"
                                                    onclick="usershowDateSelect()">Filter
                                                by date<i
                                                        class="fa fa-angle-right ml5"></i></button>
                                        </div>
                                    </div>

                                    <br/>
                                    <div class="panel-footer md-title mt0 mb5" id="userdateFilterSelektor"
                                         hidden>
                                        <label for="subjectPass"
                                               style="text-align: left">
                                            select a
                                            date: <select name="subject" id="userdateFilter"
                                                          class='md-my2 mt0 mb5'></select></label>
                                    </div>


                                </div>
                            </div>

                            <div id="userfiltrError"></div>
                            <br/>

                            <div class='panel-footer' id="usermyResult" hidden>

                                <table class="table table-responsive" id="usertableMyResult">

                                </table>
                            </div>


                        </div>


                    </div>
                    </sec:authorize>

                    <sec:authorize access="hasRole('ROLE_USER')">
                        <div>

                            <div class='panel panel-default'>
                                <div id="passATest" class='panel-body'>
                                    <h1 class='md-title mt0 mb5'>pass a test</h1>
                                </div>

                                <div id="passBody" class='panel-footer' style="display: none;">


                                    <p></p><label for="subjectPass" class="input-group-addon md-title mt0 mb5"
                                                  style="text-align: left">
                                    select a
                                    subject: <select name="subject" id="subjectPass" class='md-title mt0 mb5'></select>
                                </label>
                                    <p></p>

                                    <div id="selectTest" name="test" style="display: none;">
                                        <p></p><label for="testPass" class='input-group-addon md-title mt0 mb5'
                                                      style="text-align: left">select a test:
                                        <select class='md-title mt0 mb5' name="test" id="testPass"></select></label>
                                        <p></p>
                                    </div>
                                    <h1 class='md-title mt0 mb5' id="numberForDay"></h1>
                                    <div class="clearfix">
                                        <div class="pull-left">
                                            <button id="passATestButton" type="submit" class="btn btn-success"
                                                    onclick="pass()"
                                                    style="display: none;">Next <i
                                                    class="fa fa-angle-right ml5"></i></button>
                                        </div>
                                    </div>

                                </div>

                            </div>

                            <div class='panel panel-default'>

                                <div class='panel-body'>
                                    <h1 class='md-title mt0 mb5'>Show my result</h1>
                                </div>
                                <div class='panel-footer' style="display: none;" id="showMyResult">

                                    <div class='panel-footer'>
                                        <div class="col-sm-3">
                                            <div class="clearfix">
                                                <div class="pull-left">
                                                    <button id="showAllResult" type="submit" class="btn btn-success"
                                                            onclick="showUserResult()">Show
                                                        all result<i
                                                                class="fa fa-angle-right ml5"></i></button>
                                                </div>
                                            </div>


                                        </div>
                                        <div class="col-sm-3">
                                            <div class="clearfix">
                                                <div class="pull-left">
                                                    <button id="filterBySubject" type="submit" class="btn btn-success"
                                                            onclick="showsubjectSelect()">
                                                        Filter by subject<i
                                                            class="fa fa-angle-right ml5"></i></button>
                                                </div>
                                            </div>

                                            <br/>
                                            <div class="panel-footer md-title mt0 mb5" id="subjectFilterSelektor"
                                                 hidden>
                                                <label for="subjectPass"
                                                       style="text-align: left">
                                                    select a
                                                    subject: <select name="subject" id="subjectFilter"
                                                                     class='md-my2 mt0 mb5'></select></label>
                                            </div>
                                        </div>

                                        <div class="col-sm-3">
                                            <div class="clearfix">
                                                <div class="pull-left">
                                                    <button id="filterByTest" type="submit" class="btn btn-success"
                                                            onclick="showTopicSelect()">Filter
                                                        by test<br/> of subject<i
                                                                class="fa fa-angle-right ml5"></i></button>
                                                </div>
                                            </div>
                                            <br/>
                                            <div class="panel-footer md-title mt0 mb5" id="topicFilterSelektor"
                                                 hidden>
                                                <label for="subjectPass"
                                                       style="text-align: left">
                                                    select a
                                                    topic: <select name="subject" id="topicFilter"
                                                                   class='md-my2 mt0 mb5'></select></label>
                                            </div>

                                        </div>

                                        <div class="col-sm-3">
                                            <div class="clearfix">
                                                <div class="pull-left">
                                                    <button id="filterByDate" type="submit" class="btn btn-success"
                                                            onclick="showDateSelect()">Filter
                                                        by date<i
                                                                class="fa fa-angle-right ml5"></i></button>
                                                </div>
                                            </div>

                                            <br/>
                                            <div class="panel-footer md-title mt0 mb5" id="dateFilterSelektor"
                                                 hidden>
                                                <label for="subjectPass"
                                                       style="text-align: left">
                                                    select a
                                                    date: <select name="subject" id="dateFilter"
                                                                  class='md-my2 mt0 mb5'></select></label>
                                            </div>


                                        </div>
                                    </div>

                                    <div id="filtrError"></div>
                                    <br/>

                                    <div class='panel-footer' id="myResult" hidden>

                                        <table class="table table-responsive" id="tableMyResult">

                                        </table>
                                    </div>


                                </div>

                            </div>
                        </div>
                    </sec:authorize>

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
<script src="/resources/js/MyJS/home.js"></script>
</body>
</html>
