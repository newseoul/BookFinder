(() => {
	
	// rendering error messages
	const renderErrors = (errors) => {
		Object.keys(errors).forEach(
			(field) => {
				console.log(field);
				const message = errors[field];
				const element = document.querySelector(`.error-message[data-for='${field}']`);
				element.textContent = message;
			}
		);
	};
	
	// onload
	document.addEventListener("DOMContentLoaded", () => {
		const form = document.querySelector("#form-join");
		form.addEventListener("submit", (e) => {
			e.preventDefault();
			
			const objects = document.querySelectorAll(".error-message");
			objects.forEach(obj => obj.textContent = "");
			
			const fd = new FormData(form);
			
			axios.post('/api/join', fd)
			.then(response => {
				console.log(response);
				// window.location.href = "/";
			})
			.catch(error => {
				console.error('Ajax 통신 오류');
				console.error(error);
				if(
					typeof error.response === 'object' && 
					typeof error.response.data === 'object' &&
					typeof error.response.data.errors === 'object' &&
					Object.keys(error.response.data.errors).length > 0
				) {
					renderErrors(error.response.data.errors);
				}
			})
			return false;
		});
	});
})();