/**
 * Created by metraf on 25.12.16.
 */
var app = angular.module('valid', []);

app.controller('validRegist', function ($scope, $http) {
    var error = new uniqFunc();
    $('#registLogin').on("keyup keydown change blur", function () {
        if ($.trim($(this).val()).length > 3) {
            $http.get('api/checkNewName/' + $(this).val()).then(function (response) {
                // console.log(response);
                if (response.data.uniq) {
                    error.setSuccess();
                }
                else {
                    error.setError();
                }

            });
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