package pkg

import org.scalatest.FunSuite
import org.junit.Assert._

class DodoTest extends FunSuite {

  test("it crawls one web page") {
    val crawler = new PageCrawler("http://www.kovarik-revize-praha.cz/", "Lhůty")
    val result = crawler.crawl
    assertEquals("matches", true, result.matches)
    assertEquals("number of links", 18, result.links.size)
    assertEquals("has a known link", true, result.links.contains("http://www.kovarik-revize-praha.cz/?kontakt,15"))
  }

  test("it manages crawl queue") {
    val queue = new CrawlQueue
    queue.push("http://aaa")
    assertEquals("one item in queue", Set("http://aaa"), queue.toGo)
    assertEquals("pull an item", "http://aaa", queue.pull)
    assertEquals("visited links", Set("http://aaa"), queue.done)
    queue.push("http://bbb")
    queue.push("http://aaa")
    assertEquals("only unique items in queue", Set("http://bbb"), queue.toGo)
  }

  test("it manages crawlers") {
    val crawler = new DodoCrawler("http://www.kovarik-revize-praha.cz/", "http://www.kovarik-revize-praha.cz", "Lhůty")
    val result = crawler.run
    assertEquals(Set("http://www.kovarik-revize-praha.cz/"), result)
  }

}
