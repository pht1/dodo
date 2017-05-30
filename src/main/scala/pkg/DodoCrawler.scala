package pkg

class DodoCrawler(seed:String, whitelist:String, needle:String) {

  private val queue = new CrawlQueue

  def run = {
    var result = Set[String]()
    queue.push(seed)

    while(queue.toGo.nonEmpty) {
      println(queue.toGo)
      val url = queue.pull
      val r = new PageCrawler(url, needle).crawl
      r.links.filter(_.startsWith(whitelist)).foreach(queue.push)
      if (r.matches) result = result + url
    }

    result

  }

}
