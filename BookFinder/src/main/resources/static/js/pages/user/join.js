(() => {
	
	// onload
	document.addEventListener("DOMContentLoaded", () => {
		
		const form = document.querySelector("#form-join");
		form.addEventListener("submit", (e) => {
			e.preventDefault();
			
			const fd = new FormData(form);
			
			axios.post('/api/join', fd)
			.then(response => {
				console.log(response);
				// window.location.href = "/";
			})
			.catch(error => {
				console.error('Ajax 통신 오류');
				console.error(error);
			})
			return false;
		});
	});
})();