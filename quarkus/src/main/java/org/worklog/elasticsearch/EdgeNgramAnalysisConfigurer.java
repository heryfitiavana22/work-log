package org.worklog.elasticsearch;

import org.hibernate.search.backend.elasticsearch.analysis.ElasticsearchAnalysisConfigurationContext;
import org.hibernate.search.backend.elasticsearch.analysis.ElasticsearchAnalysisConfigurer;

import io.quarkus.hibernate.search.standalone.elasticsearch.SearchExtension;

@SearchExtension
public class EdgeNgramAnalysisConfigurer implements ElasticsearchAnalysisConfigurer {
	@Override
	public void configure(ElasticsearchAnalysisConfigurationContext context) {
		context.analyzer("name").custom()
				.tokenizer("standard")
				.tokenFilters("asciifolding", "lowercase");

		context.analyzer("english").custom()
				.tokenizer("standard")
				.tokenFilters("asciifolding", "lowercase", "porter_stem");

		context.analyzer("edge_ngram_analyzer")
				.custom()
				.tokenizer("standard")
				.tokenFilters("lowercase", "edge_ngram");

		context.tokenFilter("edge_ngram")
				.type("edge_ngram")
				.param("min_gram", "2")
				.param("max_gram", "15");

	}

}
