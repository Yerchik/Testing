<%--
  Created by IntelliJ IDEA.
  User: Yulian
  Date: 20.03.2017
  Time: 15:09
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

    <title>Sign in</title>

    <link href="/resources/css/style.default.css" rel="stylesheet">

</head>

<body class="signin">


<section>

    <div class="panel panel-signin">
        <div class="panel-body">
            <div class="logo text-center">
                <img src="/resources/images/TESTING-white.png" alt="Moldivate Logo" >
            </div>
            <br />
            <h4 class="text-center mb5">Already a Member?</h4>
            <p class="text-center">Sign in to your account</p>

            <div class="mb30"></div>

            <form action="/sign-in-processing" method="post">
                <div class="input-group mb15">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                    <input name="username" type="text" class="form-control" placeholder="Username">
                </div><!-- input-group -->
                <div class="input-group mb15">
                    <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                    <input name="password" type="password" class="form-control" placeholder="Password">
                </div><!-- input-group -->


                    <div id="errorMessage" hidden><div class='alert alert-danger' >
                        <button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>
                        <strong>Oh snap!</strong> Wrong login and password,<br>
                        or your email is not confirmed.
                    </div></div>


                <div class="clearfix">
                    <div class="pull-right">
                        <button type="submit" class="btn btn-success">Sign In <i class="fa fa-angle-right ml5"></i></button>
                    </div>
                </div>
            </form>

        </div><!-- panel-body -->
        <div class="panel-footer">
            <a href="/sign-up" class="btn btn-primary btn-block">Not yet a Member? Create Account Now</a>
        </div><!-- panel-footer -->
    </div><!-- panel -->

</section>


<script src="/resources/js/jquery-1.11.1.min.js"></script>
<script src="/resources/js/jquery-migrate-1.2.1.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/modernizr.min.js"></script>
<script src="/resources/js/pace.min.js"></script>
<script src="/resources/js/retina.min.js"></script>
<script src="/resources/js/jquery.cookies.js"></script>

<script src="/resources/js/custom.js"></script>
<script src="/resources/js/MyJS/SignIn.js"></script>

</body>
</html>
