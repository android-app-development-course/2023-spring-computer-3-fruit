package com.king.template.dict

import androidx.annotation.IntDef


/**
 * @author <a href="mailto:jenly1314@gmail.com">Jenly</a>
 */
@IntDef(
    MenuType.HEAD,
    MenuType.MENU_1,
    MenuType.MENU_2,
    MenuType.MENU_3,
)
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
@kotlin.annotation.Retention(AnnotationRetention.SOURCE)
annotation class MenuType {

    companion object {
        /**
         * 头
         */
        const val HEAD = 0

        /**
         * 菜单1
         */
        const val MENU_1 = 1

        /**
         * 菜单2
         */
        const val MENU_2 = 2
        /**
         * 菜单3
         */
        const val MENU_3 = 3
    }
}