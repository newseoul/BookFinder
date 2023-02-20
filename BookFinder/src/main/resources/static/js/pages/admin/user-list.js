(() => {
	// 엘리멘트 초기화
	const resetElem = (id) => {
		const elem = document.getElementById(id);
		while (elem.firstChild) { 
		    elem.removeChild(elem.firstChild);
		}
	}
	
	// 페이지네이션 렌더링
	const renderPagination = (totalCount, keyword, page) => {
		resetElem('pagination');
		
		const lastPage = Math.ceil(totalCount / 10);
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
					list(keyword, i);
				});
				
				li.appendChild(a);
				ul.appendChild(li);
			}
			nav.appendChild(ul);
			document.querySelector("#pagination").appendChild(nav);
		}
	};
	
	// 페이지 총 개수 표시
	const renderTotalCount = (keyword, page) => {
		const pageCount = document.getElementById('page-count');
		pageCount.textContent = "";
		axios.get('/api/user/count')
		.then(response => {
			const totalCount = response.data;
			pageCount.textContent = "전체: " + totalCount;
			renderPagination(totalCount, keyword, page);
		})
		.catch(error => {
			console.error('Ajax 통신 오류');
			console.error(error);
		});
	}
	
	// 사용자 행 렌더링
	const renderUserRow = (user) => {
		const row = document.createElement("tr");
		
		const username = document.createElement("td");
		username.textContent = user.username;
		row.appendChild(username);
		
		const email = document.createElement("td");
		email.textContent = user.email;
		row.appendChild(email);
		
		const name = document.createElement("td");
		name.textContent = user.name;
		row.appendChild(name);
		
		const phoneNumber = document.createElement("td");
		if(typeof user.mobileCarrier === 'string' && typeof user.phoneNumber === 'string' ) {
			const carrier = document.createElement("span");
			carrier.classList.add("badge");
			carrier.classList.add("text-bg-warning");
			carrier.classList.add("me-1");
			carrier.textContent = user.mobileCarrier;
			phoneNumber.appendChild(carrier);
			
			const phoneNumberText = document.createElement("span");
			phoneNumberText.textContent = user.phoneNumber.replaceAll(/-/g, '');
			
			phoneNumber.appendChild(phoneNumberText);
		}
		row.appendChild(phoneNumber);
		
		document.querySelector("#tbody-user-list").appendChild(row);
	}
	
	// 목록 표시
	const list = (keyword, page) => {
		resetElem('tbody-user-list');
		
		const params = {'keyword':keyword, 'page': page};
		axios.get("/api/user", {params})
		.then(response => {
			// 총 개수 표시
			renderTotalCount(keyword, page);
			const users = response.data;
			
			// 사용자 검색 결과 렌더링
			users.forEach(user => renderUserRow(user));
		})
		.catch(error => {
			console.error('Ajax 통신 오류');
			console.error(error);
		});
	};
	
	// onload
	document.addEventListener("DOMContentLoaded", () => {
		const inputSearchElem = document.querySelector("#input-search-keyword");
		const keyword = inputSearchElem.value;
		list(keyword, 1);
		
		// 검색 버튼 클릭시 이벤트
		const button = document.getElementById("button-search");
		button.addEventListener("click", () => {
			const keyword = inputSearchElem.value;
			list(keyword, 1);
		});
		
		// 엔터키 입력시 이벤트
		inputSearchElem.addEventListener("keyup", (e) => {
			if (e.keyCode === 13) {
				e.preventDefault();
				list(inputSearchElem.value, 1);
			}
		});
	});
	
	
})();