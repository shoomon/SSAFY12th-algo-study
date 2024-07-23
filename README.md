#  :star2: SSAFY12th-algo-study :star2:
### 2024.07.31~
#### 알고리즘 마스터가 되는 그날까지 함께 열심히 달려보아요~~! :runner: 
### 스터디원 소개 :technologist:
<br/>
<br/>

<table>
  <tr>
  <td align="center"><a href="https://github.com/shoomon"><img src="https://avatars.githubusercontent.com/u/96561442?v=4" width="100px;" alt=""/><br />
      <img src="http://mazassumnida.wtf/api/mini/generate_badge?boj=kate010117" widt="100px">
      <br /><sub><b>김수민</b></sub></a><br /><sub><b>소개</b><br>MBTI</br></sub></td>
    
  <td align="center"><a href="https://github.com/bangmq"><img src="https://avatars.githubusercontent.com/u/87954515?v=4" width="100px;" alt=""/><br />
      <img src="http://mazassumnida.wtf/api/mini/generate_badge?boj=catbirdseat" widt="100px">
      <br /><sub><b>방민규</b></sub></a><br /><sub><b>소개</b><br>MBTI</br></sub></td>
    
  <td align="center"><a href="https://github.com/Dobee-Kim"><img src="https://avatars.githubusercontent.com/u/68512221?v=4" width="100px;" alt=""/><br />
      <img src="http://mazassumnida.wtf/api/mini/generate_badge?boj=catbirdseat" widt="100px">
      <br /><sub><b>김도희</b></sub></a><br /><sub><b>소개</b><br>MBTI</br></sub></td>

  <td align="center"><a href="https://github.com/hyun0zin"><img src="https://avatars.githubusercontent.com/u/154870548?v=4" width="100px;" alt=""/><br />
      <img src="http://mazassumnida.wtf/api/mini/generate_badge?boj=catbirdseat" widt="100px">
      <br /><sub><b>김현진</b></sub></a><br /><sub><b>소개</b><br>MBTI</br></sub></td>
  </tr>
  <tr>
  <td align="center"><a href="https://github.com/Maxwithjude"><img src="https://avatars.githubusercontent.com/u/141596763?v=4" width="100px;" alt=""/><br />
      <img src="http://mazassumnida.wtf/api/mini/generate_badge?boj=catbirdseat" widt="100px">
      <br /><sub><b>이동현</b></sub></a><br /><sub><b>소개</b><br>ISTP</br></sub></td>

  <td align="center"><a href="https://github.com/mmmmingb"><img src="https://avatars.githubusercontent.com/u/175905209?v=4" width="100px;" alt=""/><br />
      <img src="http://mazassumnida.wtf/api/mini/generate_badge?boj=catbirdseat" widt="100px">
      <br /><sub><b>박민경</b></sub></a><br /><sub><b>소개</b><br>MBTI</br></sub></td>

  <td align="center"><a href="https://github.com/PlutoWooSeok"><img src="https://avatars.githubusercontent.com/u/108509935?v=4" width="100px;" alt=""/><br />
      <img src="http://mazassumnida.wtf/api/mini/generate_badge?boj=catbirdseat" widt="100px">
      <br /><sub><b>송우석</b></sub></a><br /><sub><b>소개</b><br>MBTI</br></sub></td>

  <td align="center"><a href="https://github.com/bangmq"><img src="https://avatars.githubusercontent.com/u/87954515?v=4" width="100px;" alt=""/><br />
      <img src="http://mazassumnida.wtf/api/mini/generate_badge?boj=catbirdseat" widt="100px">
      <br /><sub><b>이미림</b></sub></a><br /><sub><b>소개</b><br>MBTI</br></sub></td>
  </tr>
</table><br/>


<br/>
<br/>

---


## 업로드 규칙 :book:
### 파일 명 :page_facing_up:
#### 'BOJ' or 'SWEA' + '문제 번호'

### Commit Convention :label:
#### 업로드 날짜-이름

---

## 학습 가이드 :pencil:
### 필수 학습 :fire:
* 큐, 덱, 스택, 힙, 해시 등 자료구조를 사용한 알고리즘
* 이분탐색
* 누적합, 에라토스테네스의 체
* 구현, 시뮬레이션
* DFS, BFS
* 백트래킹, DP

### 학습 권장  :exclamation:
* 최단거리: 다익스트라, 플로이드-와샬
* MST: 유니온 파인드, 크루스칼, 프림
* 수열: LCS, LIS

### 추가 학습 :white_check_mark:
* 비트마스킹
* 세그먼트 트리

---

## Git 기본 명령어 :computer: 

### 로컬 폴더 - 깃 연결
* git으로 관리할 가장 상위 폴더 위치에 연결
```bash
git init
```

* 저장소 연결
```bash
git remote add origine 저장소 url
```

### 업로드 시
* workspace 내 모든 파일을 staging 영역으로 업로드
```
git add .
```

* repository에 업로드 전 임시 저장
```
git commit -m "message"
```

* push할 branch 설정
```
git push --set-upstream origin master
```

* repository에 업로드
```
git push origine master
```

### branch 생성 후 업로드 - PR 생성
* branch 생성
```
git checkout -b branch name
```

* 새로운 branch에 push (add, commit 후 수행)
```
git push origin feature_branch
```

* push 후 git hub에서 PR 생성
