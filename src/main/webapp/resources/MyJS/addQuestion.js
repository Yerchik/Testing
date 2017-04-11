/**
 * Created by Yerchik on 06.04.2017.
 */

var numberOfWrong = 1;
var namberOfQuestions = document.getElementById('namberOfQuestions').innerHTML;
$(document).ready(function () {

    showNumberMessage(namberOfQuestions);

    $.ajax({
        url: '/user/info',
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            $("#credentials").text(response.name + " " + response.secondName);
        }
    })

})


function wrongAdd() {
    if (numberOfWrong == 1) {
        $("#1").show(300);
    }
    if (numberOfWrong == 2) {
        $("#2").show(300);
    }
    if (numberOfWrong == 3) {
        $("#3").show(300);
    }
    if (numberOfWrong == 4) {
        $("#4").show(300);
    }
    numberOfWrong++;
}

function addClearPage() {
    if (validate()) {
        $.ajax({
            url: '/addQuestion',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(getQuestionObject()),
            success: function () {
                namberOfQuestions++;
                if (namberOfQuestions == 0) {
                    namberOfQuestions = 0 - document.getElementById('namberOfQuestions').innerHTML;
                }
                cleanErrorMessages();
                showNumberMessage(namberOfQuestions);
                numberOfWrong = 1;
                $("#addRighAnswer").val('');
                $("#addQuestion").val('');
                $("#addWrongAnswer1").val('');
                $("#addWrongAnswer2").val('');
                $("#addwronganswer3").val('');
                $("#addwronganswer4").val('');
                $("#addwronganswer5").val('');
                $("#1").hide();
                $("#2").hide();
                $("#3").hide();
                $("#4").hide();
            }
        })
    }

}

function getQuestionObject() {
    return {
        subject: document.getElementById('subject').innerHTML,
        topic: document.getElementById('topic').innerHTML,
        question: $("#addQuestion").val(),
        rightAnswer: $("#addRighAnswer").val(),
        wAnswer1: $("#addWrongAnswer1").val(),
        wAnswer2: $("#addWrongAnswer2").val(),
        wAnswer3: $("#addwronganswer3").val(),
        wAnswer4: $("#addwronganswer4").val(),
        wAnswer5: $("#addwronganswer5").val()
    }
}

function showNumberMessage(namberOfQuestions) {
    var html = "";

    if (namberOfQuestions > 0) {
        html = "Add question (added " + namberOfQuestions + " questions)"
    }

    if (namberOfQuestions == -1) {
        {
            html = "Add question (You have to add 1 question)"
        }
    }
    if (namberOfQuestions < -1) {
        html = "Add question (You have to add " + ( 0 - namberOfQuestions) + " questions)"
    }

    $("#namberLeft").append(html);
}
function cleanErrorMessages() {
    $("#namberLeft").empty();
}

function validate() {
    var object = getQuestionObject();
    if (object.question == "") {
        cleanErrorMessages2();
        showErrorMessage("a question");
        return false;
    }
    if (object.rightAnswer == "") {
        cleanErrorMessages2();
        showErrorMessage("a right answer");
        return false;
    }
    if (object.wAnswer1 == "") {
        cleanErrorMessages2();
        showErrorMessage("at least one wrong answer");
        return false;
    }
    cleanErrorMessages2();
    return true;
}

function showErrorMessage(message) {

    var html = "<div class='alert alert-danger'>"
        + "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>"
        + "<strong>Oh snap!</strong> Write <a href='#' class='alert-link'>" + message + "</a>."
        + "</div>"


    $("#errorMessage").append(html);
}

function cleanErrorMessages2() {
    $("#errorMessage").empty();
}