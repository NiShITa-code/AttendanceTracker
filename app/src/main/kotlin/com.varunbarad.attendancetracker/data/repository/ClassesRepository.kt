package com.varunbarad.attendancetracker.data.repository

import com.varunbarad.attendancetracker.data.model.Class

/**
 * Created by Varun Barad on 05-08-2017.
 */
interface ClassesRepository {
    fun getClasses(): List<Class>
    fun addClass(name: String, requiredAttendance: Double): Pair<List<Class>, Class>
    fun deleteClass(id: Int): Pair<List<Class>, Boolean>
    fun replace(data: Class): Pair<List<Class>, Boolean>
}