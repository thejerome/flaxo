package com.tcibinan.flaxo.core.env

/**
 * In-memory environment file implementation.
 */
class SimpleEnvironmentFile(
        private val name: String,
        private val content: String
) : EnvironmentFile {

    override fun name() = name

    override fun content() = content

    override fun binaryContent() = content().toByteArray()

    override fun with(path: String): EnvironmentFile =
            SimpleEnvironmentFile(path, content)

}