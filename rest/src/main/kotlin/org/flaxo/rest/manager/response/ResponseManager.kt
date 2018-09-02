package org.flaxo.rest.manager.response

import org.springframework.http.ResponseEntity

/**
 * Http response manager.
 *
 * It is just a handy suite that forms a http response entity
 * with the given body and appropriate http response status code.
 */
interface ResponseManager {

    /**
     * Forms a response with status code 200.
     */
    fun ok(body: Any? = null): ResponseEntity<Any>

    /**
     * Forms a response with status code 400.
     */
    fun bad(body: Any? = null): ResponseEntity<Any>

    /**
     * Forms a response with status code 404.
     */
    fun notFound(body: Any? = null): ResponseEntity<Any>

    /**
     * Forms a response with status code 500.
     */
    fun serverError(body: Any? = null): ResponseEntity<Any>

    /**
     * Forms a response with status code 401.
     */
    fun unauthorized(body: Any? = null): ResponseEntity<Any>

    /**
     * Forms a response with status code 403.
     */
    fun forbidden(body: Any? = null): ResponseEntity<Any>

    /**
     * Forms a response with status code 404 and course not found message.
     */
    fun courseNotFound(username: String, courseName: String): ResponseEntity<Any>

    /**
     * Forms a response with status code 404 and user not found message.
     */
    fun userNotFound(username: String): ResponseEntity<Any>

    /**
     * Forms a response with status code 400 and github token absences message.
     */
    fun githubTokenNotFound(username: String): ResponseEntity<Any>

    /**
     * Forms a response with status code 404 and github id not found message.
     */
    fun githubIdNotFound(username: String): ResponseEntity<Any>

    /**
     * Forms a response with status code 404 and course task not found message.
     */
    fun taskNotFound(username: String,
                     courseName: String,
                     taskBranch: String
    ): ResponseEntity<Any>
}