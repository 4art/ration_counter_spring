/**
 * Created by metraf on 25.12.16.
 */
var app = angular.module('valid', []);

app.controller('validRegist', function ($scope, $http) {
    var error = new uniqFunc();
    $('#registLogin').on("keyup keydown change blur", function () {
        if ($.trim($(this).val()).length != 0) {
            var element = $(this);
            $http.get('api/checkNewName/' + $(this).val()).then(function (response) {
                // console.log(response);
                if (response.data.uniq) {
                    error.setSuccess(element);
                }
                else {
                    error.setError(element);
                }

            });
        }
    });

});

app.directive('pwCheck', [function () {
    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            var firstPassword = '#' + attrs.pwCheck;
            elem.add(firstPassword).on('keyup keydown blur change', function () {
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
    this.setError = function (element) {
        if(checkLength(element)){
            this.elements.name.parent().addClass('has-error');
            this.elements.errorMes.removeClass('hidden');
        }
        // this.elements.button.disable(true);
        this.elements.button.attr('disabled', true);
    };
    this.setSuccess = function (element) {
        if(checkLength(element)){
            this.elements.name.parent().removeClass('has-error');
            this.elements.errorMes.addClass('hidden');
        }
        // this.elements.button.attr('disabled', false);
    };
    function checkLength(element) {
        var bool;
        $.trim(element.val()).length >= 3 ? bool = true : bool = false;
        return bool;
    }
};