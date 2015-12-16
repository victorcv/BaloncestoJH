'use strict';

angular.module('ligabaloncestoApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('estadisticas_jugador_partido', {
                parent: 'entity',
                url: '/estadisticas_jugador_partidos',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'ligabaloncestoApp.estadisticas_jugador_partido.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/estadisticas_jugador_partido/estadisticas_jugador_partidos.html',
                        controller: 'Estadisticas_jugador_partidoController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('estadisticas_jugador_partido');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('estadisticas_jugador_partido.detail', {
                parent: 'entity',
                url: '/estadisticas_jugador_partido/{id}',
                data: {
                    roles: ['ROLE_USER'],
                    pageTitle: 'ligabaloncestoApp.estadisticas_jugador_partido.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/estadisticas_jugador_partido/estadisticas_jugador_partido-detail.html',
                        controller: 'Estadisticas_jugador_partidoDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('estadisticas_jugador_partido');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Estadisticas_jugador_partido', function($stateParams, Estadisticas_jugador_partido) {
                        return Estadisticas_jugador_partido.get({id : $stateParams.id});
                    }]
                }
            })
            .state('estadisticas_jugador_partido.new', {
                parent: 'estadisticas_jugador_partido',
                url: '/new',
                data: {
                    roles: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/estadisticas_jugador_partido/estadisticas_jugador_partido-dialog.html',
                        controller: 'Estadisticas_jugador_partidoDialogController',
                        size: 'lg',
                        resolve: {
                            entity: function () {
                                return {asistencias: null, canastas: null, faltas: null, id: null};
                            }
                        }
                    }).result.then(function(result) {
                        $state.go('estadisticas_jugador_partido', null, { reload: true });
                    }, function() {
                        $state.go('estadisticas_jugador_partido');
                    })
                }]
            })
            .state('estadisticas_jugador_partido.edit', {
                parent: 'estadisticas_jugador_partido',
                url: '/{id}/edit',
                data: {
                    roles: ['ROLE_USER'],
                },
                onEnter: ['$stateParams', '$state', '$modal', function($stateParams, $state, $modal) {
                    $modal.open({
                        templateUrl: 'scripts/app/entities/estadisticas_jugador_partido/estadisticas_jugador_partido-dialog.html',
                        controller: 'Estadisticas_jugador_partidoDialogController',
                        size: 'lg',
                        resolve: {
                            entity: ['Estadisticas_jugador_partido', function(Estadisticas_jugador_partido) {
                                return Estadisticas_jugador_partido.get({id : $stateParams.id});
                            }]
                        }
                    }).result.then(function(result) {
                        $state.go('estadisticas_jugador_partido', null, { reload: true });
                    }, function() {
                        $state.go('^');
                    })
                }]
            });
    });
