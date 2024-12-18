# WinterTechProject
**레디스, 카프카, 도커, ec2, postgresql 과 같은 기술 사용에 집중해 보는 프로젝트**

| **주간 Workflow**

* (월요일 오후) 1차 작업 pr(각자의 main branch에 대해)
* (수요일 밤) 작업 pr에 대해 후에 기한 수요일 밤까지 review 진행하기
* (금요일 오전) review 받은 코드에 대해 리팩토링 후 2차 pr
* (금요일 오전) 금요일 아침에 보면서 review + merge

---
### 기본 설정
시작 레포에서 fork
main branch는 두고 "이름-n주차 [기술명]" branch를 하나 만들어서 작업

ERD
 - ![ERD_IMG]() 각자 ERD 짜서 이미지 넣기


CRUD
 - ![실시간 검색어 랭킹 api](./img/searchranking-api.png)

---

### 비고
  - 공부한 게 오래 남기 위해서는 발생한 에러에 대해 에러 메시지와 해결법을 정리해두면 좋다.
  - 코드 리뷰를 할 때는 궁금한 점 자유롭게 묻고 상대방에게 그렇게 짠 이유를 묻기(나도 항상 왜 이렇게 짰는 지 생각하면서 코딩하기)
  - commit convention [노션](https://bow-snail-89d.notion.site/Convention-8763cd0df1174421be5fcaae6090444e)
  - formatter 세팅 [레포](https://github.com/naver/hackday-conventions-java/tree/master) 저장 시 포맷 할거면 바뀐 부분만 적용 할 수 있음 참고(Intellij 유료 버전만 가능...)
