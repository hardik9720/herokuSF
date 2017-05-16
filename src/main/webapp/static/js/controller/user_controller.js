App.controller('personManagerController', function($scope, $http) {
    // var urlBase = "http://localhost:8084/CCPOC";
    var urlBase = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '') + '/CCPOC';
    //var urlBase = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '');
    console.log('123');
    $scope.person = {id: null, firstname: '', lastname: '', birthdate: null, amount: null, description: null};

    //get all tasks and display initially
    $scope.getAllPersons = function() {
        $http.get(urlBase + '/person/user').
                success(function(data) {
                    $scope.persons = data;
                    console.log('person data' + JSON.stringify($scope.persons));
                });
    }
    $scope.getAllPersons();

    $scope.edit = function(id) {
        console.log('User id' + id);
        $http.get(urlBase + '/person/user/' + id).
                success(function(data) {
                    $scope.person = data;
                    $scope.person.birthdate = new Date(data.birthdate);
                    console.log('update method called' + JSON.stringify(data));
                });
    }

    $scope.submit = function() {
        console.log('form data' + JSON.stringify($scope.person));
        $http.post(urlBase + '/person/user',$scope.person).
                success(function(data) {
                    $scope.persons = data;
                    console.log('person data' + JSON.stringify($scope.persons));
                    $scope.getAllPersons();
                }).error(function(data) {
            console.log('error' + JSON.stringify(data));
        });

    }
});

