package de.l3s.boilerpipe.filters.simple

import de.l3s.boilerpipe.{BoilerpipeFilter, BoilerpipeProcessingException}
import de.l3s.boilerpipe.document.TextDocument

import scala.collection.JavaConversions._

class MaxWordsFilter(maxWords: Int) extends BoilerpipeFilter {

  @throws(classOf[BoilerpipeProcessingException])
  def process(doc: TextDocument): Boolean = {
    var changes: Boolean = false
    doc.getTextBlocks.toList.foreach{
      tb => {
        if (tb.isContent && tb.getNumWords() > maxWords) {
          tb.setIsContent(false)
          changes = true
        }
      }
    }
    changes
  }
}
