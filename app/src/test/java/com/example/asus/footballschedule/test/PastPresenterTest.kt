package com.example.asus.footballschedule.test

import com.example.asus.footballschedule.model.ScheduleResponse
import com.example.asus.footballschedule.next.NextPresenter
import com.example.asus.footballschedule.past.PastPresenter
import com.example.asus.footballschedule.past.PastView
import org.junit.Before
import org.junit.Test
import org.mockito.*
import retrofit2.Call
import retrofit2.Callback

class PastPresenterTest{
    @Mock
    private lateinit var view : PastView

    @Mock
    private lateinit var mScheduleResponse: Call<ScheduleResponse>

    @Captor
    private lateinit var mArgumentCaptor : ArgumentCaptor<Callback<ScheduleResponse>>

    @InjectMocks
    private lateinit var presenter: PastPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = PastPresenter(view)
    }

    /**
     *
     * Skenario Unit Test PastPresenter
     * 1. Memastikan method "showLoading" pada view dijalankan
     * 2. Memastikan bahwa request data dari API telah dijalankan, entah sukses ataupun tidak
     */
    @Test
    fun getPastSchedulingTest(){

        presenter.setScheduleResponse(mScheduleResponse)
        presenter.getPastSchedule()
        Mockito.verify(view).showLoading()
        Mockito.verify(mScheduleResponse).enqueue(mArgumentCaptor.capture())
    }
}