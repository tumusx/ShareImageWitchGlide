package murillo.silva.myapplication.enumclass

import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.MetricAffectingSpan


open class SpannableClass(private val font: Typeface?) : MetricAffectingSpan() {


    companion object {
        const val WRONG_TYPEFACE = 0
    }

    override fun updateDrawState(textPaint: TextPaint) = updateTypeface(textPaint)

    override fun updateMeasureState(textPaint: TextPaint) = updateTypeface(textPaint)


    fun updateTypeface(textPaint: TextPaint) {
        textPaint.apply {
            val oldStyle = getOldStyle(typeface)
            if (oldStyle == WRONG_TYPEFACE) return
            typeface = Typeface.create(font, oldStyle)
        }
    }


    private fun getOldStyle(typeface: Typeface?) = typeface?.style ?: WRONG_TYPEFACE


}