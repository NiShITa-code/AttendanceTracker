package com.varunbarad.attendancetracker.data.model

/**
 * Created by Varun Barad on 02-08-2017.
 */
data class Class(val id: Int, val name: String, val lecturesRequired: Double, val lecturesAttended: Int = 0, val lecturesSkipped: Int = 0) {
    fun increaseLecturesAttended(): Class {
        return Class(id, name, lecturesRequired, (lecturesAttended + 1), lecturesSkipped)
    }

    fun decreaseLecturesAttended(): Class {
        if (lecturesAttended > 0) {
            return Class(id, name, lecturesRequired, (lecturesAttended - 1), lecturesSkipped)
        } else {
            return this
        }
    }

    fun increaseLecturesSkipped(): Class {
        return Class(id, name, lecturesRequired, lecturesAttended, (lecturesSkipped + 1))
    }

    fun decreaseLecturesSkipped(): Class {
        if (lecturesSkipped > 0) {
            return Class(id, name, lecturesRequired, lecturesAttended, (lecturesSkipped - 1))
        } else {
            return this
        }
    }

    fun getNumberOfLecturesRequiredToAttend(): Int {
        return Math.floor((lecturesAttended.toDouble() - (lecturesAttended.toDouble() * lecturesRequired) - (lecturesSkipped.toDouble() * lecturesRequired)) / (lecturesRequired - 1.0)).toInt()
    }

    fun resetAttendance(): Class {
        return Class(id, name, lecturesRequired, 0, 0)
    }
}