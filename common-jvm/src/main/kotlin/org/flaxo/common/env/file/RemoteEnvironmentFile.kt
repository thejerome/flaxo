package org.flaxo.common.env.file

import java.io.InputStream
import java.nio.file.Path

/**
 * Remote environment file.
 */
class RemoteEnvironmentFile(

        override val path: Path,

        /**
         * Remote file input stream.
         */
        private val inputStream: InputStream
) : EnvironmentFile {

    override val binaryContent: ByteArray by lazy { inputStream.use { it.readBytes() } }

    override fun toLocalFile(directory: Path): LocalFile = LazyLocalEnvironmentFile(path, directory, inputStream)

}
