function countdown(seconds) {
    var countdownTimer = setInterval(function() {
        seconds--;

        document.getElementById("countdown").innerText = seconds;

        if (seconds <= 0) {
            clearInterval(countdownTimer);
            location.reload();
        }
    }, 1000);
}

function viewEditForm(event) {
    const userId = event.currentTarget.dataset.userid;
    var link = document.createElement('a');
    link.href = $('#contextPath').val() + '/app/edit?userId=' + userId;
    link.click();
}

function regenerateClientId(event) {
    const res = confirm("Do you want to regenerate the clientId for this user?");
    if (res) {
        const userid = event.currentTarget.dataset.userid;
        const data = {
            'userId': userid
        }
        const csrfToken = getCsrfToken();
        $.ajax({
            url: $('#contextPath').val() + "/app/regenarateClientId",
            contentType: "application/json",
            dataType: "json",
            type: "POST",
            data: JSON.stringify(data),
            headers: {'X-XSRF-TOKEN': csrfToken},
            beforeSend: function () {
                $("body .wrapper").css('opacity', 0.3);
                $("#loader").show();
            },
            success: function (result) {
                console.log(JSON.stringify(result));
                if (result.code !== '00') {
                    $("#modal-fail-title").text("Sorry! Happend error!");
                    $("#errorMsg").text(result.message);
                    $("#failure").modal();
                } else {
                    $("#modal-success-title").text("Cheers! Regenerate successfully!");
                    $("#redirectAfter").hide();
                    $("#success").modal();
                    countdown(2);
                }
            },
            error: function(jqXhr, textStatus, errorMessage) {
                console.log("Error: ", errorMessage);
            },
            complete: function () {
                $("#loader").fadeOut("slow");
                $("body .wrapper").fadeIn(600);
                $("body .wrapper").css('opacity', 1);
            }
        });
    }
}

function activeDeactive(event) {
    const userid = event.currentTarget.dataset.userid;
    const res = confirm("Do you want to update status for this user?");
    if (res) {
        const csrfToken = getCsrfToken();
        $.ajax({
            url: $('#contextPath').val() + "/app/activeDeactiveUser",
            contentType: "application/json",
            dataType: "json",
            type: "POST",
            data: JSON.stringify({
                'userId': userid
            }),
            headers: {'X-XSRF-TOKEN': csrfToken},
            beforeSend: function () {
                $("body .wrapper").css('opacity', 0.3);
                $("#loader").show();
            },
            success: function (result) {
                console.log(JSON.stringify(result));
                if (result.code !== '00') {
                    $("#modal-fail-title").text("Sorry! Happend error!");
                    $("#errorMsg").text(result.message);
                    $("#failure").modal();
                } else {
                    $("#modal-success-title").text("Cheers! Update status successfully!");
                    $("#redirectAfter").hide();
                    $("#success").modal();
                    countdown(2);
                }
            },
            error: function(jqXhr, textStatus, errorMessage) {
                console.log("Error: ", errorMessage);
            },
            complete: function () {
                $("#loader").fadeOut("slow");
                $("body .wrapper").fadeIn(600);
                $("body .wrapper").css('opacity', 1);
            }
        });
    }
}

function searchUserCompany() {
    // const frmSearch = document.forms["frmSearch"];
    // const mail = frmSearch['email'].value;
    // const phone = frmSearch['phone'].value;
    // let isValid = true;
    // if (mail) {
    //     if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail)) {
    //         document.getElementById("emailErrorMessage").style.display = "inline";
    //         isValid = false;
    //     }
    // }
    // if (phone) {
    //     if (!/^\d+$/.test(phone)) {
    //         document.getElementById("phoneErrorMessage").style.display = "inline";
    //         isValid = false;
    //     }
    // }
    // if (!isValid) {
    //     return false;
    // }

    const data = $("#frmSearch").serializeObject();
    var link = document.createElement('a');
    link.href = $('#contextPath').val() + '/app/dashboard?pageNumber=1&email=' + data['email'] + '&phone=' + data['phone'] + '&company=' + data['company'];
    link.click();
}