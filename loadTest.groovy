@Grab(group='org.codehaus.gpars', module='gpars', version='1.2.1')
import static groovyx.gpars.GParsPool.withPool

withPool(50){
    (1..2000).eachParallel { i ->
    	def beginTime = System.currentTimeMillis()
        def result = 'https://dncpcllkd0.execute-api.us-east-1.amazonaws.com/dev/users'.toURL().text
       	println "${i}: ${System.currentTimeMillis() - beginTime}"
    }
}