var app = angular.module("myApp", ['truncate', 'ui', 'ui.bootstrap', 'restangular'], 
						function ($routeProvider, RestangularProvider) {
	$routeProvider.when('/', {
		templateUrl: 'views/Main.html',
		controller: MainCtrl
	}).when('/batches', {
		templateUrl: "views/Batch.html",
		controller: BatchCtrl
	}).when('/batches/:executionId', {
		templateUrl: "views/Job.html",
		controller: JobCtrl
	}).when('/transactions', {
		templateUrl: "views/Transaction.html",
		controller: JobCtrl
	}).otherwise({
		redirectTo: '/'
	});
							
	RestangularProvider.setBaseUrl("../api/");
});

Filters(app);
app.factory(Services());
app.directive(Directives());