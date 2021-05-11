package ru.voodster.weatherstation

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.TextView

class WeatherTV : androidx.appcompat.widget.AppCompatTextView {
    constructor(context: Context) : super(context){
        init()
    }
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    {
        init()
    }

    private fun init() {
        this.background.alpha = 64
        this.background.setTint(Color.BLACK)
        this.setTextColor(Color.RED)
    }
}