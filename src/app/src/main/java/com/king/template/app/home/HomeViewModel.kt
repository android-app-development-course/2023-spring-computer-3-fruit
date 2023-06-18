package com.king.template.app.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.king.template.app.base.BaseModel
import com.king.template.app.base.BaseViewModel
import com.king.template.bean.BannerBean
import com.king.template.bean.Bean
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.math.log

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
@HiltViewModel
class HomeViewModel @Inject constructor(application: Application, model: BaseModel?) : BaseViewModel(application, model){


    val liveDataBanner by lazy { MutableLiveData<List<BannerBean>>()}

    val liveData by lazy { MutableLiveData<MutableList<Bean>>()}

    fun getRequestBanner(){
        launch(false) {
            // 广告位图片
            val data = arrayOf(
                "",
                "",
                "",
                ""
            )
            delay(1000)
            liveDataBanner.value = data.map { BannerBean(it) }
        }
    }

    fun getRequestData(curPage: Int,pageSize : Int){
        // TODO 模拟请求
        launch(false) {
            var array = arrayOf("苹果", "梨", "香蕉", "待添加水果")
            var cards = arrayOf("苹果是蔷薇科苹果亚科苹果属植物，其树为落叶乔木。苹果的果实富含矿物质和维生素，是经常食用的水果之一",
                "梨，蔷薇科梨属乔木植物，树冠开展；小枝粗壮，幼时有柔毛：二年生的枝紫褐色，具稀疏皮孔；托叶膜质，边缘具腺齿；叶片卵形或椭圆形",
                "香蕉为芭蕉科植物甘蕉的果实，果肉香甜，香蕉营养高、热量低，具有降低血压、缓解抽筋、改善便秘等功效",
                "待添加水果介绍")

            /*
            var start = (curPage - 1) * pageSize + 1
            var end = (curPage) * pageSize
            if(curPage > 1){
                end -= pageSize / 2
            }
            */
            var start = 0
            var end = 3
            var data = ArrayList<Bean>()
            var fruits = arrayOf("apple_image","pear_image","banana_image","logo")

            for(index in start..end){
                var bean = Bean()
                with(bean){
                    //title = "列表模板标题示例$index"
                    title = array[index]
                    //content = "列表模板内容示例$index"
                    content = cards[index]
                    imageUrl = fruits[index]
                    //Log.d("TAG",fruits[index])
                }
                data.add(bean)
            }
            delay(1000)
            liveData.value = data
        }
    }

}