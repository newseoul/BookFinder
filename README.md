![Github messages](https://img.shields.io/github/languages/count/newseoul/BookFinder)
![Github messages](https://img.shields.io/github/last-commit/newseoul/BookFinder)
![Github messages](https://img.shields.io/github/languages/top/newseoul/BookFinder)
![Github messages](https://img.shields.io/github/repo-size/newseoul/BookFinder)
![Github messages](https://img.shields.io/github/languages/code-size/newseoul/BookFinder)
![Github messages](https://img.shields.io/github/commit-activity/w/newseoul/BookFinder)
[![GitHub forks](https://img.shields.io/github/forks/newseoul/BookFinder)](https://github.com/newseoul/BookFinder/network)
[![GitHub issues](https://img.shields.io/github/issues/newseoul/BookFinder)](https://github.com/newseoul/BookFinder/issues)
[![GitHub stars](https://img.shields.io/github/stars/newseoul/BookFinder)](https://github.com/newseoul/BookFinder/stargazers)

# 📚BookFinder

## 📖서비스 소개
- 서점이나 도서관처럼 많은 책들이 있는 곳에서 내가 원하는 책이 있는지 색인을 통해서 쉽게 찾아볼 수 있음
- 도서의 위치와 도서의 상세정보도 확인할 수 있으며 도서 대출 신청을 통해 대기 없이 빠르게 도서 대출 가능

## 👤사용자 기능

###  도서 검색/도서 목록
- 사용자가 도서를 검색하면 해당 도서에 대한 위치/대출여부/상세페이지를 볼 수 있도록 기능 구현
- 편리한 검색을 위한 도서명/저자 선택 가능, 검색어 포함 전체 도서 출력 기능 구현 

<div align="center">
  <img src="https://user-images.githubusercontent.com/8243179/225185214-67983a32-01d6-492e-889f-26f79d982cb6.png" style="width:49%">
  <img src="https://user-images.githubusercontent.com/8243179/225185223-3600c3ab-aedc-4a95-9f01-b371133bd5f4.png" style="width:49%">
</div>
<div align="center">
  <img src="https://user-images.githubusercontent.com/8243179/225185249-5d4b9778-a7b2-44d5-a310-92918cc947de.png" style="width:600px">
</div>


### 도서 상세/대출 내역
- 로그인 시 도서 대출 신청 가능, 마이페이지에서 대출 내역 확인 기능 구현
<div align="center">
  <img src="https://user-images.githubusercontent.com/8243179/225185647-23d5953a-e4cc-44c7-a986-d8ccb21b6ebf.png" style="width:49%">
  <img src="https://user-images.githubusercontent.com/8243179/225185655-fdd60241-33a5-40b5-a6db-e3ff2186ac89.png" style="width:49%">
</div>

### 회원가입/로그인
- 회원만 도서 대출을 신청할 수 있도록 회원가입 및 로그인 기능을 구현
<div align="center">
  <img src="https://user-images.githubusercontent.com/8243179/225186382-e10f77b0-8259-4954-bd6a-3eb51e94eef0.png" style="width:49%">
  <img src="https://user-images.githubusercontent.com/8243179/225186392-94d6809e-8bb3-4c20-8d08-7d55117e09f9.png" style="width:49%">
</div>

## ⚙️관리자 기능
### 도서 관리
- 도서의 목록을 조회하고 신규 도서 등록 및 수정 기능을 구현
<div align="center">
  <img src="https://user-images.githubusercontent.com/8243179/225186591-52f12aaf-ea5f-42df-9828-b94263b9c182.png" style="width:600px">
</div>
<div align="center">
  <img src="https://user-images.githubusercontent.com/8243179/225186600-fb83e325-eddd-451b-a9f9-b863b02e06cb.png" style="width:49%">
  <img src="https://user-images.githubusercontent.com/8243179/225186597-0feed18c-d531-4d62-9cbc-a97dfe2fc1a0.png" style="width:49%">
</div>

### 도서 대출 관리
- 대출 관리를 편하게 하기 위해 대출 상태에 따른 선택 검색 기능 구현
- 사용자가 도서 반납 시 반납 완료 처리 기능 구현

<div align="center">
  <img src="https://user-images.githubusercontent.com/8243179/225186929-c7be347f-7e49-4bb7-b3dc-cc9bfd7b2504.png" style="width:49%">
  <img src="https://user-images.githubusercontent.com/8243179/225186927-8f408f1f-4f68-4c8a-95e1-c53c73eb09bb.png" style="width:49%">
</div>


### 회원 관리
- 회원 관리 기능 구현 
- 회원별 대출 내역 확인 기능 구현

<div align="center">
  <img src="https://user-images.githubusercontent.com/8243179/225187106-823bcf2a-f0ae-4dbb-8958-73532df07ee8.png" style="width:49%">  
  <img src="https://user-images.githubusercontent.com/8243179/225187101-3f2e0b63-23b6-4aae-b735-351fc81820e7.png" style="width:49%">
</div>

# 🧑‍💻사용 기술
- Spring Framework
  - Spring Boot
  - Spring Data JPA 
  - Spring Security
  - Hibernate
  - Thymeleaf
  - Lombok
- Oracle Database
- AJAX
  - Axios
- Bootstrap 5
- Javascript ES6+

# 🍩ERD
![image](https://user-images.githubusercontent.com/8243179/224896805-75995138-71d4-4739-827b-2da537410fcd.png)

# 👥구성원 소개

| 이름 | 직무 | 역할 |
| :---: | :---: | --- |
| [류순하(리더)](https://github.com/rainofpainki) | Full-Stack 개발 | 메인페이지, 도서 대출/반납, 회원/권한 |
| [이미선](https://github.com/2-ms) | Full-Stack 개발 | 도서 등록/수정, 마이페이지 |
| [이원근](https://github.com/lwg1421) | Full-Stack 개발 | 도서 검색/목록, 레이아웃 |
