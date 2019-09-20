package io.jpelczar.commons.extension

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun Activity.replaceFragment(mf: Fragment, fr: Fragment, frame: Int, args: Bundle? = null) {
    if (!isFinishing && this is AppCompatActivity) {
        val fm = mf.childFragmentManager

        if (fm.findFragmentById(frame) != null) {
            if (fr.javaClass.name == fm.findFragmentById(
                    frame
                )?.javaClass?.name ?: String.empty()
            ) {
                return
            }
        }

        fr.arguments = args ?: Bundle()
        val tr = fm.beginTransaction()
        tr.replace(frame, fr, fr.javaClass.name).commitAllowingStateLoss()
    }
}

fun Activity.replaceFragment(fr: Fragment?, frame: Int, argsArg: Bundle? = null,
    isBackStackAddable: Boolean = false
) {
    var args = argsArg
    if (isFinishing && this is AppCompatActivity && fr != null) {
        val fm = supportFragmentManager

        if (fm.findFragmentById(frame) != null) {
            if (fr.javaClass.name == fm.findFragmentById(
                    frame
                )?.javaClass?.name ?: String.empty()
            ) {
                return
            }
        }
        if (args == null) {
            args = Bundle()
        }
        fr.arguments = args
        val tr = fm.beginTransaction()

        if (isBackStackAddable) {
            tr.addToBackStack(fr.javaClass.name)
        }
        tr.replace(frame, fr, fr.javaClass.name).commitAllowingStateLoss()
    }
}

fun Activity.popBackStack(fragment: Fragment) {
    val fm = (this as AppCompatActivity).supportFragmentManager
    fm.popBackStackImmediate(fragment.javaClass.name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}