# Short-URL-Service 최종본

### 프로젝트 개발자 : 김선호

프로젝트 전체 과정에 대한 기록 : https://sunh0-history.tistory.com/2

**프로젝트 설계, 개발, 리펙토링** 총 세개의 포스팅으로 작성하였습니다.


### :clipboard: 요구 사항
1. URL 입력폼 제공 및 결과 출력
2. URL Shortening Key는 8 Character 이내로 생성
3. 단축된 URL 요청시 원래 URL로 리다이렉트
4. 단축된 URL에 대한 요청 수 정보저장

### :clipboard: 리펙토링
1. Index를 통한 조회 성능 향상 시도
2. Spring Batch 와 Scheduler를 통한 오래된 데이터 삭제

