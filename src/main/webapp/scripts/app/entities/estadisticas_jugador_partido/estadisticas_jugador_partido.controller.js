'use strict';

angular.module('ligabaloncestoApp')
    .controller('Estadisticas_jugador_partidoController', function ($scope, Estadisticas_jugador_partido, ParseLinks) {
        $scope.estadisticas_jugador_partidos = [];
        $scope.page = 1;
        $scope.loadAll = function() {
            Estadisticas_jugador_partido.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                $scope.estadisticas_jugador_partidos = result;
            });
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            Estadisticas_jugador_partido.get({id: id}, function(result) {
                $scope.estadisticas_jugador_partido = result;
                $('#deleteEstadisticas_jugador_partidoConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Estadisticas_jugador_partido.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteEstadisticas_jugador_partidoConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.estadisticas_jugador_partido = {asistencias: null, canastas: null, faltas: null, id: null};
        };
    });
