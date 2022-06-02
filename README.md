# 📑 Paging3 Example
<br>  

## 📷 스크린샷
<img src="https://user-images.githubusercontent.com/79048895/171569763-94cea983-5496-40d7-b59b-90390695fa32.jpg" width="180" height="390" /> <img src="https://user-images.githubusercontent.com/79048895/171569770-fd50135b-c223-4b24-bac7-ffc65adecd8e.jpg" width="180" height="390" /> <img src="https://user-images.githubusercontent.com/79048895/171569771-7983ce77-2217-47cd-bf91-70edf9724c9f.jpg" width="180" height="390" /> <img src="https://user-images.githubusercontent.com/79048895/171569775-6b013fec-2950-4356-8b27-c18119fbecb9.jpg" width="180" height="390" />
<br>
<br>

## 📝 프로젝트 소개
### 해당 프로젝트는 Paging3의 실습 예제입니다.

`Unsplash API`를 이용해 간단히 이미지를 불러오는 앱입니다.
추가적으로 키워드 검색을 통해 이미지를 확인할 수도 있습니다.
이미지를 불러올 때 `Paging3`를 이용해 효율적으로 이미지를 
페이징하여 로드하는게 이 앱의 특징입니다.

사용된 기술스택은 아래와 같습니다.
- `Kotlin`
- `ViewModel` / `LiveData`
- `Coroutine`
- `Room`
- `Retrofit2`
- `Hilt`
- `Paging3`
- `Glide`
<br>

## ✍ 느낀점
앱 개발을 하다보면 수많은 데이터들을 사용자에게 보여줘야할 때가 많습니다.  
그럴때마다 수많은 데이터를 효율적으로 사용해야 데이터를 UI로 적용되는 과정에서   
문제없이 사용자에게 보여질 수 있습니다.  
그래서 사용하는 것이 바로 페이징 기법인 `Paging3`입니다.  
이번 실습 예제를 통해 `Paging3`에 대해 간단하게 남아 이해할 수 있었습니다.  
다만 `Paging3`자체가 복잡한 부분이 많아 `MVVM`패턴에 대한 이해가 필수적이고,  
네트워킹을 통한 `PagingSource`를 이용하거나, 로컬 DB 캐싱을 이용한 `RemoteMediator`     
이용하는 방식의 차이도 있기 때문에 아직까지는 공부가 더 많이 필요하다고 느꼈습니다..😔  
이외에도 예제에서는 `LiveData`로 적용했지만 `Flow`를 이용한 방법도 있기 때문에  
추후에 `Flow`에 대해 공부한 뒤, 다시 한번 `Paging3`에 대해 공부해야겠다고 생각했습니다.  
