package com.example.asus.footballschedule.test

import com.example.asus.footballschedule.model.ScheduleResponse
import com.example.asus.footballschedule.next.NextPresenter
import com.example.asus.footballschedule.next.NextView
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.verify
import retrofit2.Call
import retrofit2.Callback

class NextPresenterTest{
    @Mock
    private lateinit var view : NextView

    @Mock
    private lateinit var mScheduleResponse: Call<ScheduleResponse>

    @Captor
    private lateinit var mArgumentCaptor : ArgumentCaptor<Callback<ScheduleResponse>>

    @InjectMocks
    private lateinit var presenter: NextPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = NextPresenter(view)
    }

    /**
     *
     * Skenario Unit Test NextPresenter
     * 1. Memastikan method "showLoading" pada view dijalankan
     * 2. Memastikan bahwa request data dari API telah dijalankan, entah sukses ataupun tidak
     */
    @Test
    fun getNextSchedulingTest(){

       presenter.setScheduleResponse(mScheduleResponse)
       presenter.getNextSchedule()
       verify(view).showLoading()
       verify(mScheduleResponse).enqueue(mArgumentCaptor.capture())
    }
}