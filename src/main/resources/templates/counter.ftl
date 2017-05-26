<!DOCTYPE html>
<html lang="de">
<head>
    <title>Metra</title>
<#include "includes/meta.ftl" />
    <link rel="stylesheet" type="text/css" href="css/angucomplete.css">
</head>
<body>
<div class="container" ng-app="myApp" ng-controller="productsCtrl">
<#include 'includes/nav-bar.ftl'/>
    <h2>Nährwertzähler</h2>
    <p>Fang zu schreiben am ersten Feld an.</p>
    <div class="table-responsive">

        <table class="table table-hover">
            <thead>
            <tr>
                <th class="col_1"><p>№ ▼</p></th>
                <th class="col_2"><p>Name<span class="resp_hid"> ▼</span></p></th>
                <th class="col_3"><p><span class="resp_hid">Gewicht (Gramm)▼ </span><span
                        class="resp_vis">Gewicht (g)</span></p></th>
                <th class="col_4"><p>Eiweiße<span class="resp_hid"> ▼ </span></p></th>
                <th class="col_5"><p>Fette<span class="resp_hid"> ▼ </span></p></th>
                <th class="col_6"><p><span class="resp_hid">Kohlenhydrate ▼ </span><span class="resp_vis">Kohlen-<br>hydraten</span>
                </p></th>
                <th class="col_7"><p><span class="resp_hid">Kilokalorien (</span>kcal<span class="resp_hid">)▼ </span>
                </p></th>
            </tr>
            </thead>
            <form method="post" name="prodForm" id="form">
                <tbody>

                <tr id="tCop" ng-repeat="x in ration">
                    <td class="col_1" ng-bind="x.counter + '.'"></td>
                    <td class="col_2"><span name="name" ng-bind="x.name"></span></td>
                    <td class="col_3"><span name="weight" ng-bind="x.weight"></span></td>
                    <td class="col_4" name="protein" ng-bind="x.protein"></td>
                    <td class="col_5" name="fat" ng-bind="x.fat"></td>
                    <td class="col_6" name="carbonates" ng-bind="x.carbo"></td>
                    <td class="col_7" name="kcal" ng-bind="x.kcal"></td>
                    <td class="removeItem" ng-click="removeItem($index)">x</td>
                </tr>

                <tr id="tCop">
                    <td class="col_1" id="number1"></td>
                    <td class="col_2">
                        <angucomplete id="name"
                                      placeholder="Lebensmittel"
                                      pause="100"
                                      selectedobject="selected"
                                      localdata="products"
                                      searchfields="name"
                                      titlefield="name"
                                      minlength="1"
                                      inputclass="form-control form-control-small"
                                      field-required="true"
                                      input-name="autoAddress"/>
                    </td>
                    <td class="col_3"><input class="form-control" ng-model="weight" type="number" id="weight"/></td>
                    <td class="col_4" id="rationProtein"
                        ng-bind="(weight * selected.originalObject.protein).toFixed(2)"></td>
                    <td class="col_6" id="rationFat" ng-bind="(weight * selected.originalObject.fat).toFixed(2)"></td>
                    <td class="col_5" id="rationCarbo"
                        ng-bind="(weight * selected.originalObject.carbo).toFixed(2)"></td>
                    <td class="col_7" id="rationKcal" ng-bind="(weight * selected.originalObject.kcal).toFixed(2)"></td>
                    <input type="hidden" id="selectedId" value="{{selected.originalObject.id}}">
                    <input type="hidden" id="selectedProtein" value="{{selected.originalObject.protein}}">
                    <input type="hidden" id="selectedCarbo" value="{{selected.originalObject.carbo}}">
                    <input type="hidden" id="selectedFat" value="{{selected.originalObject.fat}}">
                    <input type="hidden" id="selectedKcal" value="{{selected.originalObject.kcal}}">
                    <input type="hidden" id="selectedName" value="{{selected.originalObject.name}}">

                </tr>
                </tbody>
                <tfoot>

                <tr>
                    <td class="col_1"></td>
                    <td class="col_2"></td>
                    <td class="col_3" id="weiGen"></td>
                    <td class="col_4" id="protGen"></td>
                    <td class="col_5" id="fatGen"></td>
                    <td class="col_6" id="carboGen"></td>
                    <td class="col_7" id="kcalGen"></td>
                </tr>
                </tfoot>
        </table>
    </div>
    <button type="button" class="btn btn-primary" ng-click="addRation()" id="addColumn">Hinzufügen</button>
    <br>
    <div ng-messages="form.autoAddress.$error" ng-if="form.autoAddress.$touched">
        <div class="error-message" ng-message="required">Please enter a valid address</div>
        <div class="error-message" ng-message="autocomplete-required">Please enter a valid address from dropdown</div>
    </div>
    <p id="errorText" class="errorHid">*Fühllen Sie richtig die passende Felder aus.</p>
    </form>
    <br/><br/>
    <div class="hidden" id="sessionSuccess">
        <button class="btn btn-danger" id="saveRation">Der Ration ins Profil Speichern</button>
        <a style="margin-left:15px;" href="/myrat">Mein Ration.</a>

        <br><br><br><br><br><br><br><br><br>
    </div>
    <div id="sessionError" class="hidden">
        <p class="errorVis">Loggen Sie sich ein, damit den Ration ins Profil speichern. <a href="/signin">Einloggen.</a>
        </p>
    </div>
    <span><h4>Fehlt etwas?? Sie können neue Datei<a id="addNeu"> hinzufügen.</a></h4></span>
    <br><br><br><br><br><br><br><br><br><br>

    <div id="modal" class="modal fade" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Neue Datei<a id="modalAdd" data-dismiss="modal"> hinzufügen.</a></h4>
                </div>
                <div class="modal-body">
                    <form name="newProducts" ng-submit="saveNewProducts()">
                        <tr>

                            <td>
                                <div class="form-group"
                                     ng-class="{ 'has-error' : newProducts.newProdName.$dirty && newProducts.newProdName.$invalid}">

                                    <input type="text" name="newProdName" ng-model="newProd.name" class="form-control"
                                           placeholder="Name vom Lebensmittel" ng-minlength="3" ng-maxlength="20"
                                           required>
                                </div>
                            </td>
                            <td>
                                <div class="form-group"
                                     ng-class="{ 'has-error' : newProducts.newProtein.$dirty && newProducts.newProtein.$invalid}">

                                        <input type="number" step="any" name="newProtein" ng-model="newProd.protein"
                                           placeholder="Eiweis pro 100 gr." class="form-control" min="0" required>
                                </div>
                            </td>
                            <td>
                                <div class="form-group"
                                     ng-class="{ 'has-error' : newProducts.newFat.$dirty && newProducts.newFat.$invalid}">

                                    <input type="number" step="any" class="form-control" ng-model="newProd.fat" name="newFat"
                                           placeholder="Fette pro 100 gr." min="0"
                                           required>
                                </div>
                            </td>
                            <td>
                                <div class="form-group"
                                     ng-class="{ 'has-error' : newProducts.newCarbo.$dirty && newProducts.newCarbo.$invalid}">

                                    <input type="number" step="any" name="newCarbo" ng-model="newProd.carbo"
                                           placeholder="Kohlenhydraten pro 100 gr." class="form-control" min="0"
                                           required/>
                                </div>
                            </td>
                            <td>
                                <div class="form-group"
                                     ng-class="{ 'has-error' : newProducts.newKcal.$dirty && newProducts.newKcal.$invalid}">
                                    <input type="number" step="any" name="newKcal" class="form-control" ng-model="newProd.kcal"
                                           placeholder="Kcal pro 100 gr." min="0"
                                           required>
                                </div>
                            </td>
                        </tr>
                        <button type="submit" class="btn btn-primary" ng-disabled="newProducts.$invalid">Neues Lebensmittel hinzufügen</button>
                    </form>
                    <br>
                    <span ng-bind="errorSum" class="errorVis"></span>
                    <span class="errorVis"
                          ng-show="newProducts.newProdName.$dirty && newProducts.newProdName.$invalid">

                    <span ng-show="newProducts.newProdName.$error.required">Das ist ein Pflichtfeld.</span>
                    <span ng-show="newProducts.newProdName.$error.minlength">Die Name ist zu kurz.</span>
                    <span ng-show="newProducts.newProdName.$error.maxlength">Die Name ist zu lang.</span>
                    <br>
                    </span>
                    <span class="errorVis"
                          ng-show="newProducts.newProtein.$dirty && newProducts.newProtein.$invalid">

                    <span ng-show="newProducts.newProtein.$error.required">Das ist ein Pflichtfeld.</span>
                    <span ng-show="newProducts.newProtein.$error.min">Das Feld ist zu klein.</span>
                    <br>
                    </span>
                    <span class="errorVis"
                          ng-show="newProducts.newCarbo.$dirty && newProducts.newCarbo.$invalid">

                    <span ng-show="newProducts.newCarbo.$error.required">Das ist ein Pflichtfeld.</span>
                    <span ng-show="newProducts.newCarbo.$error.min">Das Feld ist zu klein.</span>
                    <br>
                    </span>
                    <span class="errorVis"
                          ng-show="newProducts.newFat.$dirty && newProducts.newFat.$invalid">

                    <span ng-show="newProducts.newFat.$error.required">Das ist ein Pflichtfeld.</span>
                    <span ng-show="newProducts.newFat.$error.min">Das Feld ist zu klein.</span>
                    <br>
                    </span>
                    <span class="errorVis"
                          ng-show="newProducts.newKcal.$dirty && newProducts.newKcal.$invalid">

                    <span ng-show="newProducts.newKcal.$error.required">Das ist ein Pflichtfeld.</span>
                    <span ng-show="newProducts.newKcal.$error.min">Das Feld ist zu klein.</span>
                    </span>
                    <p id="errorModNum" class="errorHid">*Schreiben Sie bitte die Name ohne Ziffern.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
            </div>

        </div>
    </div>
</div>
<#include 'includes/footer.ftl' />
<#include 'includes/js.html' />
<script type="text/javascript" src="js/angucomplete.js"></script>
<script type="text/javascript" src="js/prod.js"></script>
</body>
<script>
    jQuery('#addNeu').click(function () {
        jQuery('#modal').modal();
        return false;
    });
</script>
</html>