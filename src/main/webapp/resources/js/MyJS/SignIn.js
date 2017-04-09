/**
 * Created by Yerchik on 09.04.2017.
 */
$(document).ready(function () {

    if (window.location.search == "?error"){
        $("#errorMessage").show(300);
    }
    if (window.location.search != "?error"){
        $("#errorMessage").hie();
    }
})