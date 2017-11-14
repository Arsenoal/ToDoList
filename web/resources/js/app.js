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
        var newTask ={
            "id": 0,
            "date": new Date().getTime(),
            "description": $scope.newTask,
            "isDone": false
        };

        $scope.newTask = "";


        console.log ("ADD new task:", newTask);
        console.log ("POST request...");
        $http({
            method: "POST",
            url: urlBase + "/task",
            headers: {"Content-Type":"application/json; charset=utf-8"},
            data: newTask
        }).then(function successCallback(response) {
            console.log("OK");
            //console.log(response.data);
            $scope.tasks.push(response.data);
        }, function errorCallback(response){
            console.log("error");
            console.log(response);
        })
    };

    $scope.clearCompleted = function () {
        $scope.tasks = $scope.tasks.filter( function(item){
            if(item.isDone){
                $http({
                    method: "DELETE",
                    url: urlBase + "/task/" + item.id,
                    headers: {"Content-Type":"application/json; charset=utf-8"}
                }).then(function successCallback(response){
                    console.log("OK");
                }, function errorCallback(response) {
                    console.log("error");
                })
            }

            return !item.isDone;
        })
    };
});