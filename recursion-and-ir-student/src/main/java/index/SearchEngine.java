/*
 * Copyright 2023 Marc Liberatore.
 */

package index;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import comparators.TfIdfComparator;
import documents.DocumentId;

/**
 * A simplified document indexer and search engine.
 * 
 * Documents are added to the engine one-by-one, and uniquely identified by a
 * DocumentId.
 *
 * Documents are internally represented as "terms", which are lowercased
 * versions of each word
 * in the document.
 * 
 * Queries for terms are also made on the lowercased version of the term. Terms
 * are
 * therefore case-insensitive.
 * 
 * Lookups for documents can be done by term, and the most relevant document(s)
 * to a specific term
 * (as computed by tf-idf) can also be retrieved.
 *
 * See:
 * - <https://en.wikipedia.org/wiki/Inverted_index>
 * - <https://en.wikipedia.org/wiki/Search_engine_(computing)>
 * - <https://en.wikipedia.org/wiki/Tf%E2%80%93idf>
 * 
 * @author Marc Liberatore
 *
 */
public class SearchEngine {

	/**
	 * Inserts a document into the search engine for later analysis and retrieval.
	 * 
	 * The document is uniquely identified by a documentId; attempts to re-insert
	 * the same
	 * document are ignored.
	 * 
	 * The document is supplied as a Reader; this method stores the document
	 * contents for
	 * later analysis and retrieval.
	 * 
	 * @param documentId
	 * @param reader
	 * @throws IOException iff the reader throws an exception
	 */

	private final Map<String, Set<DocumentId>> index;
	private final Map<DocumentId, Map<String, Integer>> termCounts;

	public SearchEngine() {
		index = new HashMap<>();
		termCounts = new HashMap<>();
	}

	public void addDocument(DocumentId documentId, Reader reader) throws IOException {
		// ignore re-insertions of the same document
		if (termCounts.containsKey(documentId)) {
			return;
		}

		Map<String, Integer> termCount = new HashMap<>();

		Scanner s = new Scanner(reader);
		s.useDelimiter("\\W+");
		while (s.hasNext()) {
			String term = s.next().toLowerCase();
			termCount.put(term, termCount.getOrDefault(term, 0) + 1);
			index.computeIfAbsent(term, k -> new HashSet<>()).add(documentId);
		}

		// store even if the document is empty, so the ID is "known"
		termCounts.put(documentId, termCount);
	}

	/**
	 * Returns the set of DocumentIds contained within the search engine that
	 * contain a given term.
	 * 
	 * @param term
	 * @return the set of DocumentIds that contain a given term
	 */
	public Set<DocumentId> indexLookup(String term) {
		return index.getOrDefault(term, Set.of());
	}

	/**
	 * Returns the term frequency of a term in a particular document.
	 * 
	 * The term frequency is number of times the term appears in a document.
	 * 
	 * See
	 * 
	 * @param documentId
	 * @param term
	 * @return the term frequency of a term in a particular document
	 * @throws IllegalArgumentException if the documentId has not been added to the
	 *                                  engine
	 */
	public int termFrequency(DocumentId documentId, String term) throws IllegalArgumentException {
		if (!termCounts.containsKey(documentId)) {
			throw new IllegalArgumentException("Document not found");
		}
		return termCounts.get(documentId).getOrDefault(term, 0);
	}

	/**
	 * Returns the inverse document frequency of a term across all documents in the
	 * index.
	 * 
	 * For our purposes, IDF is defined as log ((1 + N) / (1 + M)) where
	 * N is the number of documents in total, and M
	 * is the number of documents where the term appears.
	 * 
	 * @param term
	 * @return the inverse document frequency of term
	 */
	public double inverseDocumentFrequency(String term) {
		int docCount = termCounts.size();
		int termDocCount = index.getOrDefault(term, Set.of()).size();
		return Math.log((1 + docCount) / (1 + termDocCount));
	}

	/**
	 * Returns the tfidf score of a particular term for a particular document.
	 * 
	 * tfidf is the product of term frequency and inverse document frequency for the
	 * given term and document.
	 * 
	 * @param documentId
	 * @param term
	 * @return the tfidf of the the term/document
	 * @throws IllegalArgumentException if the documentId has not been added to the
	 *                                  engine
	 */
	public double tfIdf(DocumentId documentId, String term) throws IllegalArgumentException {
		double tf = termFrequency(documentId, term);
		double idf = inverseDocumentFrequency(term);
		return tf * idf;
	}

	/**
	 * Returns a sorted list of documents, most relevant to least relevant, for the
	 * given term.
	 * 
	 * A document with a larger tfidf score is more relevant than a document with a
	 * lower tfidf score.
	 * 
	 * Each document in the returned list must contain the term.
	 * 
	 * @param term
	 * @return a list of documents sorted in descending order by tfidf
	 */
	public List<DocumentId> relevanceLookup(String term) {
		String lowered = term.toLowerCase();
		List<DocumentId> relevantDocs = new ArrayList<>(indexLookup(lowered));
		relevantDocs.sort(new TfIdfComparator(this, lowered));
		return relevantDocs;
	}
}
