'use strict';

angular.module('ligabaloncestoApp')
    .controller('Estadisticas_jugador_partidoDetailController', function ($scope, $rootScope, $stateParams, entity, Estadisticas_jugador_partido, Jugador, Partido) {
        $scope.estadisticas_jugador_partido = entity;
        $scope.load = function (id) {
            Estadisticas_jugador_partido.get({id: id}, function(result) {
                $scope.estadisticas_jugador_partido = result;
            });
        };
        $rootScope.$on('ligabaloncestoApp:estadisticas_jugador_partidoUpdate', function(event, result) {
            $scope.estadisticas_jugador_partido = result;
        });
    });
