package net.yakuraion.mangakko.core_uikit.search

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * [TextWatcher] при котором [action] срабатывает спустя [DEFAULT_DEBOUNCE_PERIOD] миллисекунд
 * после изменения текста
 */
class DebouncingTextWatcher(
    lifecycle: Lifecycle,
    private val action: (String) -> Unit
) : TextWatcher {

    var debouncePeriod: Long = DEFAULT_DEBOUNCE_PERIOD

    private val coroutineScope = lifecycle.coroutineScope

    private var searchJob: Job? = null

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // empty
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        searchJob?.cancel()
        searchJob = coroutineScope.launch {
            delay(debouncePeriod)
            s?.let { action.invoke(it.toString()) }
        }
    }

    override fun afterTextChanged(s: Editable?) {
        // empty
    }

    companion object {

        private const val DEFAULT_DEBOUNCE_PERIOD = 1000L
    }
}
