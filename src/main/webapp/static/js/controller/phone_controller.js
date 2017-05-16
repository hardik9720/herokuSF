App.controller('phoneManagerController', function($scope, $http) {
    // var urlBase = "http://localhost:8084/CCPOC";
    var urlBase = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '') + '/CCPOC';
    //var urlBase = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '');
    $scope.phone = {id: null, sfId: '', statename: '', autoNumberName: '', 
                      type: '', phone: ''
                  };
    console.log('phone controller called');              
    //get all tasks and display initially
    $scope.getAllPhones = function() {
        $http.get(urlBase + '/phone/allPhones').
                success(function(data) {
                    $scope.phones = data;
                    console.log('phone data' + JSON.stringify($scope.phones));
                });
    }
    $scope.getAllPhones();

    $scope.edit = function(id) {
        console.log('Phone id' + id);
        $http.get(urlBase + '/phone/getPhone/' + id).
                success(function(data) {
                    $scope.phone = data;
                    console.log('phone data' + JSON.stringify(data));
                });
    }

    $scope.submit = function() {
        console.log('phone form data' + JSON.stringify($scope.phone));
        $http.post(urlBase + '/phone/updatePhone',$scope.phone).
                success(function(data) {
                    
                    $scope.getAllPhones();
                }).error(function(data) {
            console.log('error' + JSON.stringify(data));
        });

    }
});

