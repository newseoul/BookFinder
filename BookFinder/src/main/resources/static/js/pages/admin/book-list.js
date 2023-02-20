(() => {
	const renderBook = (book) => {
		
		const h = (text) => { 
			const li = document.createElement('li');
			li.textContent = text;
			return li; 
		};
		
		const ul = document.createElement('ul');
		ul.appendChild(h("도서번호 : " + book.bookId));
		ul.appendChild(h("도서명 : " + book.bookName));
		ul.appendChild(h("저자 : " + book.author));
		ul.appendChild(h("발간일 : " + book.publicationDate));
		ul.appendChild(h("출판사 : " + book.publisher));
		
		document.getElementById('result-area').appendChild(ul);
	};
	
	const search = (keyword) => {
		const params = { bookName: keyword };
		axios.get('/api/book', {params})
		.then(function (response) {
			response.data.forEach(book => renderBook(book));
		})
		.catch(function (error) {
			console.log("ajax 통신 오류...");
		});	
	}
	
	document.addEventListener("DOMContentLoaded", () => {
		const button = document.getElementById("button-search");
		button.addEventListener("click", () => {
			const input = document.getElementById("input-search-keyword");
			search(input.value);
		});
		
	});
})();