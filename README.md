# Short-URL-Service

### :triangular_flag_on_post: About
- url 단축 서비스 미션 프로젝트 입니다.

### :clipboard: 요구 사항
1. URL 입력폼 제공 및 결과 출력
2. URL Shortening Key는 8 Character 이내로 생성
3. 단축된 URL 요청시 원래 URL로 리다이렉트
4. 단축된 URL에 대한 요청 수 정보저장 (optional)
5. Shortening Key를 생성하는 알고리즘 2개 이상 제공하며 애플리케이션 실행중 동적으로 변경 가능 (optional)

### :bulb: Short URL의 특징
단축 URL서비스는 간편하지만, 단점(위험성)이 있습니다.

링크를 클릭하는 사용자는 단축된 URL만 보고 클릭하기 때문에 어떤 곳으로 이동할지 알 수 없습니다.

- Short URL 서비스는 주로 요청을 Redirect 시킵니다. (Redirect와 Forward의 차이점에 대해 알아보세요.)
- 긴 URL을 짧은 URL로 압축할 수 있다.
- short url만으로는 어디에 연결되어있는 지 알 수 없다. 때문에 피싱 사이트 등의 보안에 취약하다.
- 광고를 본 뒤에 원본url로 넘겨주기도 한다. 이 과정에서 악성 광고가 나올 수 있다.
- 당연하지만 이미 존재하는 키를 입력하여 들어오는 사람이 존재할 수 있다.
- 기존의 원본 URL 변경되었더라도 단축 URL을 유지하여, 혼란을 방지할 수 있다.

### :mag_right: 예시 사이트
https://url.kr/
