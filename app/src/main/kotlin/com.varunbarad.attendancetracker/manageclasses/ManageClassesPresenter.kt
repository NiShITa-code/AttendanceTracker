package com.varunbarad.attendancetracker.manageclasses

import com.varunbarad.attendancetracker.data.model.Class
import com.varunbarad.attendancetracker.data.repository.ClassesRepository

/**
 * Created by Varun Barad on 05-08-2017.
 */
class ManageClassesPresenter(val view: ManageClassesContract.View, val classesRepository: ClassesRepository) : ManageClassesContract.Presenter {
    override fun loadClasses() {
        val classes = classesRepository.getClasses()

        if (classes.isEmpty()) {
            view.showNoClasses()
        } else {
            view.showClassList(classes)
        }
    }

    override fun addClassButtonClicked() {
        view.showAddClassActivity()
    }

    override fun deleteClass(index: Int, classes: List<Class>) {
        val (newClasses, isFoundAndDeleted) = classesRepository.deleteClass(classes[index].id)

        if (isFoundAndDeleted) {
            if (newClasses.isEmpty()) {
                view.showNoClasses()
            } else {
                view.deleteClass(index, newClasses)
            }
        }
    }

    override fun resetClass(index: Int, classes: List<Class>) {
        val (newClasses, isFoundAndReplaced) = classesRepository.replace(classes[index].resetAttendance())

        if (isFoundAndReplaced) {
            view.resetClass(index, newClasses)
        }
    }
}