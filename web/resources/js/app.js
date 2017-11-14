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
        $scope.tasks = response.data
    }, function errorCallback(response) {
        console.log("error");
    });

    $scope.addTask = function () {
        $scope.tasks.push({'title':$scope.newTask, 'done': false});
        $scope.newTask = '';
    };

    $scope.clearCompleted = function () {
        $scope.tasks = $scope.tasks.filter(function (item) {
            return !item.done;
        })
    };
});