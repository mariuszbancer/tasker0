(function () {
    'use strict';

    angular
        .module('tasker0App')
        .factory('UserStory', UserStory);

    UserStory.$inject = ['$resource'];

    function UserStory($resource) {
        var url = '/api/userStories';

        return $resource(url, {}, {
            'get': {
                url: url + '/:userStoryId',
                params: {userStoryId: '@id'}
            },
            'getBySprint': {
                url: url + "/bySprint/:sprintId",
                params: {sprintId: '@sprintId'}
            },
            'delete': {
                url: url + '/:userStoryId', params: {userStoryId: '@userStoryId'}, method: 'DELETE'
            },
            'moveUp': {
                url: url + "/moveUp/:userStoryId", param: {userStoryId: "@userStoryId"}, method: 'GET'
            },
            'moveDown': {
                url: url + "/moveDown/:userStoryId", param: {userStoryId: "@userStoryId"}, method: 'GET'
            },
            'update': {
                url: url, method: 'PUT'
            }
        });
    }
})();
