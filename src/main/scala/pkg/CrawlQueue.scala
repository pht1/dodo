package pkg

class CrawlQueue {

  var toGo = Set[String]()
  var done = Set[String]()

  def pull = {
    val url = toGo.head
    done = done + url
    toGo = toGo - url
    url
  }

  def push(url : String) = { if (!done.contains(url)) toGo = toGo + url }

}
