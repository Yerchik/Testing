/**
 * Created by Yerchik on 06.04.2017.
 */

function getUserObject() {
    return {name:$("#name").val(), secondName: $("#secondName").val(), email: $("#email").val(), login: $("#login").val(), password: $("#password").val()};
}

$("#submit").click(function (e) {
    e.preventDefault();
    if(validate()){
        cleanErrorMessages()
        $.ajax({
            url:'/sign-up',
            method:'POST',
            contentType:'application/json',
            data: JSON.stringify(getUserObject()),
            success:function () {
                window.location.assign("/sign-in");
                cleanErrorMessages();
            }
        });
        setTimeout( function showSignUpErrorMessage() {
            var html = "<div class='alert alert-danger'>"
                +"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>"
                +"<strong>Oh snap!</strong> Somebody has used this Email or Login."
                +"</div>"
            $("#errorMessage").append(html);
        }, 4500);
    }


});

function validate() {
    var object = getUserObject();
    var emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var nameAndSecondNameRegex = /[^0-9]/;
    var loginRegex = /^([а-яґєії`´ʼ’ʼ’]+)/ui;

    if(object.name == "" || !object.name.match(nameAndSecondNameRegex)){
        cleanErrorMessages();
        showErrorMessage("Name");
        return false;
    }
    if(object.secondName == "" || !object.secondName.match(nameAndSecondNameRegex)){
        cleanErrorMessages();
        showErrorMessage("Secondname");
        return false;
    }
    if(object.email == "" || !object.email.match(emailRegex)){
        cleanErrorMessages();
        showErrorMessage("Email");
        return false;
    }
    if(object.login == "" || object.secondName.match(loginRegex)){
        cleanErrorMessages();
        showErrorMessage("Login");
        return false;
    }
    if(object.password != $("#passwordConfirm").val() || object.password == ""){
        cleanErrorMessages();
        showErrorMessage("Password");
        return false;
    }
    return true;

}

function showErrorMessage(message) {

    var html = "<div class='alert alert-danger'>"
        +"<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>"
        +"<strong>Oh snap!</strong> Change a <a href='#' class='alert-link'>"+message+"</a> and try submitting again."
        +"</div>"


    $("#errorMessage").append(html);
}

function cleanErrorMessages() {
    $("#errorMessage").empty();
}