(() => {
	// 사용자 정보
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
	
	// 도서 대출 내역
	const rental = () => {
		const tbody = document.querySelector("#tbody-book-rental");
		while (tbody.firstChild) { 
	 	   tbody.removeChild(tbody.firstChild);
		}
			
		const username = document.querySelector("#username").value;
		axios.get(`/api/user/${username}/rental`)
		.then(response => {
			const rentalList = response.data;
			rentalList.forEach(rental => {
				const tr = document.createElement("tr");
				
				const cellBookName = document.createElement("td");
				const linkBookName = document.createElement("a");
				linkBookName.classList.add("text-primary");
				linkBookName.setAttribute("href", "/admin/book/" + rental.bookId);
				linkBookName.textContent = rental.bookName; 
				cellBookName.appendChild(linkBookName);
				tr.appendChild(cellBookName);
				
				const cellRentalDate = document.createElement("td");
				cellRentalDate.textContent = rental.rentalDate;
				tr.appendChild(cellRentalDate);
				
				const cellReturnDueDate = document.createElement("td");
				cellReturnDueDate.textContent = rental.returnDueDate;
				tr.appendChild(cellReturnDueDate);
				
				const cellReturnDate = document.createElement("td");
				cellReturnDate.textContent = rental.returnDate;
				tr.appendChild(cellReturnDate);
				
				const cellRentalStatus = document.createElement("td");
				
				//대여 상태( 대여중:on_rental, 연체중:overdue, 반납완료 : returned)
				if(typeof rental.rentalStatus === 'string') {
					switch(rental.rentalStatus) {
						case 'on_rental':
							cellRentalStatus.textContent = "대여중";
							break;							
						case 'overdue':
							cellRentalStatus.textContent = "연체중";
							break;
						case 'returned':
							cellRentalStatus.textContent = "반납완료";
							break;
					}
					
				}
				tr.appendChild(cellRentalStatus);
				
				tbody.appendChild(tr);
			});
			
			
			
			
		}).catch(error => {
			console.error('Ajax 통신 오류');
			console.error(error);			
		});
		
	};
	
	// onload
	document.addEventListener("DOMContentLoaded", () => {
		show();
		rental();
	});
})();