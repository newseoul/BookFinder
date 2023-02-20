(() => {
	const show = () => {
		const username = document.querySelector("#username").value;
		axios.get(`/api/user/${username}`)
		.then(response => {
			const user = response.data;
			
			document.querySelector("#text-username").textContent = user.username;
			document.querySelector("#text-email").textContent = user.email;
			document.querySelector("#text-name").textContent = user.name;
			
			const elem = document.querySelector("#text-phone-number");
			while (elem.firstChild) { 
		 	   elem.removeChild(elem.firstChild);
			}
			
			if(typeof user.mobileCarrier === 'string' && typeof user.phoneNumber === 'string' ) {
				const carrier = document.createElement("span");
				carrier.classList.add("badge");
				carrier.classList.add("text-bg-warning");
				carrier.classList.add("me-1");
				carrier.textContent = user.mobileCarrier;
				elem.appendChild(carrier);
				
				const phoneNumber = document.createElement("span"); 
				phoneNumber.textContent = user.phoneNumber.replaceAll(/-/g, '');
				elem.appendChild(phoneNumber);
			}
			
		})
		.catch(error => {
			console.error('Ajax 통신 오류');
			console.error(error);			
		});
	};
	
	// onload
	document.addEventListener("DOMContentLoaded", () => {
		show();
	});
})();