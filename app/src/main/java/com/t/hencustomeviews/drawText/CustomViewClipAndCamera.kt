package com.t.hencustomeviews.drawText

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.drawable.toBitmap
import com.t.hencustomeviews.R
import com.t.hencustomeviews.dp

private val PADDING = 100.dp
private val IMAGE_WIDTH = 200.dp.toInt()

class CustomViewClipAndCamera @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val drawable = resources.getDrawable(R.mipmap.img_avatar)
    private val circlePath = Path().apply {
        addOval(PADDING, PADDING, PADDING + IMAGE_WIDTH, PADDING + IMAGE_WIDTH, Path.Direction.CCW)
    }


    private val camera = Camera()
    override fun onDraw(canvas: Canvas) {
        do4(canvas)
    }

    //clip and drawBitmap
    private fun do1(canvas: Canvas) {
        canvas.clipPath(circlePath)
        canvas.drawBitmap(
            drawable.toBitmap(IMAGE_WIDTH, IMAGE_WIDTH, Bitmap.Config.ARGB_8888),
            PADDING,
            PADDING,
            paint
        )
    }

    //translate and rotate
    /**
     * 反过来理解画布上绘制的内容
     * 先画一个图片
     * 把图片沿着0，0旋转30°
     * 然后x方向平移200f
     *
     */
    private fun do2(canvas: Canvas) {
        for (i in 1..10) {
            canvas.drawLine(i * 100f, 0f, i * 100f, height.toFloat(), paint)
            canvas.drawLine(0f, 100f * i, width.toFloat(), 100f * i, paint)
        }
        canvas.drawBitmap(
            drawable.toBitmap(IMAGE_WIDTH, IMAGE_WIDTH, Bitmap.Config.ARGB_8888),
            PADDING,
            PADDING,
            paint
        )
        canvas.translate(200f, 0f)
        canvas.drawBitmap(
            drawable.toBitmap(IMAGE_WIDTH, IMAGE_WIDTH, Bitmap.Config.ARGB_8888),
            PADDING,
            PADDING,
            paint
        )
        canvas.rotate(30f)
        canvas.drawBitmap(
            drawable.toBitmap(IMAGE_WIDTH, IMAGE_WIDTH, Bitmap.Config.ARGB_8888),
            PADDING,
            PADDING,
            paint
        )
    }


    /**
     * 页面投影
     * z轴－方向上一个相机放在那里往下照
     * 画图
     * 图中心移动到左上
     * 投影
     * 再移动回来
     */
    private fun do3(canvas: Canvas) {
        canvas.translate(PADDING + IMAGE_WIDTH / 2, PADDING + IMAGE_WIDTH / 2)
        camera.rotateX(30f)
        camera.applyToCanvas(canvas)
        canvas.translate(-(PADDING + IMAGE_WIDTH / 2), -(PADDING + IMAGE_WIDTH / 2))
        canvas.drawBitmap(
            drawable.toBitmap(IMAGE_WIDTH, IMAGE_WIDTH, Bitmap.Config.ARGB_8888),
            PADDING,
            PADDING,
            paint
        )
    }

    //下半部分翻起来
    private fun do4(canvas: Canvas) {

        //上半部分
        canvas.save()
        canvas.translate(PADDING + IMAGE_WIDTH / 2, PADDING + IMAGE_WIDTH / 2)
        canvas.rotate(-30f)
        canvas.clipRect(-IMAGE_WIDTH , -IMAGE_WIDTH , IMAGE_WIDTH , 0)
        canvas.rotate(30f)
        canvas.translate(-(PADDING + IMAGE_WIDTH / 2), -(PADDING + IMAGE_WIDTH / 2))
        canvas.drawBitmap(
            drawable.toBitmap(IMAGE_WIDTH, IMAGE_WIDTH, Bitmap.Config.ARGB_8888),
            PADDING,
            PADDING,
            paint
        )
        canvas.restore()


        //下半部分
        canvas.save()
        canvas.translate(PADDING + IMAGE_WIDTH / 2, PADDING + IMAGE_WIDTH / 2)
        canvas.rotate(-30f)
        camera.rotateX(30f)
        camera.setLocation(0f, 0f, -resources.displayMetrics.density * 6)
        camera.applyToCanvas(canvas)
        canvas.clipRect(-IMAGE_WIDTH , 0, IMAGE_WIDTH , IMAGE_WIDTH )
        canvas.rotate(30f)
        canvas.translate(-(PADDING + IMAGE_WIDTH / 2), -(PADDING + IMAGE_WIDTH / 2))
        canvas.drawBitmap(
            drawable.toBitmap(IMAGE_WIDTH, IMAGE_WIDTH, Bitmap.Config.ARGB_8888),
            PADDING,
            PADDING,
            paint
        )
        canvas.restore()
    }

}