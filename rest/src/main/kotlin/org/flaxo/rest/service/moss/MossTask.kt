package org.flaxo.rest.service.moss

import org.flaxo.core.env.EnvironmentFile

/**
 * Moss analysis task.
 */
data class MossTask(val taskName: String,
                    val base: List<EnvironmentFile>,
                    val solutions: List<EnvironmentFile>
)