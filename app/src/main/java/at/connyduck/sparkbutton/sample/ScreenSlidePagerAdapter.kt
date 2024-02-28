/* Copyright 2017 Varun, 2018 Conny Duck
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package at.connyduck.sparkbutton.sample

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.viewpager.widget.PagerAdapter
import at.connyduck.sparkbutton.SparkButton

class ScreenSlidePagerAdapter constructor(private val context: Context) : PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View
        when (position) {
            0 -> {
                view = LayoutInflater.from(context).inflate(R.layout.demo_star, container, false)
                setupStarLayoutClickEvents(view)
            }

            1 -> {
                view = LayoutInflater.from(context).inflate(R.layout.demo_heart, container, false)
                setupHeartLayoutClickEvents(view)
            }

            2 -> {
                view =
                    LayoutInflater.from(context).inflate(R.layout.demo_facebook, container, false)
                setupFacebookLayoutClickEvents(view)
            }

            3 -> {
                view = LayoutInflater.from(context).inflate(R.layout.demo_twitter, container, false)
                setupTwitterLayoutClickEvents(view)
            }
            4 -> {
                view =
                    ComposeView(container.context).apply {
                        setContent {
                            ComposeDemo()
                        }
                    }
            }

            else -> throw IllegalStateException()
        }
        view.tag = position.toString()
        container.addView(view)
        return view
    }

    override fun getCount(): Int {
        return 5
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title = ""
        when (position) {
            0 -> title = context.getString(R.string.star)
            1 -> title = context.getString(R.string.heart)
            2 -> title = context.getString(R.string.facebook)
            3 -> title = context.getString(R.string.twitter)
            4 -> title = context.getString(R.string.compose)
        }
        return title
    }

    private fun setupStarLayoutClickEvents(view: View) {
        view.findViewById<View>(R.id.cardview_1)
            .setOnClickListener { view.findViewById<View>(R.id.star_button1).performClick() }
        view.findViewById<View>(R.id.cardview_2)
            .setOnClickListener { view.findViewById<View>(R.id.star_button2).performClick() }
        view.findViewById<View>(R.id.github_page).setOnClickListener { openGithubPage() }
    }

    private fun setupHeartLayoutClickEvents(view: View) {
        view.findViewById<View>(R.id.github_page).setOnClickListener { openGithubPage() }
    }

    private fun setupFacebookLayoutClickEvents(view: View) {
        view.findViewById<View>(R.id.github_page).setOnClickListener { openGithubPage() }
    }

    private fun setupTwitterLayoutClickEvents(view: View) {
        view.findViewById<View>(R.id.github_page).setOnClickListener { openGithubPage() }
        view.findViewById<View>(R.id.twitter_card).setOnClickListener {
            (view.findViewById<View>(R.id.twitter_button) as SparkButton).playAnimation()
            Handler().postDelayed({ openTwitterPage() }, 500)
        }
    }

    private fun openGithubPage() {
        val browserIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("http://www.github.com/connyduck/SparkButton"))
        context.startActivity(browserIntent)
    }

    private fun openTwitterPage() {
        var intent: Intent
        try {
            context.packageManager.getPackageInfo("com.twitter.android", 0)
            intent = Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=517550916"))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        } catch (e: Exception) {
            intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/ConnyDuck"))
        }
        context.startActivity(intent)
    }
}
