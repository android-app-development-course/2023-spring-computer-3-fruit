package com.king.template.app.home

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.king.android.ktx.fragment.argument
import com.king.template.R
import com.king.template.app.account.Temp
import com.king.template.app.base.ListFragment
import com.king.template.bean.ImgMenu
import com.king.template.databinding.MenuListFragmentBinding
import com.king.template.dict.MenuType
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
@AndroidEntryPoint
class MenuListFragment : ListFragment<ImgMenu, MenuListViewModel, MenuListFragmentBinding>() {

    private val menuList by lazy {
        val list: MutableList<ImgMenu> = ArrayList()
        list.add(ImgMenu("营养成分"))
        list.add(ImgMenu(MenuType.MENU_1, "介绍", R.drawable.ic_menu_list_1))
        list.add(ImgMenu(MenuType.MENU_2, "记录", R.drawable.ic_menu_list_2))
        list.add(ImgMenu("待实现"))
        list.add(ImgMenu(MenuType.MENU_3, "待实现", R.drawable.ic_menu_list_3))
        list
    }


    private var text by argument<String>()

    private var showToolbar by argument(defaultValue = true)


    companion object {
        fun newInstance(text: String, showToolbar: Boolean = true): MenuListFragment {
            return MenuListFragment().apply {
                this.text = text
                this.showToolbar = showToolbar
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.menu_list_fragment
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        binding.toolbar.toolbar.isVisible = showToolbar
        binding.toolbar.tvTitle.text = text
    }


    override fun initRecyclerView(rv: RecyclerView) {
        rv.layoutManager = GridLayoutManager(requireContext(), 4)

    }

    override fun initRefreshLayout(srl: SmartRefreshLayout) {
        srl.setEnableRefresh(isSupportRefresh())
        srl.setEnableLoadMore(false)
        srl.setOnRefreshListener { requestData(1) }
        srl.setOnLoadMoreListener { requestData(curPage) }

    }

    override fun isSupportRefresh(): Boolean {
        return false
    }


    override fun createAdapter(): BaseQuickAdapter<ImgMenu, BaseViewHolder> {
        return object : BaseSectionQuickAdapter<ImgMenu, BaseViewHolder>(
            R.layout.rv_img_menu_head_item,
            R.layout.rv_img_menu_item,
            menuList
        ) {
            override fun convert(holder: BaseViewHolder, item: ImgMenu) {
                holder.setText(R.id.tv, item.name)
                holder.setImageResource(R.id.iv, item.resId)
            }

            override fun convertHeader(helper: BaseViewHolder, item: ImgMenu) {
                helper.setText(R.id.tv, item.name)
            }

        }
    }

    override fun smartRefreshLayout(): SmartRefreshLayout {
        return binding.srl
    }

    override fun recyclerView(): RecyclerView {
        return binding.rv
    }

    private fun clickCal(){
        startActivity(Temp::class.java)
    }

    private fun clickNut(){
        startActivity(Nutrition::class.java)
    }


    override fun clickItem(view: View, position: Int) {
        super.clickItem(view, position)
        val data = mAdapter.getItem(position)
        when (data.menuType) {
            MenuType.HEAD -> {}
            // TODO 点击菜单示例
            MenuType.MENU_1 -> clickNut()
            MenuType.MENU_2 -> clickCal()
           //待实现  MenuType.MENU_3 ->
        }
    }


}