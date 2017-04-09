/**
 * Created by Yerchik on 06.04.2017.
 */
var numberOfQwestion = 0;
var numberRight = 0;

var questions = [];
var answers = [];
var value = [];
var stop = false;
$(document).ready(function () {


    $.ajax({
        url: '/user/info',
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            $("#credentials").text(response.name + " " + response.secondName);

        }
    })

    $.ajax({
        url: '/getQuestions/' + document.getElementById('subject').innerHTML + ',' +
        document.getElementById('topic').innerHTML,
        method: 'GET',
        contentType: 'application/json',
        success: function update(response) {
            for (var i = 0; i < response.length; i++) {
                questions[i] = response[i].questionText;
                answers[i] = [];
                value[i] = [];
                for (var j = 0; j < response[i].answers.length; j++) {
                    answers[i].push(response[i].answers[j].answerText);
                    value[i].push(response[i].answers[j].value);
                }
            }
            show();
        }
    })


});

function show() {
    $("#answer1").empty();
    var html = "";
    $("#question").text((numberOfQwestion + 1) + " of " + document.getElementById('number').innerHTML + ". " + questions[numberOfQwestion]);
    for (var i = 0; i < answers[numberOfQwestion].length; i++) {
        html += "<p></p>" +
            "<label for='r" + i + "' class='input-group-addon md-my' style='text-align: left'>"
            + "<input type='radio' id='r" + i + "' value='" + value[numberOfQwestion][i] + "' name='answer'>"
            +  " " + answers[numberOfQwestion][i] + "</label>"
            + "<p></p>"
    }

    $("#answer1").append(html);

}

function getResultObject(){
    return {
        number: numberRight,
        subject: document.getElementById('subject').innerHTML,
        topic: document.getElementById('topic').innerHTML,
    }
}
function addResult() {
    $("#test").hide();
    $("#timer").hide();
    $.ajax({
        url:'/addResult/',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(getResultObject()),
        success: function (){
            html = numberRight + " of " + numberOfQwestion;
            $("#result").append(html);
            $("#end").show();
        }
    })
}

function next() {
    if ($("input[name=answer]:checked").val() == null) {
        showErrorMessage();
    }
    else {
        cleanErrorMessages()
        var html = "";
        numberOfQwestion++;
        if($("input[name=answer]:checked").val() == "true"){
            numberRight++;
        }

        if (numberOfQwestion == document.getElementById('number').innerHTML) {
            clearInterval(interval);
            addResult();
        }
        show();
    }
}

function end() {
    addResult();

}

function showErrorMessage() {

    var html = "<div class='alert alert-danger'>"
        + "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>"
        + "<strong>Oh snap!</strong> You have to choose something"
        + "</div>"


    $("#errorMessage").append(html);
}

function cleanErrorMessages() {
    $("#errorMessage").empty();
}


var time = document.getElementById('time').innerHTML;
var h = Math.floor(time / 60);
var m = time % 60;
var s = 1;
var interval = setInterval(function timer() {
    $("#hours").empty();
    $("#minuts").empty();
    $("#seconds").empty();
    s--;
    if (s < 10) {
        $("#seconds").append('0' + s);
    } else {
        $("#seconds").append(s)
    }
    if (m < 10) {
        $("#minuts").append(': ' + '0' + m + ' :');
    } else {
        $("#minuts").append(': ' + m + ' :')
    }
    $("#hours").append(h);
    if (s == 0 && m == 0 && h == 0) {
        numberOfQwestion = document.getElementById('number').innerHTML;
        end();
    }
    if (m == 0 && h != 0 && s == 0) {
        h--;
        m = 60;
    }
    if (s == 0) {
        m--;
        s = 60;
    }
}, 1000);