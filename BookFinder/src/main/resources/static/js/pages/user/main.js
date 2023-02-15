(() => {
	// 엘리멘트 초기화
	const resetElem = (id) => {
		const elem = document.getElementById(id);
		while (elem.firstChild) { 
		    elem.removeChild(elem.firstChild);
		}
	}
	
	// 도서 렌더링
	const renderBook = (book) => {
		const card = document.createElement("div");
		card.classList.add("card");
		card.classList.add("flex-row");
		card.classList.add("book-row");
		
		// 도서 이미지
		const img = document.createElement("img");
		img.classList.add("card-img-left");
		img.classList.add("border");
		img.classList.add("book-thumbnail");
		if(typeof book.filename !== "undefined" && book.filename !== "" && book.filename !== null) {
			img.setAttribute("src", "/images/uploads/" + book.filename);
		} else {
			img.setAttribute("src", "/images/noimg.jpg");
		}
		card.appendChild(img);
		
		const cardBody = document.createElement("div");
		cardBody.classList.add("card-body");
		cardBody.classList.add("align-middle");
		
		// 도서명
		const title = document.createElement("h4");
		cardBody.classList.add("card-title");
		cardBody.classList.add("book-title");
		title.textContent = book.bookName;
		cardBody.appendChild(title);
		
		const cardText = document.createElement("div");
		
		// 첫번째 줄
		const p1 = document.createElement("p");
		p1.classList.add("mb-0");
		
		// 저자
		const author = document.createElement("span");
		author.classList.add("text-wrap");
		author.classList.add("border-end");
		author.classList.add("item-border-right");
		author.textContent = "저자: " +  ( book.author !== "" && book.author !== null ? book.author : "" );  
		p1.appendChild(author);
		
		// 출판사
		const publisher = document.createElement("span");
		publisher.classList.add("text-wrap");
		publisher.classList.add("border-end");
		publisher.classList.add("item-border-right");
		publisher.textContent = "출판사: " +  ( book.publisher !== "" && book.publisher !== null ? book.publisher : "" );  
		p1.appendChild(publisher);
		
		// 발행연도
		const publicationDate = document.createElement("span");
		publicationDate.classList.add("text-wrap");
		publicationDate.textContent = "발행연도: " +  ( book.publicationDate !== "" && book.publicationDate !== null ? book.publicationDate.substring(0, 4) : "" );  
		p1.appendChild(publicationDate);
		
		cardText.appendChild(p1);
		
		// 두번째 줄
		const p2 = document.createElement("p");
		
		// 도서위치
		const location = document.createElement("span");
		location.classList.add("text-wrap");
		location.classList.add("border-end");
		location.classList.add("item-border-right");
		location.innerHTML = "도서위치: " +  ( typeof book.location === 'object' && book.location.locationName !== "" && book.location.locationName !== null ? book.location.locationName : "<small class='text-muted'>(알수없음)</small>" );  
		p2.appendChild(location);
		
		// 청구기호
		const locationMemo = document.createElement("span");
		locationMemo.classList.add("text-wrap");
		locationMemo.innerHTML = "청구기호: " +  ( book.locationMemo !== "" && book.locationMemo !== null ? book.locationMemo : "<small class='text-muted'>(없음)</small>" );  
		p2.appendChild(locationMemo);
		
		cardText.appendChild(p2);
		
		cardBody.appendChild(cardText);
		card.appendChild(cardBody);
		document.getElementById('result-area').appendChild(card);
	};
	
	// 페이지네이션 렌더링
	const renderPagination = (totalCount, keyword, page) => {
		// 페이지네이션 엘리멘트 초기화
		resetElem('pagination');
		
		const lastPage = Math.ceil(totalCount / 15);
		if(lastPage > 0) {
			const nav = document.createElement("nav");
			const ul = document.createElement("ul");
			ul.classList.add("pagination");
			
			for(let i = 1; i <= lastPage; i++) {
				const li = document.createElement("li");
				li.classList.add("page-item");
				// 현재 페이지 표시
				if(i === page) {
					li.classList.add("active");
				}
				
				const a = document.createElement("a");
				a.classList.add("page-link");
				a.textContent = i;
				a.setAttribute("href", '#');
				a.addEventListener("click", () => {
					search(keyword, i);
				});
				
				li.appendChild(a);
				ul.appendChild(li);
			}
			nav.appendChild(ul);
			document.querySelector("#pagination").appendChild(nav);
		}
	}
	
	// 페이지 총 개수 표시
	const renderTotalCount = (keyword, page) => {
		const pageCount = document.getElementById('page-count');
		pageCount.textContent = "";
		
		const params = { bookName: keyword };
		axios.get('/api/book/count', {params})
		.then(function (response) {
			const totalCount = response.data;
			pageCount.textContent = "전체: " + totalCount;
			renderPagination(totalCount, keyword, page);
		})
		.catch(function (error) {
			console.error('Ajax 통신 오류');
			console.error(error);
		});
	};
	
	// 도서 검색 
	const search = (keyword, page) => {
		const params = { bookName: keyword, 'page':page };
		axios.get('/api/book', {params})
		.then(function (response) {
			// 총 개수 표시
			renderTotalCount(keyword, page);
			
			// 검색 결과 영역 초기화
			resetElem('result-area');
			
			// 도서 검색 결과 렌더링
			response.data.forEach(book => renderBook(book));
		})
		.catch(function (error) {
			console.error('Ajax 통신 오류');
			console.error(error);
		});	
	}
	
	// onload
	document.addEventListener("DOMContentLoaded", () => {
		// 검색 버튼 클릭시 이벤트
		const button = document.getElementById("button-search");
		button.addEventListener("click", () => {
			const input = document.getElementById("input-search-keyword");
			search(input.value, 1);
		});
		
		// 엔터키 입력시 이벤트
		const input = document.getElementById("input-search-keyword");
		input.addEventListener("keyup", (e) => {
			if (e.keyCode === 13) {
				e.preventDefault();
				search(input.value, 1);
			}
		});
		
	});
})();