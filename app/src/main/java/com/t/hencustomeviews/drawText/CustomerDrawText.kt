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
        canvas.drawText("center text", width / 2f, height / 2f + getBaseline(paint), paint)

        paint.style = Paint.Style.STROKE
        canvas.drawCircle(width / 2f, height / 2f, 40f, paint)
        canvas.drawCircle(width / 2f, height / 2f, 100f, paint)
    }


    /**
     * 计算绘制文字时的基线到中轴线的距离
     *
     * @param p
     * @param centerY
     * @return 基线和centerY的距离
     */
    private fun getBaseline(p: Paint): Float {
        val fontMetrics: Paint.FontMetrics = p.fontMetrics
//        return (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.descent
        return (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent
    }
}