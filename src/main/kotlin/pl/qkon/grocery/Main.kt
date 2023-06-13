package pl.qkon.grocery

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import java.util.concurrent.CountDownLatch

@SpringBootApplication
@ConfigurationPropertiesScan
open class Main {
  companion object {

    private fun handleSpringGracefulClose() {
      val keepOnRunning = CountDownLatch(1)
      Runtime.getRuntime().addShutdownHook(object : Thread() {
        override fun run() {
          keepOnRunning.countDown()
        }
      })
      keepOnRunning.await()
    }

    @JvmStatic
    fun main(args: Array<String>) {
      runApplication<Main>(*args)
      handleSpringGracefulClose()
    }
  }
}
