// Countdown
function countdown(seconds, type) {
    var countdownTimer = setInterval(function() {
        seconds--;

        document.getElementById("countdown").innerText = seconds;

        if (seconds <= 0) {
            clearInterval(countdownTimer);

            // Check type for redirect
            if (type == '0' || type == '2') {
                // Login confirm, reset pass confirm
                redirectToHomeAdmin();
            } else if (type == '1') {
                // Forgot password confirm
                redirectToLoginUser();
            }
        }
    }, 1000);
}

function countdownFail(seconds, forwardUrl) {
    var countdownTimer = setInterval(function() {
        seconds--;

        document.getElementById("countdownFail").innerText = seconds;

        if (seconds <= 0) {
            clearInterval(countdownTimer);
            var link = document.createElement('a');
            link.href = forwardUrl;
            link.click();
        }
    }, 1000);
}

function confirmOTP(event, type) {
    const otp_inputs = document.querySelectorAll(".otp__digit_" + type);
    const mykey = "0123456789".split("");

    let current = event.target
    let index = parseInt(current.classList[1].split("__")[2])
    current.value = event.key

    if(event.keyCode == 8 && index > 1){
        current.previousElementSibling.focus()
    }
    if(index < 6 && mykey.indexOf(""+event.key+"") != -1){
        var next = current.nextElementSibling;
        next.focus()
    }
    var _finalKey = ""
    for(let {value} of otp_inputs){
        _finalKey += value
    }

    var email = null, phone = null;
    if (type == 'email,phone') {
        email = document.getElementById("email").innerText;
        phone = document.getElementById("phone").innerText;
    } else if (type == 'email') {
        email = document.getElementById("email").innerText;
    } else if (type == 'phone') {
        phone = document.getElementById("phone").innerText;
    }

    if(_finalKey.length == 6) {
        console.log("Call to confirm OTP number");
        const data = {
            "email" : email,
            "phone" : phone,
            "otp": _finalKey,
            "type": $('#type').val()
        }
        const csrfToken = getCsrfToken();
        $.ajax({
            url: $('#contextPath').val() + "/app/verification/confirm-otp-number",
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
                    document.getElementById("errorMsg").innerText = result.message;
                    if (result.code === '02') {
                        $("#redirectAfterFail").show();
                        countdownFail(10, result.forwardUrl);
                    } else {
                        $("#redirectAfterFail").hide();
                    }
                    $("#failure").modal();
                } else {
                    // Success
                    if (result.otpFlag == '0' || result.otpFlag == '3') {
                        if (result.count_otp < 2) {
                            $("#redirectAfter").hide();
                            $("#success").modal();
                        } else {
                            if (result.type == '1') {
                                $("#modal-success-title").text('Cheers! Forgot password successfully!');
                            } else if (result.type == '0') {
                                $("#modal-success-title").text('Cheers! Login successfully!');
                            } else if (result.type == '2') {
                                $("#modal-success-title").text('Cheers! Reset password successfully!');
                            }
                            $("#forwardUrl").val(result.forwardUrl);
                            $("#redirectAfter").show();
                            $("#success").modal();
                            countdown(5, result.type);
                        }
                    } else {
                        if (result.type == '1') {
                            $("#modal-success-title").text('Cheers! Forgot password successfully!');
                        } else if (result.type == '0') {
                            $("#modal-success-title").text('Cheers! Login successfully!');
                        } else if (result.type == '2') {
                            $("#modal-success-title").text('Cheers! Reset password successfully!');
                        }
                        $("#forwardUrl").val(result.forwardUrl);
                        $("#success").modal();
                        countdown(5, result.type);
                    }
                }
            },
            error: function(jqXhr, textStatus, errorMessage) {
                console.log("Error: ", errorMessage);
            },
            complete: function () {
                // document.getElementById(idMessage).value = "";
                $("#loader").fadeOut("slow");
                $("body .wrapper").fadeIn(600);
                $("body .wrapper").css('opacity', 1);
            }
        });
    }
}