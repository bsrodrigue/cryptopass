package bf.rodo.birthdayapp

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    private val _items = mutableStateListOf<PasswordItem>()

    val items: List<PasswordItem> = _items

    fun addItem(item: PasswordItem) {
        _items.add(item)
    }

    fun setItems(items: List<PasswordItem>){
        _items.clear()
        _items.addAll(items)
    }
}