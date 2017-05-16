App.controller('addressManagerController', function($scope, $http) {
    // var urlBase = "http://localhost:8084/CCPOC";
    //var urlBase = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '') + '/CCPOC';
    var urlBase = location.protocol + '//' + location.hostname + (location.port ? ':' + location.port : '');
    $scope.address = {id: null, sfId: '', statename: '', addressline2: '', 
                      addressline1: '', personId: '',zipCode : '',ownerId :'',
                      autoNumberName : ''
                  };

    //get all tasks and display initially
    $scope.getAllAddress = function() {
        $http.get(urlBase + '/address/allAddress').
                success(function(data) {
                    $scope.addresses = data;
                    console.log('address data' + JSON.stringify($scope.address));
                });
    }
    $scope.getAllAddress();

    $scope.edit = function(id) {
        console.log('Address id' + id);
        $http.get(urlBase + '/address/getAddress/' + id).
                success(function(data) {
                    $scope.address = data;
                    console.log('get address data'+JSON.stringify($scope.address));
                });
               
    }
    
    $scope.submit = function() {
        console.log('form data' + JSON.stringify($scope.address));
        $http.post(urlBase + '/address/update',$scope.address).
                success(function(data) {
                    $scope.getAllAddress();
                }).error(function(data) {
            console.log('error' + JSON.stringify(data));
        });
    }
    
});

