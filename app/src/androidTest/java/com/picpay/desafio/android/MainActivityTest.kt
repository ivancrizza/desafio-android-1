package com.picpay.desafio.android

import androidx.test.rule.ActivityTestRule
import com.picpay.desafio.android.robotpattern.ScreenRobot
import com.picpay.desafio.android.view.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainActivityTest {

    private var server = MockWebServer()
    private lateinit var screenRobot: ScreenRobot

    @get:Rule
    var activityScenario = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setup() {
        screenRobot = ScreenRobot(server, activityScenario)
    }

    @After
    fun tearDown() {
        server.shutdown()
        screenRobot.finishActivity()
    }

    @Test
    fun shouldScrollItem() {
        screenRobot.setRequest()
            .startActivity()
            .checkScroll()
    }


    @Test
    fun shouldDisplayListItem() {
        screenRobot.setRequest()
            .startActivity()
            .checkIfIsDisplayedRecyclerView()
    }
}
