package pl.qkon

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.test.assertEquals

class DummyClassTest {

  @Test
  fun dummyTest() {
    val random = UUID.randomUUID().toString()
    val random2 = UUID.randomUUID().toString()
    assertAll(
      { assertEquals(random, DummyClass(random).x) },
      { assertEquals(random2, DummyClass(random2).x) }
    )
  }

  @ParameterizedTest
  @EnumSource(DummyEnum::class)
  fun `dummyTest2`(dummyEnum: DummyEnum) {
    assertEquals(dummyEnum.name, DummyClass(dummyEnum.name).x)
  }

  @Test
  fun noExceptionThrown() {
    assertDoesNotThrow { "cc" }
  }

  @Test
  fun dummyTestCor() {
    val count = 1000
    val sum = AtomicInteger(0)

    runBlocking {
      val jobs = List(count) {
        launch { sum.incrementAndGet() }
      }
      jobs.forEach {
        it.join()
      }
    }
    assertEquals(count, sum.get())
  }
}
