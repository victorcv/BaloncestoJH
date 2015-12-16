'use strict';

angular.module('ligabaloncestoApp')
    .controller('JugadorController', function ($scope, Jugador, ParseLinks) {
        $scope.jugadors = [];
        $scope.page = 1;
        $scope.loadAll = function() {
            Jugador.query({page: $scope.page, per_page: 20}, function(result, headers) {
                $scope.links = ParseLinks.parse(headers('link'));
                for (var i = 0; i < result.length; i++) {
                    $scope.jugadors.push(result[i]);
                }
            });
        };
        $scope.reset = function() {
            $scope.page = 1;
            $scope.jugadors = [];
            $scope.loadAll();
        };
        $scope.loadPage = function(page) {
            $scope.page = page;
            $scope.loadAll();
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            Jugador.get({id: id}, function(result) {
                $scope.jugador = result;
                $('#deleteJugadorConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Jugador.delete({id: id},
                function () {
                    $scope.reset();
                    $('#deleteJugadorConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.reset();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.jugador = {nombre: null, fecha: null, totalCanastas: null, totalAsistencias: null, totalRebotes: null, posicion: null, id: null};
        };
    });
