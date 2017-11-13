var appName = angular.module('ToDoList', []);
appName.controller('toDoController', ['$scope', function($scope) {
        $scope.tasks = [
            {'title':'Build a todo app', 'done':false}
        ];

        $scope.addTask = function () {
            $scope.tasks.push({'title':$scope.newTask, 'done': false});
            $scope.newTask = '';
        };
        $scope.clearCompleted = function () {
            $scope.tasks = $scope.tasks.filter(function (item) {
                return !item.done;
            })
        };
    }]);