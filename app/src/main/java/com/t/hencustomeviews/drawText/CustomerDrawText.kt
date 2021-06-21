package com.t.hencustomeviews.drawText

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 *   @ClassName: CustomeDrawText
 *   @Date: 2021/6/21 21:47
 *   @Author: syz
 *   @Description:
 */
class CustomerDrawText(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 100f
        color = Color.RED
        style = Paint.Style.FILL
    }


    override fun onDraw(canvas: Canvas) {
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText("center text", width / 2f, height / 2f, paint)
    }
}