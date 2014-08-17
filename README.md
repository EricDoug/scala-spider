The purpose of this project is to provide a spider to access web page. 
the code for test is in the SpiderHandler.scala

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

