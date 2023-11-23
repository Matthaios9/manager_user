
function validateRegisterForm() {
    const registerForm = document.forms["registerForm"];
    const mail = registerForm['email'].value;
    const phone = registerForm['phone'].value;
    let isValid = true;
    if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail)) {
        document.getElementById("emailErrorMessage").style.display = "inline";
        isValid = false;
    }
    if (!/^\d+$/.test(phone)) {
        document.getElementById("phoneErrorMessage").style.display = "inline";
        isValid = false;
    }
    // $("#_crsf").val = getCsrfToken();
    return isValid;
}

function changeCompanyType() {
    const companyTypeId = document.getElementById("companyType").value;
    if (!companyTypeId) {
        return;
    }
    if ($('#companyErr').length) {
        $('#companyErr').hide();
    }
    const data = {
        'companyTypeId': companyTypeId
    }
    const csrfToken = getCsrfToken();
    $.ajax({
        url: $('#contextPath').val() + "/app/changeCompanyType",
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
            if (result.code === '00') {
                const divTag = document.getElementById("divCompany");

                if (!$('#companyOther').length) {
                    const oldSelectTag = document.getElementById("company");
                    const oldOptionTag = oldSelectTag.getElementsByTagName("option");
                    oldSelectTag.remove();
                    removeHtmlElement(oldOptionTag);
                }

                if (result.isOther === 0) {
                    const inputTags = divTag.getElementsByTagName("input");
                    removeHtmlElement(inputTags);

                    const newSelectTag = document.createElement("select");
                    newSelectTag.classList.add("form-control");
                    newSelectTag.classList.add("select");
                    newSelectTag.setAttribute("id", "company");
                    newSelectTag.setAttribute("name", "company");

                    var html = "<option value=''>Select company</option>";
                    result.companyList.forEach(company => {
                        html += "<option value='" + company.id + "'>" + company.name + "</option>";
                    })

                    newSelectTag.innerHTML = html;
                    divTag.appendChild(newSelectTag);

                } else {
                    const html = "<input type=\"text\" name=\"companyOther\" id=\"companyOther\" class=\"form-control\" placeholder=\"Enter company\"/>"
                    divTag.innerHTML = html;
                }
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