package com.thiago.myapp

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    // Scenario with longer periods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (viewModel.isReady) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )
        viewModel.doSomeWork()
    }

    // Scenario with animation
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val splashScreen = installSplashScreen()
//        setContentView(R.layout.activity_main)
//
//        splashScreen.setOnExitAnimationListener { splashScreenView ->
//            val slideUpAnim = ObjectAnimator.ofFloat(
//                splashScreenView.view,
//                View.TRANSLATION_Y,
//                0f,
//                -splashScreenView.view.height.toFloat()
//            ).apply {
//                interpolator = AnticipateInterpolator()
//                duration = 600L
//                doOnEnd { splashScreenView.remove() }
//            }
//            slideUpAnim.start()
//        }
//    }
}