package com.example.nytimestask.data.model

import com.google.gson.annotations.SerializedName


data class ArticlesResult(
    val status: String,
    val copyright: String,
    @SerializedName("num_results")
    val numResults: Long,
    val results: List<Article>
)

data class Article(
    val uri: String = "",
    val url: String = "",
    val id: Long = 0,
    @SerializedName("asset_id")
    val assetID: Long = 0,
    val source: String = "",
    @SerializedName("published_date")
    val publishedDate: String = "",
    val updated: String = "",
    val section: String = "",
    val subsection: String = "",
    val nytdsection: String = "",
    @SerializedName("adx_keywords")
    val adxKeywords: String = "",
    val byline: String = "",
    val type: String = "",
    val title: String = "",
    val abstract: String = "",
    @SerializedName("des_facet")
    val desFacet: List<String> = emptyList(),
    @SerializedName("org_facet")
    val orgFacet: List<String> = emptyList(),
    @SerializedName("per_facet")
    val perFacet: List<String> = emptyList(),
    @SerializedName("geo_facet")
    val geoFacet: List<String> = emptyList(),
    val media: List<Media> = emptyList(),
    @SerializedName("eta_id")
    val etaID: Long = 0
)

data class Media(
    val type: String,
    val subtype: String,
    val caption: String,
    val copyright: String,
    @SerializedName("approved_for_syndication")
    val approvedForSyndication: Long,
    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadatum>
)

data class MediaMetadatum(
    val url: String,
    val format: String,
    val height: Long,
    val width: Long
)
