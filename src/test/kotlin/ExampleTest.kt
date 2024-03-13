import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.BenchmarkMode
import org.openjdk.jmh.annotations.Mode
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.RunnerException
import org.openjdk.jmh.runner.options.Options
import org.openjdk.jmh.runner.options.OptionsBuilder
import kotlin.test.Test


class ExampleTest {
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Throws(InterruptedException::class)
    fun doExection() {
        Thread.sleep(1000)
    }

    @Test
    fun main() {
        val opt: Options = OptionsBuilder()
            .include(ExampleTest::class.java.getSimpleName())
            .forks(2)
            .build()
        Runner(opt).run()
    }
}