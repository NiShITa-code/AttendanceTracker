package com.varunbarad.attendancetracker.util

/**
 * Created by Varun Barad on 03-08-2017.
 */

fun <E> List<E>.replace(index: Int, newElement: E): List<E> {
    return this.take(index) + newElement + this.drop(index + 1)
}