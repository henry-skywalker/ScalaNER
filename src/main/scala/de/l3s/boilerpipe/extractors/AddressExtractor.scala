package de.l3s.boilerpipe.extractors

import de.l3s.boilerpipe.BoilerpipeProcessingException
import de.l3s.boilerpipe.document.TextDocument
import de.l3s.boilerpipe.filters.heuristics.AddressBlockFusionProcessor
import de.l3s.boilerpipe.filters.simple.MarkEverythingContentFilter
import de.l3s.boilerpipe.filters.simple.MinWordsFilter
import de.l3s.boilerpipe.filters.simple.MaxWordsFilter
//import de.l3s.boilerpipe.filters.english.AddressRulesClassifier

class AddressExtractor extends ExtractorBase {

  @throws(classOf[BoilerpipeProcessingException])
  def process(doc: TextDocument): Boolean = {

    MarkEverythingContentFilter.INSTANCE.process(doc) |
      new MaxWordsFilter(10).process(doc) |
      new MinWordsFilter(3).process(doc) |
      AddressBlockFusionProcessor.INSTANCE.process(doc)
//      | AddressRulesClassifier.INSTANCE.process(doc)
  }
}
