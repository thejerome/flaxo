package org.flaxo.rest.service.travis

import org.flaxo.cmd.CmdExecutor
import org.flaxo.travis.SimpleTravis
import org.flaxo.travis.Travis
import org.flaxo.travis.TravisClient
import org.flaxo.travis.build.TravisBuild
import org.flaxo.travis.parseTravisWebHook
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.io.Reader

class TravisSimpleService(private val baseUrl: String) : TravisService {

    override fun retrieveTravisToken(githubUsername: String, githubToken: String): String {
        CmdExecutor.execute("travis", "login",
                "-u", githubUsername,
                "-g", githubToken)

        return CmdExecutor.execute("travis", "token")
                .first().split(" ").last()
    }

    override fun travis(travisToken: String): Travis {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build()
                .create(TravisClient::class.java)
                .with(travisToken)
    }

    private fun TravisClient.with(travisToken: String): Travis =
            SimpleTravis(this, travisToken)

    override fun parsePayload(reader: Reader): TravisBuild? =
            parseTravisWebHook(reader)

}