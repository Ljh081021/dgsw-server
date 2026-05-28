application.yml은 중요한 정보가 탈취될 위험이 있어 클래스룸에 함께 올려두었습니다. main/resources에 넣어서 사용하시면 될 것 같습니다. swagger 주소는 http://localhost:8080/swagger-ui/index.html#/ 해당 주소로 테스트를 진행했습니다. 테스트를 위해 정보를 .sql파일에다 넣어두었고 특별한 행동을 추가로 하실 필요는 없으시지만 테스트 과정에서 대부분의 테스트들이 수행하기 수월하지만 딱 한가지 기능이 테스트하기 어려우실 것 같아 예시를 하나 드리고자 합니다. 게시물들을 가져올때 사용자의 현재 위치, 화면의 크기, 확대 정도에 따라서 화면에 드러나 있는 게시물들만 들고 오면 충분하다고 생각했습니다. 때문에 latitude: 37.5665
longitude: 126.9780
screenWidth: 390
screenHeight: 844
zoomLevel: 11 이렇게 값을 입력해서 확인해보시면 거리 계산을 하고 [
{
"id": 1,
"title": "조용한 북카페",
"latitude": 37.5665,
"longitude": 126.978,
"writer": 1,
"likeNum": 10,
"created_at": "2026-05-01T00:00:00"
},
{
"id": 2,
"title": "한강 산책로",
"latitude": 37.5283,
"longitude": 126.9341,
"writer": 1,
"likeNum": 25,
"created_at": "2026-05-02T00:00:00"
},
{
"id": 6,
"title": "서울숲 새벽산책",
"latitude": 37.5444,
"longitude": 127.0374,
"writer": 2,
"likeNum": 40,
"created_at": "2026-05-06T00:00:00"
},
{
"id": 9,
"title": "경복궁 야경",
"latitude": 37.5796,
"longitude": 126.977,
"writer": 1,
"likeNum": 35,
"created_at": "2026-05-09T00:00:00"
}
] 이러한 정보가 나오게 될 것입니다.