package ru.kode.base.internship.ui.carddetails.states

sealed class ListState {
  data object History : ListState()
  data object Actions : ListState()
  data object Payments : ListState()
}
