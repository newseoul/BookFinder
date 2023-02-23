(() => {
	
	// page rendering
	const renderPage = (bookId) => {
		axios.get(`/api/book/${bookId}`)
		.then(response => {
			const book = response.data;
			
			// book image
			const divThumbnail = document.querySelector("#div-book-thumbnail");
			while (divThumbnail.firstChild) { 
			    divThumbnail.removeChild(divThumbnail.firstChild);
			}
			const img = document.createElement("img");
			img.classList.add("img-thumbnail");
			img.classList.add("border");
			if(typeof book.filename !== "undefined" && book.filename !== "" && book.filename !== null) {
				img.setAttribute("src", "/images/uploads/" + book.filename);
			} else {
				img.setAttribute("src", "/images/noimg.jpg");
			}
			divThumbnail.appendChild(img);
			
			// book attributes
			document.querySelector("#title-book-name").textContent = book.bookName;
			document.querySelector("#li-author").textContent = "저자: " +  ( book.author !== "" && book.author !== null ? book.author : "" );
			document.querySelector("#li-publisher").textContent = "출판사: " +  ( book.publisher !== "" && book.publisher !== null ? book.publisher : "" );
			document.querySelector("#li-publication-date").textContent = "발행연도: " +  ( book.publicationDate !== "" && book.publicationDate !== null ? book.publicationDate.substring(0, 4) : "" );
			document.querySelector("#li-location-name").innerHTML = "소장위치: " +  ( typeof book.location === 'object' && book.location.locationName !== "" && book.location.locationName !== null ? book.location.locationName : "<small class='text-muted'>(알수없음)</small>" );
			document.querySelector("#li-location-memo").innerHTML = "청구기호: " +  ( book.locationMemo !== "" && book.locationMemo !== null ? book.locationMemo : "<small class='text-muted'>(없음)</small>" );
			if(typeof book.rentalStatus === 'string') {
				let badge = '<span class="badge text-bg-secondary">(알수없음)</span>';
				switch(book.rentalStatus) {
					case 'rentable': badge='<span class="badge text-bg-success">대출가능</span>'; break;
					case 'on_rental': badge='<span class="badge text-bg-warning">대출불가</span>'; break;
					case 'overdue': badge='<span class="badge text-bg-danger">대출불가</span>'; break;
				}				
				document.querySelector("#li-rental-status").innerHTML = "대출가능여부: " +  badge;
				const btnBookRental = document.querySelector("#btn-book-rental");
				if(typeof btnBookRental === 'object' && btnBookRental !== null) {
					if(book.rentalStatus === 'rentable') {
						btnBookRental.removeAttribute("disabled");					
					} else {
						btnBookRental.setAttribute("disabled", "disabled");
					}
				}
			}
			document.querySelector("#div-book-detail").innerHTML = (book.bookDetail !== "" && book.bookDetail !== null) ? book.bookDetail.replaceAll("\n", "<br>") : "<small class='text-muted'>소개된 내용이 없습니다.</small>";
		})
		.catch(error => {
			console.error('Ajax 통신 오류');
			console.error(error);
		});
	};
	
	
	// onload
	document.addEventListener("DOMContentLoaded", () => {
		const input = document.querySelector("#input-book-id");
		renderPage(input.value);
		
		
		// 도서 대출 신청 버튼 클릭시
		const btnBookRental = document.querySelector("#btn-book-rental");
		if(typeof btnBookRental === 'object' && btnBookRental !== null) {
			btnBookRental.addEventListener("click", () => {
				const bookId = document.querySelector("#input-book-id").value;
				
				const fd = new FormData();
				const csrfElem = document.getElementById("csrfToken");
				fd.append(csrfElem.getAttribute('name'), csrfElem.value);
				
				axios.post(`/api/rental/${bookId}`, fd)
				.then(response => {
					console.log(response);
					alert("대출 신청이 완료되었습니다.");
					renderPage(bookId);
					
				}).catch(error => {
					console.error('Ajax 통신 오류');
					console.error(error);
					if(error.response.status === 404) {
						alert("다시 확인 후 시도해주세요.");
						renderPage(bookId);
					}
					if(error.response.status === 403) {
						alert("로그인 후 사용 가능합니다.");
						window.location.href = "/login";
					}
				})
			});
			
		}
	});
})();