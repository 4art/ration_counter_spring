<!DOCTYPE html>
<html lang="de">
<head>
<#include "includes/meta.ftl" />

    <link rel="stylesheet" type="text/css" href="css/signin.css">
</head>

<body ng-app="valid" ng-controller="validRegist">

<div class="container">
<#include 'includes/nav-bar.ftl'/>

    <form class="form-signin" name="registForm" ng-submit="saveNewUser()">
        <!-- <h2 class="form-signin-heading">Bitte Loggen Sie sich ein.</h2>-->
        <div class="form-group"
             ng-class="{ 'has-error' : registForm.registLogin.$dirty && registForm.registLogin.$invalid}">
            <label for="inputName" class="sr-only">Login</label>
            <input type="text" id="registLogin" name="registLogin" ng-model="regist.login" class="form-control"
                   placeholder="Login" ng-minlength="3" ng-maxlength="20" required autofocus>
            <span class="errorVis hidden" id="errorName">Die Name ist schon besetzt.</span>

            <span class="errorVis"
                  ng-show="registForm.registLogin.$dirty && registForm.registLogin.$invalid">

                    <span class="errorVis" ng-show="registForm.registLogin.$error.required">Das ist ein Pflichtfeld.</span>
                    <span class="errorVis" ng-show="registForm.registLogin.$error.minlength">Das Login ist zu kurz.</span>
                    <span class="errorVis" ng-show="registForm.registLogin.$error.maxlength">Das Login ist zu lang.</span>
                    <span class="errorVis" ng-show="registForm.registLogin.$error.notUniq">Die Name ist schon besetzt.</span>
            </span>
        </div>
        <div class="form-group"
             ng-class="{ 'has-error' : registForm.registEmail.$dirty && registForm.registEmail.$invalid}">
            <label for="inputEmail" class="sr-only">Email</label>
            <input type="email" id="inputEmail_Reg" ng-model="regist.email" name="registEmail" class="form-control"
                   placeholder="Email" required autofocus>
            <span class="errorVis"
                  ng-show="registForm.registEmail.$dirty && registForm.registEmail.$invalid">
                    <span class="errorVis" ng-show="registForm.registEmail.$error.required">Das ist ein Pflichtfeld.</span>
                    <span class="errorVis" ng-show="registForm.registEmail.$invalid">Das Email ist invalid.</span>
            </span>
        </div>
        <div class="form-group"
             ng-class="{ 'has-error' : registForm.registPassword.$dirty && registForm.registPassword.$invalid}">
            <label for="inputPassword" class="sr-only" ng-minlength="3" ng-maxlength="50">Kennword</label>
            <input type="password" id="registPassword" class="form-control" ng-model="regist.password"
                   name="registPassword" placeholder="Kennword" required>
            <span class="errorVis"
                  ng-show="registForm.registPassword.$dirty && registForm.registPassword.$invalid">

                    <span class="errorVis" ng-show="registForm.registPassword.$error.required">Das ist ein Pflichtfeld.</span>
                    <span class="errorVis" ng-show="registForm.registPassword.$error.minlength">Das Kennwort ist zu kurz.</span>
                    <span class="errorVis" ng-show="registForm.registPassword.$error.maxlength">Das Kennwort ist zu lang.</span>
            </span>
        </div>
        <div class="form-group"
             ng-class="{ 'has-error' : registForm.registPasswordAgain.$dirty && registForm.registPasswordAgain.$invalid}">
            <label for="inputPassword" class="sr-only">Kennword wiederholen</label>
            <input type="password" id="inputPassword_Reg_re" class="form-control" name="registPasswordAgain"
                   ng-model="regist.passwordAgain" ng-minlength="3" ng-maxlength="50" class="form-control"
                   placeholder="Kennword wieder" pw-check="registPassword" required>
            <span class="errorVis"
                  ng-show="registForm.registPasswordAgain.$dirty && registForm.registPasswordAgain.$invalid">

                    <span class="errorVis" ng-show="registForm.registPasswordAgain.$error.required">Das ist ein Pflichtfeld.</span>
                    <span class="errorVis" ng-show="registForm.registPasswordAgain.$error.minlength">Das Kennwort ist zu kurz.</span>
                    <span class="errorVis" ng-show="registForm.registPasswordAgain.$error.maxlength">Das Kennwort ist zu lang.</span>
                    <span class="errorVis" ng-show='registForm.registPasswordAgain.$error.pwmatch'>Die Kennwörter sind nicht gleich.</span>
            </span>
        </div>
        <p class="errorHid" id="error_regist">* Füllen Sie alle Felder richtig aus.</p>
        <input type="submit" id="reg_but" class="btn btn-lg btn-block btn-primary" ng-disabled="registForm.$invalid"
               value="Registrieren">
    </form>
    <div class="empty"></div>
<#include 'includes/js.html' />
    <script type="text/javascript" src="js/usersValid.js"></script>
<#include 'includes/footer.ftl' />
</div> <!-- /container -->

</body>
</html>
