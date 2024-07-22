# SSAFY12th-algo-study
### 2024.07.31~
### 알고리즘 마스터가 되는 그날까지 함께 열심히 달려보아요~~!

## 업로드 규칙
### 파일 명은 '문제 번호'

### Commit Convention
업로드 날짜-이름
## Git 기본 명령어

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

* 새로운 branch에 push - add, commit 후
```
git push origin feature_branch
```

* push 후 git hub에서 PR 생성
