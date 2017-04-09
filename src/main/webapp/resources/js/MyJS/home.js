/**
 * Created by Yerchik on 06.04.2017.
 */
var isActivated = false;
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
        url: '/getSubjects',
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            var s1 = document.querySelector("#subjectselekt").options;
            var s2 = document.querySelector("#editSubjectSelekt").options;
            for (var i = 0; i < s1.length; i++) {
                s1[i] = null;
            }
            for (var i = 0; i < s2.length; i++) {
                s2[i] = null;
            }
            for (var j = 0; j < response.length; j++) {
                s1[j] = new Option(response[j].subjectName, response[j].subjectName, true);
                s2[j + 1] = new Option(response[j].subjectName, response[j].subjectName, true);
            }

        }
    })

    $.ajax({
        url: '/allUsers',
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            var s1 = document.querySelector("#selektUser").options;
            for (var i = 0; i < s1.length; i++) {
                s1[i] = null;
            }
            for (var j = 0; j < response.length; j++) {
                s1[j + 1] = new Option(response[j].secondName + " " + response[j].name, response[j].login, true);
            }
        }
    })


    subjectPass();

    $('.panel-body').on('click', function () {
        $(this).next().slideToggle(300);
        $("#usershowMyResult").hide(300);

    });


})
function example() {
    $("#passBody").slideToggle(300);
}
function addTest() {
    $("#AddTestPanel").slideToggle(300);
}
function editTest() {
    $("#EditTestPanel").slideToggle(300);
}
function usersResult() {
    $("#ShowUsersResult").slideToggle(300);
    $("#usershowMyResult").hide(300);

}
function showMyResult() {
    $("#showMyResult").slideToggle(300);

}


//  pass Test


function subjectPass() {
    $.ajax({
        url: '/getSubjects',
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            var s2 = document.querySelector("#subjectPass").options;
            for (var i = 0; i < s2.length; i++) {
                s2[i] = null;
            }
            for (var j = 0; j < response.length; j++) {
                s2[j + 1] = new Option(response[j].subjectName, response[j].subjectName, true);
            }

        }
    })
}


$("#subjectPass").on('change', function () {
    var s = document.querySelector("#testPass").options;
    for (var i = 0; i <= s.length; i++) {
        s[i] = null;
    }
    $("#numberForDay").text('');


    $.ajax({
        url: '/getTapeBy/' + document.getElementById('subjectPass').value,
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            for (var i = 0; i <= s.length; i++) {
                s[i] = null;
            }
            s[0] = null;
            for (var j = 0; j < response.length; j++) {
                s[j + 1] = new Option(response[j].topic, response[j].topic, true);
            }
            $("#selectTest").show(200);
            $("#passATestButton").hide();
        }
    })

})


$("#testPass").on('change', function () {

    $.ajax({
        url: '/numberLeft/' + document.getElementById('subjectPass').value
        + "," + document.getElementById('testPass').value,
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            if (response > 1) {
                $("#passATestButton").show(200);
                $("#numberForDay").text("Today you can pass this test " + response + " times.");
            }
            if (response == 1) {
                $("#passATestButton").show(200);
                $("#numberForDay").text("Today you can pass this test " + response + " time.");
            }
            if (response == -1) {
                $("#passATestButton").hide(200);
                $("#numberForDay").text("Sorry, this test isn't completed");
            }
            if (response == 0) {
                $("#passATestButton").hide(200);
                $("#numberForDay").text("Sorry, you can no longer pass this test today");
            }
        }
    })

})


//  add Test


function getTestObject() {
    if ($("#addsubject").val() == "") {
        return {
            topic: $("#addtopic").val(),
            numberOfQuestions: $("#addnumberOfQuestions").val(),
            numberInADay: $("#addnumberInADay").val(),
            time: $("#addtime").val(),
            subject: $("#subjectselekt").val()
        }
    } else return {
        topic: $("#addtopic").val(),
        numberOfQuestions: $("#addnumberOfQuestions").val(),
        numberInADay: $("#addnumberInADay").val(),
        time: $("#addtime").val(),
        subject: $("#addsubject").val()
    }
}

$("#addTestSubmit").click(function (e) {
    e.preventDefault();

    if (validate()) {
        $.ajax({
            url: '/addTest',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(getTestObject()),
            success: function () {
                window.location.assign("/addQuestion/" + getTestObject().subject + "," + getTestObject().topic + "");
            }
        })
    }
})

function pass() {
    window.location.assign("/passATest/" + document.getElementById('subjectPass').value
        + "," + document.getElementById('testPass').value + "");
}


function validate() {
    var object = getTestObject();
    var namber = /^[0-9]+$/;
    var subjectAndTopic = /^([а-яґєії`´ʼ’ʼ’]+)/ui;
    if (object.subject.match(subjectAndTopic)) {
        cleanErrorMessages();
        showTopicErrorMessage("subject");
        return false;
    }
    if (object.topic == "" || object.numberOfQuestions.match(subjectAndTopic)) {
        cleanErrorMessages();
        showTopicErrorMessage("topic");
        return false;
    }
    if (object.numberOfQuestions == "" || !object.numberOfQuestions.match(namber)) {
        cleanErrorMessages();
        showNumberErrorMessage("number Of Questions");
        return false;
    }
    if (object.numberInADay == "" || !object.numberInADay.match(namber)) {
        cleanErrorMessages();
        showNumberErrorMessage("number in a day");
        return false;
    }
    if (object.time == "" || !object.time.match(namber)) {
        cleanErrorMessages();
        showNumberErrorMessage("time");
        return false;
    }

    return true;
}

function showTopicErrorMessage(message) {

    var html = "<div class='alert alert-danger'>"
        + "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>"
        + "<strong>Oh snap!</strong> Write a " + message + " in English"
        + "</div>"


    $("#errorMessage").append(html);
}
function showNumberErrorMessage(message) {

    var html = "<div class='alert alert-danger'>"
        + "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>"
        + "<strong>Oh snap!</strong> Put a number in to <a href='#' class='alert-link'>" + message + "</a>."
        + "</div>"
    $("#errorMessage").append(html);
}

function cleanErrorMessages() {
    $("#errorMessage").empty();
}


//    My result

var show3 = false;
function showUserResult() {
    $("#myResult").show(300);
    show1 = false;
    show2 = false;
    $("#subjectFilterSelektor").hide(300);
    $("#topicFilterSelektor").hide(300);
    show4 = false;
    $("#dateFilterSelektor").hide(300);
    document.getElementById('dateFilter').value = '';
    document.getElementById('subjectFilter').value = '';
    document.getElementById('topicFilter').value = '';
    clearMyTable();
    if (show3) {
        show3 = false;
        $("#myResult").hide();
    } else {
        show3 = true;
        $("#myResult").show(300);
        getAllMyResult();
    }
}

var tableHeadHTML = "<tr>" +
    "<th>Subject </th>" +
    "<th>Topic </th>" +
    "<th>Result(%) </th>" +
    "<th>date </th>" +
    "</tr>";
var subjectResult = [];
var topicResult = [];
var resultTest = [];
var dateResult = [];

function getAllMyResult() {
    clearMyTable();
    $.ajax({
        url: '/myResult',
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            $("#tableMyResult").append(tableHeadHTML);
            for (var i = 0; i < response.length; i++) {
                subjectResult[i] = response[i].subject;
                topicResult[i] = response[i].topic;
                resultTest[i] = response[i].resultTest;
                dateResult[i] = response[i].date;
                makeTable(subjectResult[i], topicResult[i], resultTest[i], dateResult[i]);
            }

        }
    })
}
function clearMyTable() {
    $("#tableMyResult").empty();

}


function makeTable(a1, a2, a3, a4) {
    html = "<tr>" +
        "<td>" + a1 + "</td>" +
        "<td>" + a2 + "</td>" +
        "<td>" + a3 + "</td>" +
        "<td>" + a4 + "</td>" +
        "</tr>";
    $("#tableMyResult").append(html);

}

function getSubjectFiltr() {
    $.ajax({
        url: '/getSubjects',
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            var s2 = document.querySelector("#subjectFilter").options;
            for (var i = 0; i < s2.length; i++) {
                s2[i] = null;
            }
            s2[0] = null;
            for (var j = 0; j < response.length; j++) {
                s2[j + 1] = new Option(response[j].subjectName, response[j].subjectName, true);
            }
            $("#subjectFilterSelektor").show(300);
        }
    })

}
var show1 = false;
function showsubjectSelect() {
    clearMyTable();
    if (show1) {
        show4 = false;
        document.getElementById('dateFilter').value = ''
        $("#dateFilterSelektor").hide(300);
        show1 = false;
        show2 = false;
        var s2 = document.querySelector("#subjectFilter").options;
        for (var i = 0; i < s2.length; i++) {
            s2[i] = null;
        }
        document.getElementById('subjectFilter').value = '';
        $("#subjectFilterSelektor").hide(300);
        $("#topicFilterSelektor").hide(300);
    }
    else {
        show4 = false;
        $("#dateFilterSelektor").hide(300);
        document.getElementById('dateFilter').value = '';
        cleanFiltrError();
        show1 = true;
        getSubjectFiltr();
    }
}

$("#subjectFilter").on('change', function () {
    show4 = false;
    $("#dateFilterSelektor").hide(300);
    document.getElementById('dateFilter').value = '';
    clearMyTable();
    var s = document.querySelector("#topicFilter").options;
    for (var i = 0; i <= s.length; i++) {
        s[i] = null;
    }
    $("#tableMyResult").append(tableHeadHTML);
    $.ajax({
        url: '/myResult',
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            for (var i = 0; i < response.length; i++) {
                subjectResult[i] = response[i].subject;
                topicResult[i] = response[i].topic;
                resultTest[i] = response[i].resultTest;
                dateResult[i] = response[i].date;
                if (subjectResult[i] == document.getElementById('subjectFilter').value) {
                    makeTable(subjectResult[i], topicResult[i], resultTest[i], dateResult[i]);
                }
            }

            $.ajax({
                url: '/getTapeBy/' + document.getElementById('subjectFilter').value,
                method: 'GET',
                contentType: 'application/json',
                success: function (response) {
                    for (var i = 0; i <= s.length; i++) {
                        s[i] = null;
                    }
                    s[0] = null;
                    for (var j = 0; j < response.length; j++) {
                        s[j + 1] = new Option(response[j].topic, response[j].topic, true);
                    }
                }
            })

            $("#myResult").show(300);
            clearMyDataFilter();


        }
    })

})


var show2 = false;
function showTopicSelect() {
    if (show2) {
        show4 = false;
        document.getElementById('dateFilter').value = ''
        $("#dateFilterSelektor").hide(300);
        $("#topicFilterSelektor").hide(300);
        show2 = false;
        var s2 = document.querySelector("#topicFilter").options;
        for (var i = 0; i < s2.length; i++) {
            s2[i] = null;
        }
        document.getElementById('topicFilter').value = '';

    }
    else {
        show4 = false;
        $("#dateFilterSelektor").hide(300);
        document.getElementById('dateFilter').value = '';
        if (document.getElementById('subjectFilter').value != '') {
            getTopicFiltr();
            show2 = true;
            $("#topicFilterSelektor").show(300);
        } else {
            cleanFiltrError();
            showFiltrError();
        }

    }
}

function getTopicFiltr() {
    cleanFiltrError();
    var s = document.querySelector("#topicFilter").options;
    for (var i = 0; i <= s.length; i++) {
        s[i] = null;
    }
    $.ajax({
        url: '/getTapeBy/' + document.getElementById('subjectFilter').value,
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            for (var i = 0; i <= s.length; i++) {
                s[i] = null;
            }
            s[0] = null;
            for (var j = 0; j < response.length; j++) {
                s[j + 1] = new Option(response[j].topic, response[j].topic, true);
            }
            $("#topicFilterSelektor").show(300);

        }
    })
}

$("#topicFilter").on('change', function () {
    show4 = false;
    $("#dateFilterSelektor").hide(300);
    document.getElementById('dateFilter').value = '';
    clearMyTable();
    $("#tableMyResult").append(tableHeadHTML);
    $.ajax({
        url: '/myResult',
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            for (var i = 0; i < response.length; i++) {
                subjectResult[i] = response[i].subject;
                topicResult[i] = response[i].topic;
                resultTest[i] = response[i].resultTest;
                dateResult[i] = response[i].date;
                if (subjectResult[i] == document.getElementById('subjectFilter').value &&
                    topicResult[i] == document.getElementById('topicFilter').value) {
                    makeTable(subjectResult[i], topicResult[i], resultTest[i], dateResult[i]);
                }
            }

            $("#myResult").show(300);

            clearMyDataFilter();
        }
    })

})

var show4 = false;
function showDateSelect() {
    clearMyTable();
    if (show4) {
        show4 = false;
        document.getElementById('dateFilter').value = ''
        $("#dateFilterSelektor").hide(300);
    }
    else {
        cleanFiltrError();
        show4 = true;
        getDateFiltr();
        $("#dateFilterSelektor").show(300);
    }


}

function getDateFiltr() {
    var s = document.querySelector("#dateFilter").options;
    for (var i = 0; i <= s.length; i++) {
        s[i] = null;
    }
    $.ajax({
        url: '/myResult',
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            $("#tableMyResult").append(tableHeadHTML);
            s[0] = null;
            var j = 1;
            for (var i = 0; i < response.length; i++) {
                if (i == 0) {
                    s[j] = new Option(response[i].date, response[i].date, true);
                    j++;
                } else {
                    if (response[i].date != response[i - 1].date) {
                        s[j] = new Option(response[i].date, response[i].date, true);
                        j++;
                    }
                }
                subjectResult[i] = response[i].subject;
                topicResult[i] = response[i].topic;
                resultTest[i] = response[i].resultTest;
                dateResult[i] = response[i].date;
            }

        }
    })

}

function clearMyDataFilter() {
    var s = document.querySelector("#dateFilter").options;
    for (var i = 0; i < s.length; i++) {
        s[i] = null;
    }
}

$("#dateFilter").on('change', function () {
    clearMyTable();
    $("#tableMyResult").append(tableHeadHTML);
    for (var i = 0; i < dateResult.length; i++) {
        if (dateResult[i] == document.getElementById('dateFilter').value && subjectResult[i] == document.getElementById('subjectFilter').value &&
            topicResult[i] == document.getElementById('topicFilter').value) {
            makeTable(subjectResult[i], topicResult[i], resultTest[i], dateResult[i]);
        }
        if (dateResult[i] == document.getElementById('dateFilter').value && document.getElementById('subjectFilter').value == '' &&
            document.getElementById('topicFilter').value == '') {
            makeTable(subjectResult[i], topicResult[i], resultTest[i], dateResult[i]);
        }
        if (dateResult[i] == document.getElementById('dateFilter').value && subjectResult[i] == document.getElementById('subjectFilter').value &&
            document.getElementById('topicFilter').value == '') {
            makeTable(subjectResult[i], topicResult[i], resultTest[i], dateResult[i]);
        }
    }
    $("#myResult").show(300);
})

function showFiltrError() {

    var html = "<div class='alert alert-danger'>"
        + "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>"
        + "<strong>Oh snap!</strong> Select a Subject</a>."
        + "</div>"
    $("#filtrError").append(html);
}

function cleanFiltrError() {
    $("#filtrError").empty();
}


// Edit or delete test


$("#editSubjectSelekt").on('change', function () {
    $("#errorEditSubjectMessage").hide(300);
    var s = document.querySelector("#editTestSelekt").options;
    for (var i = 0; i <= s.length; i++) {
        s[i] = null;
    }

    $.ajax({
        url: '/getTapeBy/' + document.getElementById('editSubjectSelekt').value,
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            for (var i = 0; i <= s.length; i++) {
                s[i] = null;
            }
            s[0] = null;
            for (var j = 0; j < response.length; j++) {
                s[j + 1] = new Option(response[j].topic, response[j].topic, true);
            }
            $("#editTestDiv").show(300);

        }
    })

})

$("#editTestSelekt").on('change', function () {
    $("#addQuestionButton").show(300);
    $("#errorEditTestMessage").hide(300);

})

function addQuestion() {

    if (document.getElementById('editTestSelekt').value == '' || document.getElementById('editSubjectSelekt').value == '') {
        $("#errorEditAddQuestMessage").show(300);
    }
    else {
        window.location.assign("/addQuestion/" + document.getElementById('editSubjectSelekt').value + ","
            + document.getElementById('editTestSelekt').value + "");
    }
}


function deleteSubject() {
    if (document.getElementById('editSubjectSelekt').value == '') {
        $("#errorEditSubjectMessage").show(300);
    } else {
        $.ajax({
            url: '/delete/' + document.getElementById('editSubjectSelekt').value,
            method: 'POST',
            contentType: 'application/json',
            success: function () {
                alert('Subject is deleted');
                var s1 = document.querySelector("#subjectselekt").options;
                var s2 = document.querySelector("#editSubjectSelekt").options;
                for (var i = 0; i < (s2.length - 1); i++) {
                    if (s2[i].value == document.getElementById('editSubjectSelekt').value) {
                        s2[i] = s2[i + 1];
                        s1[i - 1] = s1[i];
                    }
                }
                s2[s2.length] = null;
                s1[s1.length] = null;
                $("#editTestDiv").hide(300);
                $("#addQuestionButton").hide(300);

            }
        })
    }
}

function deleteTopic() {
    if (document.getElementById('editTestSelekt').value == '' || document.getElementById('editSubjectSelekt').value == '') {
        $("#errorEditTestMessage").show(300);
    }
    else {
        $.ajax({
            url: '/deletetopic/' + document.getElementById('editSubjectSelekt').value +
            "," + document.getElementById('editTestSelekt').value,
            method: 'POST',
            contentType: 'application/json',
            success: function () {
                alert('Test is deleted')
                $("#editTestDiv").hide(300);
                $("#addQuestionButton").hide(300);

            }
        })
    }
}


// UsresResult


$("#selektUser").on('change', function () {
    usershow1 = false;
    usershow2 = false;
    usershow3 = false
    $("#usersubjectFilterSelektor").hide(300);
    $("#usertopicFilterSelektor").hide(300);
    usershow4 = false;
    $("#userdateFilterSelektor").hide(300);
    var s2 = document.querySelector("#usersubjectFilter").options;
    for (var i = 0; i < s2.length; i++) {
        s2[i] = null;
    }
    s2[0] = null;
    if (document.getElementById('selektUser').value == '') {
        $("#usershowMyResult").hide(300);
    }
    else {
        $("#usershowMyResult").hide(300);
        userclearMyTable();
        $("#usershowMyResult").show(300);
        $.ajax({
            url: '/user/info/' + document.getElementById('selektUser').value,
            method: 'GET',
            contentType: 'application/json',
            success: function (response) {
                if (response.activated) {
                    $("#isUserActivatedText").text("Account activated");
                }
                else {
                    $("#isUserActivatedText").text("Account isn't activated");
                }
                $("#isUserActivated").show(300);
            }
        })

    }

})


var usershow3 = false;
function usershowUserResult() {
    $("#usermyResult").show(300);
    usershow1 = false;
    usershow2 = false;
    $("#usersubjectFilterSelektor").hide(300);
    $("#usertopicFilterSelektor").hide(300);
    usershow4 = false;
    $("#userdateFilterSelektor").hide(300);
    var s2 = document.querySelector("#usersubjectFilter").options;
    for (var i = 0; i < s2.length; i++) {
        s2[i] = null;
    }
    s2[0] = null;
    document.getElementById('userdateFilter').value = '';
    document.getElementById('usersubjectFilter').value = '';
    document.getElementById('usertopicFilter').value = '';
    userclearMyTable();
    if (usershow3) {
        usershow3 = false;
        $("#usermyResult").hide();
    } else {
        usershow3 = true;
        $("#usermyResult").show(300);
        usergetAllMyResult();
    }
}

var usertableHeadHTML = "<tr>" +
    "<th>Subject </th>" +
    "<th>Topic </th>" +
    "<th>Result(%) </th>" +
    "<th>date </th>" +
    "</tr>";
var usersubjectResult = [];
var usertopicResult = [];
var userresultTest = [];
var userdateResult = [];

function usergetAllMyResult() {
    userclearMyTable();
    $.ajax({
        url: '/userResult/' + document.getElementById('selektUser').value,
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            $("#usertableMyResult").append(usertableHeadHTML);
            for (var i = 0; i < response.length; i++) {
                usersubjectResult[i] = response[i].subject;
                usertopicResult[i] = response[i].topic;
                userresultTest[i] = response[i].resultTest;
                userdateResult[i] = response[i].date;
                usermakeTable(usersubjectResult[i], usertopicResult[i], userresultTest[i], userdateResult[i]);
            }

        }
    })
}
function userclearMyTable() {
    $("#usertableMyResult").empty();

}


function usermakeTable(a1, a2, a3, a4) {
    var html = "<tr>" +
        "<td>" + a1 + "</td>" +
        "<td>" + a2 + "</td>" +
        "<td>" + a3 + "</td>" +
        "<td>" + a4 + "</td>" +
        "</tr>";
    $("#usertableMyResult").append(html);

}

function usergetSubjectFiltr() {
    $.ajax({
        url: '/getSubjects',
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            var s2 = document.querySelector("#usersubjectFilter").options;
            for (var i = 0; i < s2.length; i++) {
                s2[i] = null;
            }
            s2[0] = null;
            for (var j = 0; j < response.length; j++) {
                s2[j + 1] = new Option(response[j].subjectName, response[j].subjectName, true);
            }
            $("#usersubjectFilterSelektor").show(300);
        }
    })

}
var usershow1 = false;
function usershowsubjectSelect() {
    userclearMyTable();
    if (usershow1) {
        usershow4 = false;
        document.getElementById('userdateFilter').value = ''
        $("#dateFilterSelektor").hide(300);
        usershow1 = false;
        usershow2 = false;
        var s2 = document.querySelector("#usersubjectFilter").options;
        for (var i = 0; i < s2.length; i++) {
            s2[i] = null;
        }
        document.getElementById('usersubjectFilter').value = '';
        $("#usersubjectFilterSelektor").hide(300);
        $("#usertopicFilterSelektor").hide(300);
    }
    else {
        usershow4 = false;
        $("#userdateFilterSelektor").hide(300);
        document.getElementById('userdateFilter').value = '';
        usercleanFiltrError();
        usershow1 = true;
        usergetSubjectFiltr();
    }
}

$("#usersubjectFilter").on('change', function () {
    usershow4 = false;
    $("#userdateFilterSelektor").hide(300);
    document.getElementById('userdateFilter').value = '';
    userclearMyTable();
    var s = document.querySelector("#usertopicFilter").options;
    for (var i = 0; i <= s.length; i++) {
        s[i] = null;
    }
    $("#usertableMyResult").append(tableHeadHTML);
    $.ajax({
        url: '/userResult/' + document.getElementById('selektUser').value,
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            for (var i = 0; i < response.length; i++) {
                usersubjectResult[i] = response[i].subject;
                usertopicResult[i] = response[i].topic;
                userresultTest[i] = response[i].resultTest;
                userdateResult[i] = response[i].date;
                if (usersubjectResult[i] == document.getElementById('usersubjectFilter').value) {
                    usermakeTable(usersubjectResult[i], usertopicResult[i], userresultTest[i], userdateResult[i]);
                }
            }

            $.ajax({
                url: '/getTapeBy/' + document.getElementById('usersubjectFilter').value,
                method: 'GET',
                contentType: 'application/json',
                success: function (response) {
                    for (var i = 0; i <= s.length; i++) {
                        s[i] = null;
                    }
                    s[0] = null;
                    for (var j = 0; j < response.length; j++) {
                        s[j + 1] = new Option(response[j].topic, response[j].topic, true);
                    }
                }
            })

            $("#usermyResult").show(300);
            clearDataFilter();

        }
    })

})


var usershow2 = false;
function usershowTopicSelect() {
    if (usershow2) {
        usershow4 = false;
        document.getElementById('userdateFilter').value = ''
        $("#userdateFilterSelektor").hide(300);
        $("#usertopicFilterSelektor").hide(300);
        usershow2 = false;
        var s2 = document.querySelector("#usertopicFilter").options;
        for (var i = 0; i < s2.length; i++) {
            s2[i] = null;
        }
        document.getElementById('usertopicFilter').value = '';

    }
    else {
        usershow4 = false;
        $("#userdateFilterSelektor").hide(300);
        document.getElementById('userdateFilter').value = '';
        if (document.getElementById('usersubjectFilter').value != '') {
            usergetTopicFiltr();
            usershow2 = true;
            $("#usertopicFilterSelektor").show(300);
        } else {
            usercleanFiltrError();
            usershowFiltrError();
        }

    }
}

function usergetTopicFiltr() {
    usercleanFiltrError();
    var s = document.querySelector("#usertopicFilter").options;
    for (var i = 0; i <= s.length; i++) {
        s[i] = null;
    }
    $.ajax({
        url: '/getTapeBy/' + document.getElementById('usersubjectFilter').value,
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            for (var i = 0; i <= s.length; i++) {
                s[i] = null;
            }
            s[0] = null;
            for (var j = 0; j < response.length; j++) {
                s[j + 1] = new Option(response[j].topic, response[j].topic, true);
            }
            $("#usertopicFilterSelektor").show(300);

        }
    })
}

$("#usertopicFilter").on('change', function () {
    usershow4 = false;
    $("#userdateFilterSelektor").hide(300);
    document.getElementById('userdateFilter').value = '';
    userclearMyTable();
    $("#usertableMyResult").append(tableHeadHTML);
    $.ajax({
        url: '/userResult/' + document.getElementById('selektUser').value,
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            for (var i = 0; i < response.length; i++) {
                usersubjectResult[i] = response[i].subject;
                usertopicResult[i] = response[i].topic;
                userresultTest[i] = response[i].resultTest;
                userdateResult[i] = response[i].date;
                if (usersubjectResult[i] == document.getElementById('usersubjectFilter').value &&
                    usertopicResult[i] == document.getElementById('usertopicFilter').value) {
                    usermakeTable(usersubjectResult[i], usertopicResult[i], userresultTest[i], userdateResult[i]);
                }
            }

            $("#usermyResult").show(300);
            clearDataFilter();

        }
    })

})

var usershow4 = false;
function usershowDateSelect() {
    userclearMyTable();
    clearDataFilter();

    if (usershow4) {
        usershow4 = false;
        document.getElementById('userdateFilter').value = ''
        $("#userdateFilterSelektor").hide(300);
    }
    else {
        usercleanFiltrError();
        usershow4 = true;
        usergetDateFiltr();
        $("#userdateFilterSelektor").show(300);
    }


}

function clearDataFilter() {
    var s = document.querySelector("#userdateFilter").options;
    for (var i = 0; i < s.length; i++) {
        s[i] = null;
    }
    s[0] = null;
}

function usergetDateFiltr() {
    var s = document.querySelector("#userdateFilter").options;
    for (var i = 0; i < s.length; i++) {
        s[i] = null;
    }
    $.ajax({
        url: '/userResult/' + document.getElementById('selektUser').value,
        method: 'GET',
        contentType: 'application/json',
        success: function (response) {
            $("#usertableMyResult").append(usertableHeadHTML);
            for (var i = 0; i < s.length; i++) {
                s[i] = null;
            }
            var j = 1;
            for (var i = 0; i < response.length; i++) {
                if (i == 0) {
                    s[j] = new Option(response[i].date, response[i].date, true);
                    j++;
                } else {
                    if (response[i].date != response[i - 1].date) {
                        s[j] = new Option(response[i].date, response[i].date, true);
                        j++;
                    }
                }
                usersubjectResult[i] = response[i].subject;
                usertopicResult[i] = response[i].topic;
                userresultTest[i] = response[i].resultTest;
                userdateResult[i] = response[i].date;
            }

        }
    })

}

$("#userdateFilter").on('change', function () {
    $("#usermyResult").hide();
    userclearMyTable();
    $("#usertableMyResult").append(usertableHeadHTML);
    for (var i = 0; i < userdateResult.length; i++) {

        if (userdateResult[i] == document.getElementById('userdateFilter').value && usersubjectResult[i] == document.getElementById('usersubjectFilter').value &&
            usertopicResult[i] == document.getElementById('usertopicFilter').value) {
            usermakeTable(usersubjectResult[i], usertopicResult[i], userresultTest[i], userdateResult[i]);
        }
        if (userdateResult[i] == document.getElementById('userdateFilter').value && document.getElementById('usersubjectFilter').value == '' &&
            document.getElementById('usertopicFilter').value == '') {
            usermakeTable(usersubjectResult[i], usertopicResult[i], userresultTest[i], userdateResult[i]);
        }
        if (userdateResult[i] == document.getElementById('userdateFilter').value && usersubjectResult[i] == document.getElementById('usersubjectFilter').value &&
            document.getElementById('usertopicFilter').value == '') {
            usermakeTable(usersubjectResult[i], usertopicResult[i], userresultTest[i], userdateResult[i]);
        }
    }
    $("#usermyResult").show(300);
})

function usershowFiltrError() {

    var html = "<div class='alert alert-danger'>"
        + "<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;</button>"
        + "<strong>Oh snap!</strong> Select a Subject</a>."
        + "</div>"
    $("#userfiltrError").append(html);
}

function usercleanFiltrError() {
    $("#userfiltrError").empty();
}

// delete user

function deleteUser() {
    $.ajax({
        url: '/deleteUser/' + document.getElementById('selektUser').value,
        method: 'POST',
        contentType: 'application/json',
        success: function () {
            alert('User is deleted')
            var s1 = document.querySelector("#selektUser").options;
            for (var i = 0; i < s1.length; i++) {
                if (s1[i].value == document.getElementById('selektUser').value) {
                    s1[i] = null;
                }
            }
            $("#usershowMyResult").hide(300);
            $("#isUserActivated").hide(300);


        }
    })
}
