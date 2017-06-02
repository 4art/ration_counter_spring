/**
 * Created by metraf on 25.12.16.
 */
var app = angular.module('valid', []);

app.controller('validRegist', function ($scope) {
    var checkName = new getAjax();
    $('#registLogin').keyup(function () {

        checkName.getArrayOptions.url = 'api/checkNewName/' + $(this).val();
        checkName.getArrayOptions.async = false;
        console.log($scope.registForm.registLogin.$invalid); 
        var arr = checkName.getArray();
        var error = new uniqFunc();
        if (arr) {
            // console.log('not uniq');
            // $scope.registForm.registLogin.$invalid = true;
            // $scope.registForm.registLogin.$error.notUniq = true;
            error.setError();
        }
        else {
            if ($.trim($(this).val()).length > 3) {
                // console.log('bla');
                // $scope.registForm.registLogin.$invalid = false;
                // $scope.registForm.registLogin.$error.notUniq = false;
                error.setSuccess();
            }
        }
    });
    $scope.saveNewUser = function () {
        // console.log('test');
        if ($scope.registForm.$valid) {
            var data = {
                name: $scope.regist.login.toLowerCase(),
                email: $scope.regist.email,
                password: $scope.regist.password
            };
            console.log(JSON.stringify(data));
        }
    };
});

app.directive('pwCheck', [function () {
    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            var firstPassword = '#' + attrs.pwCheck;
            elem.add(firstPassword).on('keyup', function () {
                scope.$apply(function () {
                    var v = elem.val() === $(firstPassword).val();
                    ctrl.$setValidity('pwmatch', v);
                });
            });
        }
    }
}]);
var uniqFunc = function () {
  this.elements = {
      name: $('#registLogin'),
      button: $('#reg_but'),
      errorMes: $('#errorName')
  };
  this.setError = function () {
      this.elements.name.parent().addClass('has-error');
      this.elements.errorMes.removeClass('hidden');
      // this.elements.button.disable(true);
      this.elements.button.attr('disabled', true);
  };
  this.setSuccess = function () {
      this.elements.name.parent().removeClass('has-error');
      this.elements.errorMes.addClass('hidden');
      // this.elements.button.attr('disabled', false);
  };
};

var getAjax = function () {
    this.getArrayOptions = {
        url: "/api",
        contentType: "application/json",
        async: true
    };
    this.getArray = function () {
        var arr;
        $.ajax({
            async: this.getArrayOptions.async,
            url: this.getArrayOptions.url,
            type: 'GET',
            dataType: 'html',
            contentType: this.getArrayOptions.contentType,
            success: function (data) {
                if (isJson(data)) {
                    arr = JSON.parse(data);
                }
            }
        });
        return arr;
    }
};
function isJson(str) {
    try {
        JSON.parse(str);
    } catch (e) {
        return false;
    }
    return true;
}