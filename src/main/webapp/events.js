function Events($scope, $http) {
	$http.get('http://localhost:8080/MVC4Example/events.json').
		success(function(data) {
			$scope.events = data;
		});
}