'use strict';

angular.module('ligabaloncestoApp').controller('PartidoDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Partido', 'Estadisticas_jugador_partido', 'Arbitro', 'Temporada', 'Equipo',
        function($scope, $stateParams, $modalInstance, entity, Partido, Estadisticas_jugador_partido, Arbitro, Temporada, Equipo) {

        $scope.partido = entity;
        $scope.estadisticas_jugador_partidos = Estadisticas_jugador_partido.query();
        $scope.arbitros = Arbitro.query();
        $scope.temporadas = Temporada.query();
        $scope.equipos = Equipo.query();
        $scope.load = function(id) {
            Partido.get({id : id}, function(result) {
                $scope.partido = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('ligabaloncestoApp:partidoUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.partido.id != null) {
                Partido.update($scope.partido, onSaveFinished);
            } else {
                Partido.save($scope.partido, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
