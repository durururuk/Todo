# 주특기 기초주간 과제 API 명세서

일정 추가

http Method : ‘POST’

URL : “/api/todos”

설명 : 새로운 일정을 추가합니다.

Request Body

```json
{
    "username" : "담당자명",
    "password" : "비밀번호",
    "contents" : "일정 내용"
}
```

Response

```json
{
    "contents": "일정 내용",
    "username": "담당자명",
    "date": "YYYY-MM-DD",
    "id": (int)id,
    "message": "등록 성공"
}
```

일정 단건 조회

http Method : ‘GET’

URL : “/api/todos”

설명 : ID로 일정을 단건 조회합니다. 

Request Body 

```json
{
    "id" : (int)id
}
```

Response

```json
{
    "contents": "일정 내용",
    "username": "담당자명",
    "date": "YYYY-MM-DD",
    "id": (int)id,
    "message": 조회 성공
}
```

일정 다건 조회

http Method : ‘GET’

URL : “/api/todos”

설명 : 담당자명, 작성/수정 일자로 일정을 조회합니다.  둘 중 하나만 있는 경우, 둘 다 없는 경우, 둘 다 있는 경우 모두 조회가 가능합니다.

Response Body

```json
{
    "username" : "담당자명", 
    "date" : "YYYY-MM-DD"
}
```

Response

```json
[
    {
        "contents": "일정 내용",
        "username": "담당자명",
        "date": "YYYY-MM-DD",
        "id": (int)id,
        "message": "조회 성공"
    },
    {
        "contents": "일정 내용",
        "username": "담당자명",
        "date": "YYYY-MM-DD",
        "id": (int)id,
        "message": "조회 성공"
    },
    {
        "contents": "일정 내용",
        "username": "담당자명",
        "date": "YYYY-MM-DD",
        "id": (int)id,
        "message": "조회 성공"
    }
]
```

일정 수정

http Method : ‘PUT’

URL : “/api/todos”

설명 : 비밀번호를 확인하고 선택한 일정의 담당자명, 일정 내용을 수정합니다.

Request Body 

```json
{
    "id" : (int)id,
    "password" : "비밀번호",
    "username" : "수정할 담당자명",
    "contents" : "수정할 일정 내용"
}
```

Response

```json
{
    "contents": "수정된 일정 내용",
    "username": "수정된 담당자명",
    "date": "YYYY-MM-DD"(수정날짜),
    "id": (int)id,
    "message": "수정 성공"
}
```

일정 삭제

http Method : ‘DELETE’

URL : “/api/todos”

설명 : 비밀번호를 확인하고 선택한 일정을 삭제합니다.

Request Body

```json
{
    "id" : (int)id,
    "password" : "1231"
}
```

Response

```json
{
    "contents": null,
    "username": null,
    "date": "YYYY-MM-DD",
    "id": (int)id,
    "message": "삭제 성공"
}
```

ERD

![image](https://github.com/user-attachments/assets/a207dc8f-2f0e-4bb3-9400-842c7ceb1c56)

