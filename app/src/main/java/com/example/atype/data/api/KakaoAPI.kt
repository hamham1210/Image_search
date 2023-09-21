package com.example.atype.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface KakaoAPI {
    @GET("v2/search/image")
   fun getSearchImage(
        @Header("Authorization") apiKey: String,
        @Query("query") query: String,
        @Query("sort") sort: String ,
        @Query("page") page: Int = 1,
        @Query("size") size: Int =30,
    ): Call<ImageSearchResponse?>?
}
//Authorization	Authorization: KakaoAK ${REST_API_KEY}
//query	String	검색을 원하는 질의어	O
//sort	String	결과 문서 정렬 방식, accuracy(정확도순) 또는 recency(최신순), 기본 값 accuracy	X
//page	Integer	결과 페이지 번호, 1~50 사이의 값, 기본 값 1	X
//size	Integer	한 페이지에 보여질 문서 수, 1~80 사이의 값, 기본 값 80	X

