package com.example.atype.data.api

import com.google.gson.annotations.SerializedName

data class ImageSearchResponse(
    @SerializedName("meta")// 해당 데이터의 이름을 바꿔주는 코드
    val metaData: MetaData?,

    @SerializedName("documents")
    var documents: MutableList<KakaoImage>?
)

data class KakaoImage(
    val collection	: String,
    val thumbnail_url : String,
    val image_url : String,
    val  width	:Integer,
    val height : Integer,
    @SerializedName("display_sitename")
    val sitename : String,
    val doc_url : String,
    val datetime : String
)
//collection	String	컬렉션
//thumbnail_url	String	미리보기 이미지 URL
//image_url	String	이미지 URL
//width	Integer	이미지의 가로 길이
//height	Integer	이미지의 세로 길이
//display_sitename	String	출처
//doc_url	String	문서 URL
//datetime	Datetime	문서 작성시간, ISO 8601
//[YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz]
data class MetaData(
    @SerializedName("total_count")
    val totalCount: Int?,
    @SerializedName("is_end")
    val isEnd: Boolean?,
    @SerializedName("pageable_count")
    val pagecount: Boolean?,
)
//
//total_count	Integer	검색된 문서 수
//pageable_count	Integer	total_count 중 노출 가능 문서 수
//is_end	Boolean	현재 페이지가 마지막 페이지인지 여부, 값이 false면 page를 증가시켜 다음 페이지를 요청할 수 있음