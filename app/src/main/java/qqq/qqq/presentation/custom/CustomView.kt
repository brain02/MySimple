package qqq.qqq.presentation.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import qqq.qqq.R
import kotlin.math.pow

const val SIZE_DEFAULT = 100

class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    /**Квадрат*/
    private val mRectSquare = Rect()
    private val mPaintSquare = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mSquareColor: Int = 0
    private var mSquareSize: Int = SIZE_DEFAULT

    /**Круг*/
    private val mPaintCircle = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mCircleX: Float = 0f
    private var mCircleY: Float = 0f
    private var mCircleRadius: Float = 100f

    private var foregroundColor = Color.WHITE

    private var mImage: Bitmap? = null

    /**Конструктор*/
    init {
        val getAttrs =
            getContext().obtainStyledAttributes(attrs, R.styleable.CustomView)
        mSquareColor =
            getAttrs.getColor(R.styleable.CustomView_square_color, Color.RED)
        mSquareSize =
            getAttrs.getDimensionPixelSize(R.styleable.CustomView_square_size, SIZE_DEFAULT)

        mPaintSquare.color = mSquareColor
        mPaintCircle.color = mSquareColor
        mCircleRadius = mSquareSize.toFloat() / 2

        mImage = BitmapFactory.decodeResource(resources, R.drawable.image)
        getAttrs.recycle()
    }


    /**Метод смена цвета по событию*/
    fun swapColor() {
        mPaintSquare.color = Color.YELLOW.takeIf { mPaintSquare.color == Color.RED } ?: Color.RED
        mPaintCircle.color = Color.YELLOW.takeIf { mPaintSquare.color == Color.RED } ?: Color.GREEN
        postInvalidate()
    }

    /**Метод установки фонового цвета*/
    fun foregroundColor(color: Int) {
        foregroundColor = color
    }

    /**Рисуем*/
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mRectSquare.apply {
            left = 50
            top = 50
            right = left + mSquareSize
            bottom = top + mSquareSize
        }

        if (mCircleX == 0f || mCircleY == 0f) {
            mCircleX = width.toFloat() - mCircleRadius - 50
            mCircleY = mCircleRadius + 50
        }

        canvas?.drawColor(foregroundColor)
        canvas?.drawRect(mRectSquare, mPaintSquare)
        canvas?.drawCircle(mCircleX, mCircleY, mCircleRadius, mPaintCircle)

        canvas?.apply {
            drawLines(floatArrayOf(150f,150f,300f,300f), mPaintCircle)
            drawLines(floatArrayOf(300f,300f,150f,300f), mPaintCircle)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val value = super.onTouchEvent(event)
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                val x = event.x
                val y = event.y

                if (mRectSquare.left < x && mRectSquare.right > x) {
                    if (mRectSquare.top < y && mRectSquare.bottom > y) {
                        mCircleRadius += 20
                        postInvalidate()
                    }
                }
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                val x = event.x
                val y = event.y
                val dx = (x - mCircleX).pow(2f)
                val dy = (y - mCircleY).pow(2f)

                if (dx + dy < mCircleRadius.pow(2f)) {
                    mCircleX = x
                    mCircleY = y
                    postInvalidate()
                    return true
                }
                return value
            }
        }
        return value
    }

}