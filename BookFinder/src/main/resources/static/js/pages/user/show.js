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
			document.querySelector("#li-location-name").innerHTML = "자료위치: " +  ( typeof book.location === 'object' && book.location.locationName !== "" && book.location.locationName !== null ? book.location.locationName : "<small class='text-muted'>(알수없음)</small>" );
			document.querySelector("#li-location-memo").innerHTML = "청구기호: " +  ( book.locationMemo !== "" && book.locationMemo !== null ? book.locationMemo : "<small class='text-muted'>(없음)</small>" );
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
		
	});
})();