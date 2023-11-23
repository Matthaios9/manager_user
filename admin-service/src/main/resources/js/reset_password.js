function validateResetPassword() {
    const resetPassForm = document.forms["resetPassForm"];
    const newPassword = resetPassForm['newPassword'].value;
    const confirmNewPassword = resetPassForm['confirmNewPassword'].value;
    if (newPassword !== confirmNewPassword) {
        document.getElementById("confirmNewPasswordMessage").style.display = "inline";
        return false;
    }
    return true;
}