
$(document).ready(function () {
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });

    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
});

// Wait for window load
$(window).on("load", function() {
    console.log("window loading");
    // Animate loader off screen
    $("#loader").fadeOut("slow");
    $("body .wrapper").fadeIn(600);
});

const removeHtmlElement = (arr) => {
    for (var i = arr.length - 1; i >= 0; --i) {
        arr[i].remove();
    }
}

function redirectToHomeAdmin() {
    var link = document.createElement('a');
    const forwardUrl = document.getElementById("forwardUrl").value;
    link.href = forwardUrl;
    link.click();
}

function redirectToLoginUser() {
    var link = document.createElement('a');
    const forwardUrl = document.getElementById("forwardUrl").value;
    link.href = forwardUrl;
    link.click();
}

function getCsrfToken() {
    const csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');
    return csrfToken;
}

function logoutSubmit() {
    const form = document.getElementById("frmLogout");
    form.submit();
}

function changeActiveClass(e) {
    //Remove another active
    const liElements = $('#sidebar > ul > li > a');
    for (var i = 0; i < liElements.length; i++) {
        liElements[i].classList.remove("active");
    }
    e.target.classList.add("active");
}