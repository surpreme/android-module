package com.valy.acountlibrary.net

import io.reactivex.Flowable
import okhttp3.ResponseBody
import retrofit2.http.*

/**

 * @Auther: valy

 * @datetime: 2020/3/18

 * @desc:

 */
interface RetrofitAcountInterfaces {
    @POST("index.php?act=login&op=index")
    @FormUrlEncoded
    fun logIn(
            @Field("username") username: String,
            @Field("password") password: String,
            @Field("client") client: String
    ): Flowable<ResponseBody>
    @GET("index.php?act=member_message&op=message")
    fun getCommonMsg(
            @Query("curpage") curpage: Int,
            @Query("key") key: String,
            @Query("lang_type") lang_type: String
    ): Flowable<ResponseBody>
}