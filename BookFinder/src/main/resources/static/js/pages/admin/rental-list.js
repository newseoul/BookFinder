(() => {
	
	document.addEventListener("DOMContentLoaded", () => {
		axios.get('/api/rental', {params})
		.then(function (response) {
			
		})
		.catch(function (error) {
			console.error('Ajax 통신 오류');
			console.error(error);
		});	
	})
	
})();