package com.varunbarad.attendancetracker

import com.varunbarad.attendancetracker.data.model.Class
import com.varunbarad.attendancetracker.data.repository.ClassesRepository
import com.varunbarad.attendancetracker.manageclasses.ManageClassesContract
import com.varunbarad.attendancetracker.manageclasses.ManageClassesPresenter
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Varun Barad on 05-08-2017.
 */

@RunWith(MockitoJUnitRunner::class)
class ManageClassesPresenterTest {
    val classesRepository: ClassesRepository = Mockito.mock(ClassesRepository::class.java)
    val view: ManageClassesContract.View = Mockito.mock(ManageClassesContract.View::class.java)
    val presenter: ManageClassesContract.Presenter = ManageClassesPresenter(view, classesRepository)

    @Test
    fun loadEmptyClasses() {
        Mockito.`when`(classesRepository.getClasses()).thenReturn(emptyList())

        presenter.loadClasses()

        Mockito.verify(view).showNoClasses()
    }

    @Test
    fun loadNonEmptyClasses() {
        val classes = listOf(Class(1, "Physics", 50.0, 0, 0), Class(2, "Chemistry", 60.0, 3, 0))
        Mockito.`when`(classesRepository.getClasses()).thenReturn(classes)

        presenter.loadClasses()

        Mockito.verify(view).showClassList(classes)
    }

    @Test
    fun dialogShownOnClickingAddClassButton() {
        presenter.addClassButtonClicked()

        Mockito.verify(view).showAddClassActivity()
    }

    @Test
    fun removeClassFromSingleElementList() {
        Mockito.`when`(classesRepository.deleteClass(1)).thenReturn(Pair(emptyList(), true))

        presenter.deleteClass(0, listOf(Class(1, "Physics", 50.0, 3, 4)))

        Mockito.verify(view).showNoClasses()
    }

    @Test
    fun removeClassFromFilledList() {
        val classes = listOf(Class(1, "Physics", 50.0, 0, 0), Class(2, "Chemistry", 60.0, 3, 0))
        val newClasses = listOf(Class(1, "Physics", 50.0, 0, 0))
        Mockito.`when`(classesRepository.deleteClass(2)).thenReturn(Pair(newClasses, true))

        presenter.deleteClass(1, classes)

        Mockito.verify(view).deleteClass(1, newClasses)
    }

    @Test
    fun resetClass() {
        val physicsClass = Class(1, "Physics", 50.0, 0, 0)
        val chemistryClass = Class(2, "Chemistry", 60.0, 3, 0)
        val classes = listOf(physicsClass, chemistryClass)
        val newClasses = listOf(physicsClass, Class(2, "Chemistry", 60.0, 0, 0))

        Mockito.`when`(classesRepository.replace(chemistryClass.resetAttendance())).thenReturn(Pair(newClasses, true))

        presenter.resetClass(1, classes)

        Mockito.verify(view).resetClass(1, newClasses)
    }
}