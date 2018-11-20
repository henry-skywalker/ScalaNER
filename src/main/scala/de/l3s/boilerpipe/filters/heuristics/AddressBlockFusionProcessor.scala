package de.l3s.boilerpipe.filters.heuristics

import de.l3s.boilerpipe.document.{TextBlock, TextDocument}
import de.l3s.boilerpipe.{BoilerpipeFilter, BoilerpipeProcessingException}
import com.everstring.common.utils.LocationUtils

import scala.collection.JavaConversions._

class AddressBlockFusionProcessor extends BoilerpipeFilter {

  @throws(classOf[BoilerpipeProcessingException])
  def process(doc: TextDocument): Boolean = {
    var changes: Boolean = false

    var textBlocks = doc.getTextBlocks.filter(_.isContent).toList
    //add trailing empty text block to the end of the block list to handle the process of the actual last block
    val emptyTextBlock = new TextBlock("")
    emptyTextBlock.setIsContent(true)
    textBlocks ++= List(emptyTextBlock)

    if(textBlocks.size <= 2)
      changes = false
    else
      textBlocks.sliding(2).foreach(blocks => {
        if(blocks.head.isContent && blocks.last.isContent) {
          val lastBlock = blocks.last.getText.toLowerCase.split(",\\s+")
          if (LocationUtils.abbrevToCountry.exists(c =>
            lastBlock.contains(c._1.toLowerCase) || lastBlock.contains(c._2.toLowerCase)
          ) || LocationUtils.countryAlternateForms.exists(c =>
            lastBlock.contains(c._1.toLowerCase) || lastBlock.contains(c._2.toLowerCase)
          ) || LocationUtils.abbrevToState.exists(s =>
            lastBlock.contains(s._1.toLowerCase) || lastBlock.contains(s._2.toLowerCase)
          ) || lastBlock.exists(b => """\b\d{5}(-\d{4})?\b""".r.findAllIn(b).nonEmpty)
          ) {
            blocks.head.mergeNext(blocks.last)
            blocks.last.setIsContent(false)
            changes = true
          } else {
            blocks.head.setIsContent(false)
            changes = true
          }
        }
      })

    changes
  }

}

object AddressBlockFusionProcessor {
  private val _instance = new AddressBlockFusionProcessor
  def INSTANCE() = _instance
}
