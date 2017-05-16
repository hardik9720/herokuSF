'use strict';

var App = angular.module("myApp", ["ui.router"]);
App.config(function($stateProvider, $urlRouterProvider, $httpProvider) {
    $httpProvider.interceptors.push('AuthenticationInterceptor');

    $stateProvider
            // PERSON STATES AND NESTED VIEWS ========================================
    .state('person', 
    {
        url: '/person',
        templateUrl: 'person',
        controller: 'personManagerController'
    }).state('address', 
    {
        url: '/address',
        templateUrl: 'address',
        controller: 'addressManagerController'
    }).state('phone', 
    {
        url: '/phone',
        templateUrl: 'phone',
        controller: 'phoneManagerController'
    }).state('403', 
    {
        url: '/403',
        templateUrl: '403'
    });

});

App.factory('AuthenticationInterceptor',['$location','$injector', function RequestInterceptor($location,$injector) {
    var service = this;
    service.responseError = function(response) {
        if (response.status === 403) {
            console.log('403 forbidden');
            //$state.go('403');
            $injector.get('$state').transitionTo('403');
            //$location.path('/403');
        }
        return response;
    };
    return service;
}]);


