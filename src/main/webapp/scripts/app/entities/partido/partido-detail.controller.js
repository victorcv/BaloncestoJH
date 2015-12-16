'use strict';

angular.module('ligabaloncestoApp')
    .controller('PartidoDetailController', function ($scope, $rootScope, $stateParams, entity, Partido, Estadisticas_jugador_partido, Arbitro, Temporada, Equipo) {
        $scope.partido = entity;
        $scope.load = function (id) {
            Partido.get({id: id}, function(result) {
                $scope.partido = result;
            });
        };
        $rootScope.$on('ligabaloncestoApp:partidoUpdate', function(event, result) {
            $scope.partido = result;
        });
    });
