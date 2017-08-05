package com.varunbarad.attendancetracker.manageclasses

import com.varunbarad.attendancetracker.data.model.Class

/**
 * Created by Varun Barad on 03-08-2017.
 */
interface ManageClassesContract {
    interface Presenter {
        fun loadClasses()
        fun addClassButtonClicked()
        fun deleteClass(index: Int, classes: List<Class>)
        fun resetClass(index: Int, classes: List<Class>)
    }

    interface View {
        fun showAddClassActivity()
        fun showClassList(classes: List<Class>)
        fun showNoClasses()
        fun deleteClass(index: Int, updatedClasses: List<Class>)
        fun resetClass(index: Int, updatedClasses: List<Class>)
    }
}