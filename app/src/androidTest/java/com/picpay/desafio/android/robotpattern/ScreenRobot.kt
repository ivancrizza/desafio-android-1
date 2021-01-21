package com.picpay.desafio.android.robotpattern

import android.content.Context
import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.picpay.desafio.android.PicPayAPI
import com.picpay.desafio.android.R
import com.picpay.desafio.android.robotutils.RobotUtils
import com.picpay.desafio.android.view.MainActivity
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import java.io.IOException
import java.io.InputStream

class ScreenRobot(
    private val mockServer: MockWebServer,
    private val activityScenarioRule: ActivityTestRule<MainActivity>
) {
    fun setRequest(): ScreenRobot {
        mockServer.dispatcher = object : Dispatcher() {

            override fun dispatch(request: RecordedRequest): MockResponse {
                if (request.path?.contains(PicPayAPI.USERS) == true) {
                    return MockResponse().setBody(
                        readFileFromAssets(
                            "response_body.json",
                            InstrumentationRegistry.getInstrumentation().context
                        )
                    )
                }
                return MockResponse().setBody(
                    readFileFromAssets(
                        "error_not_found.json",
                        InstrumentationRegistry.getInstrumentation().context
                    )
                )
            }

        }

        return this
    }

    fun checkScroll(): ScreenRobot {
        RobotUtils.scroolTheLastPositionOfRecyclerView(
            activityScenarioRule.activity,
            R.id.recyclerView
        )
        RobotUtils.isScroll(R.id.recyclerView, 8)
        RobotUtils.checkIfTextIsDisplayedOnRecyclerView(R.id.recyclerView, 13, "Vitor Lins")
        return this
    }

    fun checkIfIsDisplayedRecyclerView(): ScreenRobot {
        RobotUtils.isRecyclerViewVisible(R.id.recyclerView)
        return this
    }

    fun finishActivity() {
        activityScenarioRule.finishActivity()
    }

    fun startActivity(): ScreenRobot {
        activityScenarioRule.launchActivity(Intent(Intent.ACTION_MAIN))
        return this
    }

    private fun readFileFromAssets(name: String, context: Context): String {
        return try {
            val file: InputStream = context.assets.open(name)
            val size: Int = file.available()
            val buffer = ByteArray(size)
            file.read(buffer)
            file.close()
            String(buffer)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }


}
