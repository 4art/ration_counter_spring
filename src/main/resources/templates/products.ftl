<html>
<head>
    <title>Lebensmittel</title>
<#include "includes/meta.ftl" />
    <link rel="stylesheet" type="text/css" href="css/angucomplete.css">
</head>
<body>

<div ng-app="myApp" class="container">
    <div ng-controller="productsCtrl">
    <#include 'includes/nav-bar.ftl'/>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>id</th>
                <th>Name</th>
                <th>Eiweiß</th>
                <th>Kohlenhydrat</th>
                <th>Fett</th>
                <th>Kcal</th>
            </tr>
            </thead>
            <tbody>
            <tr ng-repeat="product in products">
                <td>{{ product.id }}</td>
                <td>{{ product.name }}</td>
                <td>{{ product.protein }}</td>
                <td>{{ product.carbo }}</td>
                <td>{{ product.fat }}</td>
                <td>{{ product.kcal }}</td>
            </tr>
            </tbody>
        </table>
        <input type="hidden" ng-model="selected" value="{{selected.originalObject.id}}">

        <angucomplete id="ex1"
                      placeholder="Search countries"
                      pause="100"
                      selectedobject="selected"
                      localdata="products"
                      searchfields="name"
                      titlefield="name"
                      minlength="1"
                      inputclass="form-control form-control-small"/>
    </div>
</div>
<#include 'includes/footer.ftl' />
</div>

<#include 'includes/js.html' />
<script type="text/javascript" src="js/angucomplete.js"></script>
<script type="text/javascript" src="js/prod.js"></script>
</body>
</html>