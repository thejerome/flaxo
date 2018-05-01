package org.flaxo.git

import org.flaxo.core.env.EnvironmentFile

/**
 * Git service provider client.
 *
 * Client is associated with a single user.
 */
interface Git {

    /**
     * @return git service user nickname.
     */
    fun nickname(): String

    /**
     * Creates a git repository for the current user.
     *
     * @param repositoryName Git service repository name.
     * @param private Type of the repository private/public to create *(public by default)*.
     * @return Repository of the current user with [repositoryName] and [private] type.
     */
    fun createRepository(repositoryName: String,
                         private: Boolean = false
    ): Repository

    /**
     * Completely deletes a repository from the git service.
     *
     * @param repositoryName To be deleted.
     */
    fun deleteRepository(repositoryName: String)

    /**
     * Retrieves [repositoryName] pull request by [pullRequestNumber].
     *
     * @param repositoryName Git repository name.
     * @param pullRequestNumber Git pull request identifier.
     */
    fun getPullRequest(repositoryName: String,
                       pullRequestNumber: Int
    ): PullRequest

    /**
     * Forks existing repository of [ownerNickname] user with [repositoryName].
     *
     * @return Forked repository.
     */
    fun forkRepository(ownerNickname: String,
                       repositoryName: String
    ): Repository

    /**
     * Returns a [repositoryName] repository of a current user.
     *
     * @return Repository of current user with [repositoryName].
     */
    fun getRepository(repositoryName: String): Repository

    /**
     * Returns a [repositoryName] repository of [ownerName] user.
     *
     * @return Repository of [ownerName] user with [repositoryName].
     */
    fun getRepository(ownerName: String,
                      repositoryName: String
    ): Repository

}