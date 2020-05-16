package com.valy.acountlibrary.net

import com.valy.baselibrary.net.RetrofitClients


/**

 * @Auther: valy

 * @datetime: 2020/3/20
 工厂
 * @desc:

 */
 object RetrofitAcountFactory {

    fun build(): RetrofitAcountInterfaces {
        return RetrofitClients.getInstance().retrofit.create(RetrofitAcountInterfaces::class.java)
    }

}