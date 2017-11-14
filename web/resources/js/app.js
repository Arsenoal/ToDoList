var appName = angular.module('ToDoList', []);
appName.controller('toDoController', function($scope, $http) {
    var urlBase = "http://localhost:8080";
    $scope.tasks = [];
    $http.defaults.headers.post["Content-Type"] = "application/json; charset=utf-8";

    console.log ("Get all task:");
    console.log ("Send GET request...");
    $http({
        method: "GET",
        url: urlBase + "/tasks",
        headers: {"Content-Type":"application/json; charset=utf-8"}
    }).then(function successCallback(response) {
        console.log("OK");
        console.log(response);
        $scope.tasks = response.data
    }, function errorCallback(response) {
        console.log("error");
    });

    $scope.addTask = function () {

    };

    $scope.clearCompleted = function () {

    };
});