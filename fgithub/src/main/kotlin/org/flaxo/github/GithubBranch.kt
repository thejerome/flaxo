package org.flaxo.github

import org.flaxo.core.env.BinaryEnvironmentFile
import org.flaxo.core.env.EnvironmentFile
import org.flaxo.core.env.RemoteEnvironmentFile
import org.flaxo.git.Branch
import org.kohsuke.github.GitHub as KohsukeGithub

/**
 * Github repository branch class.
 */
class GithubBranch(override val name: String,
                   override val repository: GithubRepository,
                   private val github: Github
) : Branch {

    private val client: KohsukeGithub = github.client

    override fun commit(file: EnvironmentFile): Branch =
            commit(file.name, file)

    override fun commit(filePath: String,
                        file: EnvironmentFile
    ): Branch = also { branch ->
        when (file) {
            is BinaryEnvironmentFile -> client.repository(repository.name)
                    .createContent(
                            file.binaryContent(),
                            "feat: Add $filePath",
                            filePath,
                            branch.name
                    )
            else -> client.repository(repository.name)
                    .createContent(
                            file.content(),
                            "feat: Add $filePath",
                            filePath,
                            branch.name
                    )
        }
    }

    override fun update(file: EnvironmentFile): Branch =
            update(file.name, file)

    override fun update(filePath: String,
                        file: EnvironmentFile
    ): Branch = also { branch ->
        when (file) {
            is BinaryEnvironmentFile -> client.repository(repository.name)
                    .getFileContent(filePath, branch.name)
                    .update(
                            file.content(),
                            "feat: Update $filePath",
                            branch.name
                    )
            else -> client.repository(repository.name)
                    .getFileContent(filePath, branch.name)
                    .update(
                            file.binaryContent(),
                            "feat: Update $filePath",
                            branch.name
                    )
        }
    }

    override fun createSubBranch(subBranchName: String): Branch {
        val rootBranchName = name

        client.repository(repository.name).apply {
            getBranch(rootBranchName).shA1.also {
                createBranch(subBranchName, it)
            }
        }

        return GithubBranch(subBranchName, repository, github)
    }


    override fun createSubBranches(count: Int, prefix: String): Branch = also {
        (1..count).map { prefix + it }
                .forEach { createSubBranch(it) }
    }

    override fun files(): List<EnvironmentFile> =
            client.repository(repository.name)
                    .getTreeRecursive(name, 1)
                    .tree
                    .filter { it.type == "blob" }
                    .map { RemoteEnvironmentFile(it.path, it.readAsBlob()) }

}