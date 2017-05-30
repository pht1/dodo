
package pkg

import java.net.URL

import sys.process._

class PageCrawler(url: String, needle: String) {

  def crawl : CrawlResult = {
    val html = s"curl $url".lineStream_!.mkString("\n")
    val matches = html.contains(needle)
    val linkregex = """<a href="([^"]+)"""".r
    val urls = linkregex.findAllMatchIn(html).map { _.group(1) }.map(absolute).filter(acceptable).toSet
    CrawlResult(matches, urls)
  }

  private def absolute(u : String) = if (hasProtocol(u)) u else new URL(new URL(url),u).toString   

  private def acceptable(u : String) = hasAcceptableProtocol(u)

  private def hasProtocol(u: String) = u.startsWith("http:") || u.startsWith("https:") || u.startsWith("javascript:") || u.startsWith("mailto:")
  private def hasAcceptableProtocol(u: String) = u.startsWith("http:") || u.startsWith("https:")

}

case class CrawlResult(matches: Boolean, links:Set[String])
