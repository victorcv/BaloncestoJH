'use strict';

angular.module('ligabaloncestoApp')
    .factory('Jugador', function ($resource, DateUtils) {
        return $resource('api/jugadors/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.fecha = DateUtils.convertLocaleDateFromServer(data.fecha);
                    return data;
                }
            },
            'update': {
                method: 'PUT',
                transformRequest: function (data) {
                    data.fecha = DateUtils.convertLocaleDateToServer(data.fecha);
                    return angular.toJson(data);
                }
            },
            'save': {
                method: 'POST',
                transformRequest: function (data) {
                    data.fecha = DateUtils.convertLocaleDateToServer(data.fecha);
                    return angular.toJson(data);
                }
            }
        });
    });
