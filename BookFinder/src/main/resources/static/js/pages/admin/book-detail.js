(() => {
	// 상세 불러오기
	const select = (bookId) => {
		axios.get(`/api/admin/${bookId}`)
		.then(function (response) {
			const book = response.data;
			
			document.querySelector("#bookName").value = book.bookName;
			document.querySelector("#author").value = book.author;
			document.querySelector("#publisher").value = book.publisher;
			document.querySelector("#publicationDate").value = book.publicationDate;
			
			if(book.location !== null && typeof book.location.locationId === 'number') {
				document.querySelector('#locationId').value = book.location.locationId;
			}
			
			document.querySelector("#locationMemo").value = book.locationMemo;
			
			if(book.category !== null && typeof book.category.categoryId === 'number') {
				document.querySelector('#categoryId').value = book.category.categoryId;
			}
			
			// 노출 여부
			const displayStatusTrue = document.querySelector("#openY");
			const displayStatusFalse = document.querySelector("#openN"); 
			displayStatusTrue.checked = false;
			displayStatusFalse.checked = false;
			if(book.displayStatus === 1) {
				displayStatusTrue.checked = true;
			} else {
				displayStatusFalse.checked = true;
			}
		
			// 썸네일
			if(typeof book.filename === 'string') {				
				const img = document.createElement("img");
				img.setAttribute("src", `http://localhost:8090/images/uploads/${book.filename}`);
				document.querySelector("#area-thumbnail").appendChild(img);
				document.querySelector("#filename").setAttribute('value', book.filename);
				img.style.width = '100%';
			}
	
			document.querySelector("#beforeImg").value = book.filename // 이미지 변경 시 기존 이미지 삭제
			document.querySelector("#bookDetail").value = book.bookDetail;
			
		})
		.catch(function (error) {
			console.error(error);
			console.error('Ajax 통신 오류 - select');
		});	
	};
	
	const categoryList = () => {
		// 도서 분류 Element
		const elem = document.querySelector("#categoryId");
		
		// 하위 엘리멘트 제거
		while(elem.firstChild) {
			elem.removeChild(elem.firstChild);
		}
		
		axios.get('/api/admin/category')
		.then(function (response) {
			const placeholder = document.createElement('option');
			//placeholder.textContent = "도서 분류를 선택해주세요";
			elem.appendChild(placeholder);
			for(let i = 0; i < response.data.length; i++) {
				const option = document.createElement("option");
				option.textContent = `${response.data[i].categoryName}`;
				option.value = `${response.data[i].categoryId}`;
				elem.appendChild(option);				
			}
		})
		.catch(function (error) {
			console.error('Ajax 통신 오류 - location');
			const placeholder = document.createElement('option');
			placeholder.textContent = "도서 분류 오류";
			elem.appendChild(placeholder);
		});	
	};
	
	const locationList = () => {
		// 도서 위치 Element
		const elem = document.querySelector("#locationId");
		
		// 하위 엘리멘트 제거
		while(elem.firstChild) {
			elem.removeChild(elem.firstChild);
		}
		
		axios.get('/api/admin/location')
		.then(function (response) {
			const placeholder = document.createElement('option');
			//placeholder.textContent = "도서 위치를 선택해주세요";
			elem.appendChild(placeholder);
			for(let i = 0; i < response.data.length; i++) {
				const option = document.createElement("option");
				option.textContent = `${response.data[i].locationName}열 : ${response.data[i].locationDetail}`;
				option.value = `${response.data[i].locationId}`;
				elem.appendChild(option);				
			}
		})
		.catch(function (error) {
			console.error('Ajax 통신 오류 - location');
			const placeholder = document.createElement('option');
			placeholder.textContent = "도서 분류 오류";
			elem.appendChild(placeholder);
		});	
	};
	
	// 도서 수정 
	const update = (formData) => {
		axios.post('/api/admin/update', formData, {
		    headers: {
		        'Content-Type': 'multipart/form-data'
		      }
		  })
		.then(function (response) {
			console.log('Ajax 통신 성공 - update');
		})
		.catch(function (error) {
			console.error('Ajax 통신 오류 - update');
		});
	};
	
	document.addEventListener("DOMContentLoaded", () => {
		
		locationList();
		categoryList();
		
		const input = document.querySelector("#input-book-id");
		select(input.value);
		
		const button = document.getElementById("btn-modify");
		button.addEventListener("click", () => {
			const formData = new FormData();
			formData.append('bookId', document.getElementById("input-book-id").value);
			formData.append('bookName', document.getElementById("bookName").value);
			formData.append('author', document.getElementById("author").value);
			formData.append('publisher', document.getElementById("publisher").value);
			formData.append('publicationDate', document.getElementById("publicationDate").value);
			formData.append('locationId', document.getElementById("locationId").value);
			formData.append('locationMemo', document.getElementById("locationMemo").value);
			formData.append('categoryId', document.getElementById("categoryId").value);
			
			const openChk = document.querySelector('input[name="openChk"]:checked').value;
			formData.append('displayStatus', openChk);
			
			formData.append('img', document.getElementById("filename").files[0]);
			formData.append('beforeImg', document.getElementById("beforeImg").value); // 이미지 변경 시 기존 이미지 삭제
			formData.append('bookDetail', document.getElementById("bookDetail").value);
			
			update(formData);
			
			// 값 확인
			for(const value of formData.values()) {
				console.log(value)
			};
		}); // click
		
	});
	
})();