import com.nitro.stanford_ner.ner.EntityExtraction
import edu.stanford.nlp.ie.crf.CRFClassifier
import org.scalatest.{Matchers, WordSpec}
import org.jsoup.Jsoup
import org.jsoup.nodes.{ Node, Element, Comment, Document }
import scala.collection.JavaConverters._

class ModelSpec extends WordSpec with Matchers {

  val stopTags = Set("style", "script", "[document]", "head", "title", "option", "input")

  "Stanford's Pre-trained models" should {

    "find the correct named entities" in {

      val text = "Sarah went to work at the FBI"
//      val html = scala.io.Source.fromFile("privacy.html")
//      val doc = Jsoup.parse(html.getLines.mkString("\n"))
//      val elements = doc.getElementsByTag("p").asScala.filter(hasUsableBodyText)
//      //println(elements)
//      val text1 = elements.map(_.ownText().trim).filter( _ != "").mkString("\n")
//      println(text1)
//
//      val myCRF = CRFClassifier.getClassifier("stanford_models/english.all.3class.distsim.crf.ser.gz")
//
//      val entities = EntityExtraction.extract(myCRF, text)
//      val entities1 = EntityExtraction.extract(myCRF, text1)
//
//      val entityPredictions1 = entities1
//        .flatMap(_.EntityTokens.map(_.entity))
//      println(entityPredictions1)
//
//      val entityPredictions = entities
//        .flatMap(_.EntityTokens.map(_.entity))
//
//      entityPredictions should equal(Seq("PERSON", "O", "O", "O", "O", "O", "ORGANIZATION"))

    }

  }

  private def hasUsableBodyText(node: Node): Boolean = {
    node match {
        case el: Comment => false
        case el: Element => !stopTags.contains(el.tagName().toLowerCase) && el.ownText().trim != ""
    }
  }

}
