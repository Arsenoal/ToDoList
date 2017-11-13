<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="ToDoList">
  <head>
    <title>To do list</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
    <script src="resources/js/app.js"></script>
    <link rel="stylesheet" href="./resources/css/style.css">
  </head>
  <body>

  <div ng-controller="toDoController">
    <form name="frm" ng-submit="addTask()">
      <input type="text" name="newTask" ng-model="newTask" required />
      <button ng-disabled="frm.$invalid">add</button>
    </form>
    <button ng-click="clearCompleted()">clear completed</button>

    <ul>
      <li ng-repeat="task in tasks">
        <input type="checkbox" ng-model="task.done"/>
        <span ng-class="{'done':task.done}">{{task.title}}</span>

      </li>
    </ul>
  </div>

  </body>
</html>
