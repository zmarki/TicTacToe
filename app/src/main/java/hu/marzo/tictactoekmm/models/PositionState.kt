package hu.marzo.tictactoekmm.models

import androidx.annotation.DrawableRes
import hu.marzo.tictactoekmm.R

sealed class PositionState(@DrawableRes val res: Int) {
    object Cross: PositionState(R.drawable.ic_close)
    object Circle: PositionState(R.drawable.ic_circle)
    object Empty: PositionState(R.drawable.ic_baseline_crop_free_24)
}
