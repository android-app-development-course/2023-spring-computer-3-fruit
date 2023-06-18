package com.king.template.app.me

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.king.template.BuildConfig
import com.king.template.R
import com.king.template.app.Constants
import com.king.template.app.about.AboutActivity
import com.king.template.app.account.ChangePwdActivity
import com.king.template.app.account.Temp
import com.king.template.app.base.BaseFragment
import com.king.template.app.home.Nutrition
import com.king.template.app.home.Service
import com.king.template.databinding.MeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MeFragment : BaseFragment<MeViewModel,MeFragmentBinding>(),View.OnClickListener {

    companion object{
        fun newInstance(): MeFragment{
            return MeFragment()
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)

        updateUI()
        viewDataBinding.tvAppVersion.text = "V ${BuildConfig.VERSION_NAME}"

        // TODO 对应的菜单按钮
        viewDataBinding.rlUser.setOnClickListener(this)
        viewDataBinding.tvMenu1.setOnClickListener(this)
        viewDataBinding.tvMenu2.setOnClickListener(this)
        viewDataBinding.tvMenu3.setOnClickListener(this)
        viewDataBinding.btnAbout.setOnClickListener(this)
    }

    private fun updateUI(){
        // TODO 更新UI显示
        viewDataBinding.tvName.text = Constants.TAG
        viewDataBinding.tvUsername.text = "******"
    }

    override fun getLayoutId(): Int {
        return R.layout.me_fragment
    }

    //-------------------------------

    private fun clickChangePassword(){
        startActivity(ChangePwdActivity::class.java)
    }

    private fun clickUser(){
        // TODO 点击用户信息逻辑
        startLoginActivity()
    }

    private fun clickAbout(){
        startActivity(AboutActivity::class.java)
    }

    private fun clickService(){
        startActivity(Service::class.java)
    }

    override fun onClick(v: View){
        when(v.id){
            R.id.rlUser -> clickUser()
            R.id.btnAbout -> clickAbout()
            R.id.tvMenu1 -> clickChangePassword()
            //  R.id.tvMenu2 -> 待实现
            R.id.tvMenu3 -> clickService()
        }
    }
}