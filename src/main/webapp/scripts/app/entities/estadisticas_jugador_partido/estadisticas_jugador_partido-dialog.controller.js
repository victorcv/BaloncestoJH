'use strict';

angular.module('ligabaloncestoApp').controller('Estadisticas_jugador_partidoDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Estadisticas_jugador_partido', 'Jugador', 'Partido',
        function($scope, $stateParams, $modalInstance, entity, Estadisticas_jugador_partido, Jugador, Partido) {

        $scope.estadisticas_jugador_partido = entity;
        $scope.jugadors = Jugador.query();
        $scope.partidos = Partido.query();
        $scope.load = function(id) {
            Estadisticas_jugador_partido.get({id : id}, function(result) {
                $scope.estadisticas_jugador_partido = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('ligabaloncestoApp:estadisticas_jugador_partidoUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.estadisticas_jugador_partido.id != null) {
                Estadisticas_jugador_partido.update($scope.estadisticas_jugador_partido, onSaveFinished);
            } else {
                Estadisticas_jugador_partido.save($scope.estadisticas_jugador_partido, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
