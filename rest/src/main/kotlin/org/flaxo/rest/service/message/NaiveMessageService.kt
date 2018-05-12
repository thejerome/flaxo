package org.flaxo.rest.service.message

import org.springframework.context.MessageSource
import java.util.*
import kotlin.NoSuchElementException

/**
 * Basic localized message service implementation.
 */
class NaiveMessageService(
        private val messageSource: MessageSource
) : MessageService {

    override fun get(code: String,
                     vararg params: String,
                     locale: Locale
    ): String {
        return try {
            messageSource.getMessage(code, params, locale)
        } catch (e: NoSuchElementException) {
            "No message provided"
        }
    }
}