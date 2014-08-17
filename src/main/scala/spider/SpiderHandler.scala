package spider
import scala.collection.mutable.Queue
import scala.actors.Actor._
import scala.concurrent.ops.spawn
import scalaj.http.HttpOptions
import scalaj.http.Http
class SpiderHandler{
  
}

object SpiderHandler {
      val q = Queue[String]()
      private case class Put(x: String)
      private case object Take
      private case object Stop
  
      private val buffer =
        actor
        {
          loop
          {
            react
            {
              case Put(x) =>
                q.enqueue(x);
                reply()
              case Take  =>
                if(!q.isEmpty){
                val u = q.dequeue
                val r = req(u)
                reply(r)
            }else{
              reply("q is empty")
            }
              case Stop =>
                reply(); exit("stopped")
            }
          }
        }

      def put(x: String) { buffer !? Put(x) }
      def take() : String = (buffer !? Take).asInstanceOf[String]
      def stop() { buffer !? Stop }
      def req(url:String) :String={
        val options = List(HttpOptions.connTimeout(1234),HttpOptions.readTimeout(1234))
        var req = Http(url).charset("UTF-8").options(options)
	    req.asString
      }
      
      def main(args:Array[String]):Unit={
        import concurrent.ops._
        
        val tp = new SpiderProducer()
        val tc = new SpiderConsumer()
        spawn{
          val urls : Array[String] = Array(
          "http://www.baidu.com",
          "http://www.jd.com",
          "http://www.sina.com",
          "http://www.sohu.com"
        );
         urls.foreach((url)=>tp.inQueue(url))
        }
        
        spawn{
          tc.fromQueue()
        }
      }
}