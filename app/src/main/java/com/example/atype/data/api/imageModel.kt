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
    val  width	:Int,
    val height : Int,
    @SerializedName("display_sitename")
    val sitename : String,
    val doc_url : String,
    val datetime : String
)

data class MetaData(
    @SerializedName("total_count")
    val totalCount: Int?,
    @SerializedName("is_end")
    val isEnd: Boolean?,
    @SerializedName("pageable_count")
    val pagecount: Int?,
)
