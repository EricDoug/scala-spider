package spider

class SpiderProducer {
    def inQueue(url:String){
      SpiderHandler.put(url) 
    }
}
