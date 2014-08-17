package spider

class SpiderConsumer {

    def fromQueue(){
       var message = SpiderHandler.take()
        while (message != "DONE")
        {
          System.out.format("MESSAGE RECEIVED: %s%n", message)
          message = SpiderHandler.take()
          Thread.sleep(1000l);
        }
        SpiderHandler.stop()
      }
   
}
