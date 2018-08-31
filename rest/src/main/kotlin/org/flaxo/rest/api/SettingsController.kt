package org.flaxo.rest.api

import org.flaxo.core.lang.Language
import org.flaxo.model.LanguageView
import org.flaxo.rest.manager.response.ResponseManager
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Flaxo settings controller.
 */
@RestController
@RequestMapping("/rest/settings")
class SettingsController(private val responseManager: ResponseManager,
                         private val supportedLanguages: Map<String, Language>
) {

    /**
     * Returns a list of supported languages by flaxo.
     */
    @GetMapping("/languages")
    fun supportedLanguages(): ResponseEntity<Any> =
            supportedLanguages
                    .map { (name, language) ->
                        LanguageView(
                                name = name,
                                compatibleTestingLanguages = language.compatibleTestingLanguages.map { it.name },
                                compatibleTestingFrameworks = language.compatibleTestingFrameworks.map { it.name }
                        )
                    }
                    .let { responseManager.ok(it) }
}