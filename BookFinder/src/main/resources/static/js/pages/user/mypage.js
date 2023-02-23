(() => {
	// 나의 도서 대출 내역
	const bookRentalList = () => {
		const tbody = document.querySelector("#tbody-user-rental");
		
		while(tbody.firstChild) {
			tbody.removeChild(tbody.firstChild);
		}
		
		const username = document.querySelector("#username").value;
		axios.get(`/api/user/${username}/rental`)
		.then( (response) => {
			const books = response.data;
			for(let i = 0; i < books.length; i++) {
				const book = books[i];
				
				const tr = document.createElement("tr");
				
				const cellBookName = document.createElement("td");
				const linkBookName = document.createElement("a");
				linkBookName.classList.add("text-primary");
				linkBookName.setAttribute("href", "/book/" + book.bookId);
				linkBookName.textContent = book.bookName; 
				cellBookName.appendChild(linkBookName);
				tr.appendChild(cellBookName);
				
				// 대출일시
				const cellRentalDate = document.createElement("td");
				cellRentalDate.textContent = book.rentalDate;
				tr.appendChild(cellRentalDate);
				
				// 반납기한
				const cellReturnDueDate = document.createElement("td");
				cellReturnDueDate.textContent = book.returnDueDate;
				tr.appendChild(cellReturnDueDate);
				
				// 반납일자
				const cellReturnDate = document.createElement("td");
				cellReturnDate.textContent = book.returnDate;
				tr.appendChild(cellReturnDate);
				
				// 대출상태
				const cellRentalStatus = document.createElement("td");
				if(typeof book.rentalStatus === 'string') {
					switch(book.rentalStatus) {
						case 'on_rental':
							cellRentalStatus.textContent = "대출중";
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
			}
		})
		.catch(error => {
			console.error('Ajax 통신 오류');
			console.error(error);			
		}); // 1
	}
	
	// onload
	document.addEventListener("DOMContentLoaded", () => {
		bookRentalList();
	});
	
})();