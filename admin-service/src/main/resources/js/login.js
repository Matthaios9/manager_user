function validateLoginForm() {
    const loginForm = document.forms["loginForm"];
    const mail = loginForm['emailOrPhone'].value;
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail)) {
        return true;
    } else if (/^\d+$/.test(phone)) {
        return true;
    }
    document.getElementById("emailOrPhoneErrorMessage").style.display = "inline";
    return false;
}