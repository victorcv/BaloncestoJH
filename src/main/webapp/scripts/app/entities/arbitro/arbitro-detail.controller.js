'use strict';

angular.module('ligabaloncestoApp')
    .controller('ArbitroDetailController', function ($scope, $rootScope, $stateParams, entity, Arbitro, Liga, Partido) {
        $scope.arbitro = entity;
        $scope.load = function (id) {
            Arbitro.get({id: id}, function(result) {
                $scope.arbitro = result;
            });
        };
        $rootScope.$on('ligabaloncestoApp:arbitroUpdate', function(event, result) {
            $scope.arbitro = result;
        });
    });
