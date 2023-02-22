(function() {
	
	// 엘리멘트 초기화
	const resetElem = (id) => {
		const elem = document.getElementById(id);
		while (elem.firstChild) { 
		    elem.removeChild(elem.firstChild);
		}
	}
	
	// 도서 렌더링
	const renderBook = (book) => {
		
				// 행
				const row = document.createElement("tr");
				
				// 행의 첫번째 열-도서번호
				const cellBookId = document.createElement("td");
				cellBookId.textContent = book.bookId;
				row.appendChild(cellBookId);
				
				// 행의 두번째 열-도서명
				const cellBookName = document.createElement("td");
				//cellBookName.textContent = book.bookName;
				row.appendChild(cellBookName);
				
				const link = document.createElement("a");
				link.textContent = book.bookName;
				link.setAttribute("href", "admin/book/" + book.bookId);
				cellBookName.appendChild(link);
				
				// 행의 세번째 열-저자
				const cellAuthor = document.createElement("td");
				cellAuthor.textContent = book.author;
				row.appendChild(cellAuthor);
				
				// 행의 네번째 열-출판사
				const cellPublisher = document.createElement("td");
				cellPublisher.textContent = book.publisher;
				row.appendChild(cellPublisher);
				
				// 행의 다섯번째 열-출판일
				const cellPublicationDate = document.createElement("td");
				cellPublicationDate.textContent = book.publicationDate;
				row.appendChild(cellPublicationDate);
				
				// 행의 여섯번째 열-대출여부
				const cellRentalStatus = document.createElement("td");
				//cellRentalStatus.textContent = book.rentalStatus;
				if (book.rentalStatus == "rentable"){
					cellRentalStatus.textContent = "대출가능";
				} else if (book.rentalStatus == "overdue"){
					cellRentalStatus.textContent = "연체중";
				} else {
					cellRentalStatus.textContent = "대출중";
				}
				row.appendChild(cellRentalStatus);
				
				// 행의 여섯번째 열-상세정보
				//const cellDetailBook = document.createElement("td");
				//cellDetailBook.innerHTML = "<span style='color:blue'>상세정보보기</span>"
				//row.appendChild(cellDetailBook);
				
				//const link = document.createElement("a");
				//link.innerHTML = "<td style='color:blue'>상세정보보기</td>"
				//link.classList.add("link-secondary");
				//link.classList.add("link-book-name");
				//link.setAttribute("href", "/book/" + book.bookId);
				
				//cellDetailBook.appendChild(link);
				
				const target = document.getElementById("target");
				target.appendChild(row);
			};
		
		
	// 페이지네이션 렌더링
	const renderPagination = (totalCount, keyword, condition, rentalStatus, page) => {
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
					search(keyword, condition, rentalStatus, i);
				});
				
				li.appendChild(a);
				ul.appendChild(li);
			}
			nav.appendChild(ul);
			document.querySelector("#pagination").appendChild(nav);
		}
	}
		
	
	// 페이지 총 개수 표시
	const renderTotalCount = (keyword, condition, rentalStatus, page) => {
		const pageCount = document.getElementById('page-count');
		pageCount.textContent = "";
		
		const params = { keyword: keyword, 'condition':condition };
		axios.get('/api/book/count', {params})
		.then(function (response) {
			const totalCount = response.data;
			pageCount.textContent = "전체 도서 수 : " + totalCount +"권";
			renderPagination(totalCount, keyword, condition, rentalStatus, page);
		})
		.catch(function (error) {
			console.error('Ajax 통신 오류');
			console.error(error);
		});
	};
		
			
	// 도서 검색 
	const search = (keyword, condition, rentalStatus, page) => {
		
		const params = { keyword: keyword, 'condition':condition, 'rentalStatus':rentalStatus, 'page':page };
		axios.get('/api/book', {params})
		.then(function (response) {
			// 총 개수 표시
			renderTotalCount(keyword, condition, rentalStatus, page);
			
			// 검색 결과 영역 초기화
			resetElem('target');
			
			// 도서 검색 결과 렌더링
			response.data.forEach(book => renderBook(book));
		})
		.catch(function (error) {
			console.error('Ajax 통신 오류');
			console.error(error);
		});	
	}
	

	// HTML 언어로 쓰여진 문자열들을 웹브라우저가 화면에 렌더링 완료했다.
	document.addEventListener("DOMContentLoaded", function () {
		
		// 기본적으로 페이지 뜨게끔
		const keyword = document.getElementById("input-search-keyword").value;
		const select = document.getElementById("select-search-condition");
		const condition =  select.options[select.selectedIndex].value;
		
		const rentalStatusSelect = document.getElementById("select-rental-status");
		const rentalStatus = rentalStatusSelect.options[rentalStatusSelect.selectedIndex].value;
		search(keyword, condition, rentalStatus, 1);
		
		// 검색 버튼 클릭시 이벤트
		const button = document.getElementById("button-search");
		button.addEventListener("click", () => {
			const keyword = document.getElementById("input-search-keyword").value;
			const select = document.getElementById("select-search-condition");
			const condition =  select.options[select.selectedIndex].value;
			const rentalStatusSelect = document.getElementById("select-rental-status");
			const rentalStatus = rentalStatusSelect.options[rentalStatusSelect.selectedIndex].value;
			search(keyword, condition, rentalStatus, 1);
		});
		
		// 엔터키 입력시 이벤트
		const input = document.getElementById("input-search-keyword");
		input.addEventListener("keyup", (e) => {
			if (e.keyCode === 13) {
				e.preventDefault();
				const select = document.getElementById("select-search-condition");
				const condition =  select.options[select.selectedIndex].value;
				const rentalStatusSelect = document.getElementById("select-rental-status");
				const rentalStatus = rentalStatusSelect.options[rentalStatusSelect.selectedIndex].value;
				search(input.value, condition, rentalStatus, 1);
			}
		});
		
		/*//필터 전체보기 클릭시 이벤트
		const seeAll = document.getElementById("book-filter-seeAll");
		seeAll.addEventListener("click", () => {
			console.log("전체보기 선택");
		});
		
		//필터 대출중 클릭시 이벤트
		const seeNo = document.getElementById("book-filter-seeNo");
		seeAll.addEventListener("click", () => {
			console.log("대출중 선택");
		});
		//필터 대출가능 클릭시 이벤트
		const seeOk = document.getElementById("book-filter-seeOk");
		seeAll.addEventListener("click", (e) => {
			console.log("대출가능 선택");
		});*/

	});
	
	
	
	
})();


















