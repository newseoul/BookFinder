(() => {
	// 도서 분류
	const categoryList = () => {
		// 도서 위치 Element
		const elem = document.querySelector("#categoryId");
		
		// 하위 엘리멘트 제거
		while(elem.firstChild) {
			elem.removeChild(elem.firstChild);
		}
		
		axios.get('/api/admin/category')
		.then(function (response) {
			const placeholder = document.createElement('option');
			placeholder.textContent = "도서 분류를 선택해주세요";
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
			placeholder.textContent = "도서 위치 오류";
			elem.appendChild(placeholder);
		});	
	};
	
	// 도서 위치
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
			placeholder.textContent = "도서 위치를 선택해주세요";
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
	
	// 도서 등록
	const write = (formData) => {
		axios.post('/api/admin/insert', formData, {
		    headers: {
		        'Content-Type': 'multipart/form-data'
		      }
		  })
		.then(function (response) {
			console.log('Ajax 통신 성공 - inset');
		})
		.catch(function (error) {
			console.error('Ajax 통신 오류 - inset');
		});	
	};
	 
	document.addEventListener("DOMContentLoaded", () => {
		const button = document.getElementById("btn-register");
		button.addEventListener("click", () => {
			const formData = new FormData();
			formData.append('bookName', document.getElementById("bookName").value); // 도서명
			formData.append('author', document.getElementById("author").value); // 저자
			formData.append('publisher', document.getElementById("publisher").value); // 출판사
			formData.append('publicationDate', document.getElementById("publicationDate").value); // 출판일
			
			if(locationId !== null && typeof locationId === 'number') {
				formData.append('locationId', document.getElementById("locationId").value); 
			} // 도서위치
			
			formData.append('locationMemo', document.getElementById("locationMemo").value); // 도서 상세위치
			
			if(categoryId !== null && typeof categoryId === 'number') {
				formData.append('categoryId', document.getElementById("categoryId").value);
			} // 분류ID
			
			// 노출여부
			const openChk = document.querySelector('input[name="openChk"]:checked').value;
			formData.append('displayStatus', openChk); 
	
			formData.append('img', document.getElementById("filename").files[0]); // 도서 이미지
			formData.append('bookDetail', document.getElementById("bookDetail").value); // 도서 상세(내용)
			
			write(formData);
			
			// 값 확인
			for(const value of formData.values()) {
				console.log(value)
			};
		}); // click
		
		locationList(); // 도서 위치
		categoryList(); // 도서 분류
		
	});
	
})();