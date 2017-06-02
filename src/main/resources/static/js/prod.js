var productsURL = "api/products";
var app = angular.module('myApp', ["angucomplete"]);

app.controller('productsCtrl', function ($scope, $http) {
    $http.get(productsURL)
        .then(function (response) {
            $scope.weight = 0;
            $scope.products = response.data;
            $scope.ration = [];
            $('#rationCarbo').text('0.00');
            $('#rationProtein').text('0.00');
            $('#rationFat').text('0.00');
            $('#rationKcal').text('0.00');
            $('#weight').on("blur keyup keydown change", function () {
                if($(this).val() < 0){
                    $(this).val(0);
                }
            });
            $scope.addRation = function () {
                $scope.ration.push({
                    weight: $scope.weight,
                    protein: (parseFloat($('#selectedProtein').val()) * $scope.weight).toFixed(2),
                    fat: (parseFloat($('#selectedFat').val()) * $scope.weight).toFixed(2),
                    carbo: (parseFloat($('#selectedCarbo').val()) * $scope.weight).toFixed(2),
                    kcal: (parseFloat($('#selectedKcal').val()) * $scope.weight).toFixed(2),
                    name: firstToUpperCase($('#selectedName').val()),
                    counter: $scope.ration.length + 1
                });
                $('#name_value').val('');
                $scope.weight = 0;
                // console.log($scope.ration);
            };
            $scope.removeItem = function (x) {
                $scope.ration.splice(x, 1);
                for (var i = 0; i < $scope.ration.length; i++) {
                    $scope.ration[i].counter = i + 1;
                }
            };
            $scope.saveNewProducts = function () {
                if ($scope.newProducts.$valid) {
                    // console.log($scope.newProducts);
                    var data = {
                        protein: $scope.newProd.protein / 100,
                        fat: $scope.newProd.fat / 100,
                        carbo: $scope.newProd.protein / 100,
                        kcal: $scope.newProd.kcal / 100,
                        name: $scope.newProd.name.toLowerCase()
                    };
                    if (data.protein + data.fat + data.carbo < 1) {

                        // console.log(data);
                        $scope.newProd.protein = "";
                        $scope.newProd.fat = "";
                        $scope.newProd.carbo = "";
                        $scope.newProd.name = "";
                        $scope.newProd.kcal = "";

                        $http.post(productsURL, data)
                            .then(function (response) {
                                $('#modal').modal('toggle');
                                $scope.errorSum = "";
                                $http.get(productsURL).then(function (response) {
                                    $scope.products = response.data;
                                });
                            });
                    }
                    else {
                        $scope.newProducts.newProtein.$invalid = true;
                        $scope.newProducts.newCarbo.$invalid = true;
                        $scope.newProducts.newFat.$invalid = true;
                        $scope.errorSum = " *Die Summe von der drei Felder muss nicht größer als 100g sein.";
                    }

                }
            }
        });
});

function firstToUpperCase( str ) {
    return str.substr(0, 1).toUpperCase() + str.substr(1);
}

